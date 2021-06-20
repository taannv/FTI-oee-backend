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
@EnableJpaRepositories(basePackages = {"com.dds.oee.repositorycanong02"},
        entityManagerFactoryRef = "canOng02EntityManager",
        transactionManagerRef = "canOng02TransactionManager")
public class CanOng02DataSourceConfig {

    @Autowired
    private Environment env;

    public CanOng02DataSourceConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean canOng02EntityManager() {
        System.out.println("loading config Can Ong 02");

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(canOng02DataSource());
        em.setPackagesToScan(new String[] { "com.dds.oee.entitycanong02" });

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
    @ConfigurationProperties(prefix="spring.second-c2-datasource")
    public DataSource canOng02DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.second-c2-datasource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.second-c2-datasource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.second-c2-datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.second-c2-datasource.password")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager canOng02TransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(canOng02EntityManager().getObject());

        return transactionManager;
    }
}
