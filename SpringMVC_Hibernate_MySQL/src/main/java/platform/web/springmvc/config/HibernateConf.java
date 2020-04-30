package platform.web.springmvc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource("classpath:hibernateconfig.properties")
@ComponentScan(basePackages = { "platform.web.springmvc" })
public class HibernateConf {
	// Using environment bean to read property configuration from
	// hibernateconfig.properties file
	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		System.out.println("\n\n\n\n>>>>>> HibernateConf.sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "platform.web.springmvc" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		System.out.println(
				"-----> HibernateConfiguration--> sessionFactory-->localSesFactory:" + sessionFactory.toString());
		/*
		 * System.out.println("-----> HibernateConfiguration--> sessionFactory--
		 * >SessonFactory:" + sessionFactory.getObject().toString());
		 */
		System.out.println(">>>>>>End sessionFactory\n\n\n\n");
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		System.out.println(">>>>>> HibernateConf.dataSource");
		// BasicDataSource dataSource = new BasicDataSource();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		System.out.println(" - jdbc.driverClassName:" + environment.getRequiredProperty("jdbc.driverClassName"));
		System.out.println(" - jdbc.url:" + environment.getRequiredProperty("jdbc.url"));
		System.out.println(" - jdbc.username:" + environment.getRequiredProperty("jdbc.username"));
		System.out.println(" - jdbc.password: " + environment.getRequiredProperty("jdbc.password"));
		System.out.println(">>>>>>End dataSource");
		return dataSource;
	}

	private final Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("current_session_context_class", environment.getProperty("current_session_context_class"));
		System.out.println(" - hibernate.dialect:" + environment.getRequiredProperty("hibernate.dialect"));
		System.out.println(" - hibernate.show_sql:" + environment.getRequiredProperty("hibernate.show_sql"));
		System.out.println(" - hibernate.format_sql" + environment.getRequiredProperty("hibernate.format_sql"));
		System.out.println(
				" - current_session_context_class:" + environment.getProperty("current_session_context_class"));
		return properties;
	}

	/*
	 * @Autowired
	 * 
	 * @Bean(name = "transactionManager") public HibernateTransactionManager
	 * transactionManager(SessionFactory s) { System.out.println(">>>>>>
	 * HibernateConfiguration.transactionManager -- >SessionFactory: " +
	 * s.toString()); HibernateTransactionManager txManager = new
	 * HibernateTransactionManager(s); // txManager.setSessionFactory(s);
	 * System.out.println("\t\t>>>>>> sessionFactory: " + s.toString());
	 * System.out.println(">>>>>>End transactionManager\n\n\n\n"); return txManager;
	 * }
	 */
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		System.out.println("\n\n\n\n>>>>>>HibernateConfiguration.getTransactionManager -->SessionFactory: ");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		SessionFactory sessionFactory = getSessionFactory().getObject();
		System.out.println("\t\t>>>>>> sessionFactory: " + sessionFactory.toString());
		transactionManager.setSessionFactory(sessionFactory);
		System.out.println(">>>>>> End getTransactionManager");
		return transactionManager;
	}
}
