package edu.neu.csye6220.util;


import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import edu.neu.csye6220.model.*;


@Component
public class HibernateUtil {
	
	  private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hungercrushdb1?createDatabaseIfNotExist=true");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "password");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
	                settings.put(Environment.SHOW_SQL, "true");
	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	                settings.put(Environment.HBM2DDL_AUTO, "update");
	                settings.put(Environment.POOL_SIZE, "5");

	                configuration.setProperties(settings);

	                configuration.addAnnotatedClass(User.class);
	                configuration.addAnnotatedClass(Customer.class);
	                configuration.addAnnotatedClass(Restaurant.class);
	                configuration.addAnnotatedClass(FoodItem.class);
	                configuration.addAnnotatedClass(CartLine.class);
	                configuration.addAnnotatedClass(OrderItem.class);
	                configuration.addAnnotatedClass(Order.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}
