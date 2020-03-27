package com.project.gelingeducation.common.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.project.gelingeducation.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:config.properties"})
@ComponentScan(basePackages = {"com.project.gelingeducation"})
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;
    @Value("${jdbc.minPoolSize}")
    private int minPoolSize;
    @Value("${jdbc.autoCommitOnClose}")
    private boolean autoCommitOnClose;
    @Value("${jdbc.checkoutTimeout}")
    private int checkoutTimeout;
    @Value("${jdbc.acquireRetryAttempts}")
    private int acquireRetryAttempts;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;
    @Value("${hibernate.current_session_context_class}")
    private String hibernateCurrentSessionContextClass;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan(new String[] {"org.mobiletrain.aopspring.entity"});
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", hibernateDialect);
        props.setProperty("hibernate.show_sql", hibernateShowSql);
        props.setProperty("hibernate.format_sql", hibernateFormatSql);
        props.setProperty("hibernate.current_session_context_class", hibernateCurrentSessionContextClass);
        props.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Subject.class, Course.class,
                Video.class, User.class, LoginLog.class, Role.class, Permission.class);
        return factoryBean;
    }


    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setAutoCommitOnClose(autoCommitOnClose);
        dataSource.setCheckoutTimeout(checkoutTimeout);
        dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
            throws PropertyVetoException {
        return new HibernateTransactionManager(sessionFactory);
    }

}
