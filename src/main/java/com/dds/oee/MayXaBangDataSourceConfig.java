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
@EnableJpaRepositories(basePackages = {"com.dds.oee.repository"},
        entityManagerFactoryRef = "mxbEntityManager",
        transactionManagerRef = "mxbTransactionManager")
public class MayXaBangDataSourceConfig {
    @Autowired
    private Environment env;

    public MayXaBangDataSourceConfig() {
        super();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mxbEntityManager() {
        System.out.println("loading config May Xa Bang");

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mxbDataSource());
        em.setPackagesToScan(new String[] { "com.dds.oee.entity" });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("jpa.show-sql", env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource mxbDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.datasource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.datasource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.datasource.password")));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mxbTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mxbEntityManager().getObject());

        return transactionManager;
    }
}
