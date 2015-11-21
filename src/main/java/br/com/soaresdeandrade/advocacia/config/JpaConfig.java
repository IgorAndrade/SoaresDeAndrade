package br.com.soaresdeandrade.advocacia.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("br.com.soaresdeandrade.advocacia.repository")
@EnableTransactionManagement
@ComponentScan({ "br.com.soaresdeandrade.advocacia.config" })
class JpaConfig implements TransactionManagementConfigurer {

	@Autowired
	private Environment env;
	
    public DataSource configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        config.setJdbcUrl(env.getProperty("dataSource.username"));
        config.setUsername(env.getProperty("dataSource.url"));
        config.setPassword(env.getProperty("dataSource.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }
    
    @Bean(name={"JNDI"})
    public DataSource dataSourceJNDI() {
    	  final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
          dsLookup.setResourceRef(true);
          DataSource dataSource = dsLookup.getDataSource(env.getProperty("dataSource.jndi"));
          return dataSource;
    }


   
	@Bean(name={"entityManagerFactory"}) 
	public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
		 LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	       entityManagerFactoryBean.setPackagesToScan("br.com.soaresdeandrade.advocacia");
	        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	        entityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");
	        entityManagerFactoryBean.setDataSource(dataSourceJNDI());
	        entityManagerFactoryBean.setJpaProperties(getProperties());
	        entityManagerFactoryBean.afterPropertiesSet();
		    return entityManagerFactoryBean;
		
	}
    

    @Bean(name={"transactionManager"})
    public PlatformTransactionManager annotationDrivenTransactionManager() {
      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(configureEntityManagerFactory().getObject());
      return txManager;
    }

	private Properties getProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put(org.hibernate.cfg.Environment.DIALECT,env.getProperty("hibernate.dialect"));
		jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		return jpaProperties;
	}


    
}
