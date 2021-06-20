package com.dds.oee;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 01, 2021
 *
 */
@Configuration
@PropertySource({"classpath:datasource-config.properties"})
@EnableJpaRepositories(basePackages = {"com.dds.oee.repositorycanong03"},
        entityManagerFactoryRef = "canOng03EntityManager",
        transactionManagerRef = "canOng03TransactionManager")
public class CanOng03DataSourceConfig {

    @Autowired
    private Environment env;

    public CanOng03DataSourceConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean canOng03EntityManager() {
        System.out.println("loading config Can Ong 03");

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(canOng03DataSource());
        em.setPackagesToScan(new String[] { "com.dds.oee.entitycanong03" });

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
    @ConfigurationProperties(prefix="spring.second-c3-datasource")
    public DataSource canOng03DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.second-c3-datasource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.second-c3-datasource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.second-c3-datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.second-c3-datasource.password")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager canOng03TransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(canOng03EntityManager().getObject());

        return transactionManager;
    }
}
