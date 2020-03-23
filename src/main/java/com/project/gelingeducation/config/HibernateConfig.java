package com.project.gelingeducation.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.project.gelingeducation.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(value = "com.project.gelingeducation")
@EnableTransactionManagement
public class HibernateConfig {


    @Bean
    public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan(new String[] {"org.mobiletrain.aopspring.entity"});
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Subject.class, Course.class,
                Video.class, AdminInfo.class, LoginLog.class);
        return factoryBean;
    }


    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/education?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(10);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setCheckoutTimeout(10000);
        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) throws PropertyVetoException {
        return new HibernateTransactionManager(sessionFactory);
    }

}
