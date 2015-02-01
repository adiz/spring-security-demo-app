package ro.cti.ssa.twittboost.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author adrian.zamfirescu
 * @since 08/12/2014
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ro.cti.ssa.twittboost.dao")
public class DaoConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource(){

        DriverManagerDataSource twittBoostDataSource = new DriverManagerDataSource();
        twittBoostDataSource.setDriverClassName("org.h2.Driver");
        twittBoostDataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        twittBoostDataSource.setUsername("user");
        twittBoostDataSource.setPassword("pass");

        return twittBoostDataSource;
    }

    @Bean(name = "entityManagerFactory")
    @DependsOn("liquibase")
    public AbstractEntityManagerFactoryBean getEntityManagerFactoryBean(){

        LocalContainerEntityManagerFactoryBean twittBoostEmf = new LocalContainerEntityManagerFactoryBean();
        twittBoostEmf.setDataSource(getDataSource());
        twittBoostEmf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        twittBoostEmf.setPackagesToScan("ro.cti.ssa.twittboost.model");

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.show_sql","true");
        twittBoostEmf.setJpaProperties(jpaProperties);

        return twittBoostEmf;
    }

    @Bean(name = "transactionManager")
    public AbstractPlatformTransactionManager getTransactionManager(){

        JpaTransactionManager twittBoostJtm = new JpaTransactionManager();
        twittBoostJtm.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());

        return twittBoostJtm;
    }

    @Bean(name = "liquibase")
    public SpringLiquibase getLiquibase(){

        SpringLiquibase twittBoostLqb = new SpringLiquibase();
        twittBoostLqb.setDataSource(getDataSource());
        twittBoostLqb.setChangeLog("classpath:database.xml");

        return twittBoostLqb;
    }

}
