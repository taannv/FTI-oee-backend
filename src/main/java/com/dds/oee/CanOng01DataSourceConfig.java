package com.dds.oee;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 04, 2021
 *
 */

@Configuration
@PropertySource({"classpath:datasource-config.properties"})
@EnableJpaRepositories(basePackages = {"com.dds.oee.repositorycanong01"},
        entityManagerFactoryRef = "canOng01EntityManager",
        transactionManagerRef = "canOng01TransactionManager")
public class CanOng01DataSourceConfig {
    @Autowired
    private Environment env;

    public CanOng01DataSourceConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean canOng01EntityManager() {
        System.out.println("loading config Can Ong 01");

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(canOng01DataSource());
        em.setPackagesToScan(new String[] { "com.dds.oee.entitycanong01" });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("jpa.show-sql", env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource canOng01DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.second-datasource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.second-datasource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.second-datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.second-datasource.password")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager canOng01TransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(canOng01EntityManager().getObject());

        return transactionManager;
    }
}
