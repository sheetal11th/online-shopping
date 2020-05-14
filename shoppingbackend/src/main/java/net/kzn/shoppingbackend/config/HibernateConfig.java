package net.kzn.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"net.kzn.shoppingbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	//Change the below based on the DBMS you choose
	private final static String BATABASE_URL ="jdbc:h2:tcp://localhost/~/test";
	private final static String BATABASE_DRIVER ="org.h2.Driver";
	private final static String BATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private final static String BATABASE_USERNAME = "sa";
	private final static String BATABASE_PASSWORD= "";
	
	//Database bean will be available here
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		//Providing the database connection information
		dataSource.setDriverClassName(BATABASE_DRIVER);
		dataSource.setUrl(BATABASE_URL);
		dataSource.setUsername(BATABASE_USERNAME);
		dataSource.setPassword(BATABASE_PASSWORD);
		
		return dataSource;
	}
	//SessionFactory bean will be available here
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		
		LocalSessionFactoryBuilder builder =new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("net.kzn.shoppingbackend.dto");
		
		return builder.buildSessionFactory();
		
	}
	
	//All the Hibernate properties will be returned in this method
	
	private Properties getHibernateProperties() {
		Properties properties= new Properties();
		properties.put("hibernate.dialect", BATABASE_DIALECT);
		properties.putIfAbsent("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		return properties;
	}
	
	@Bean
	
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}

}
