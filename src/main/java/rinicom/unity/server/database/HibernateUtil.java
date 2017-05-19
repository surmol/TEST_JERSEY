package rinicom.unity.server.database;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class HibernateUtil {
	
	final static Logger logger = Logger.getLogger(SessionFactory.class);

	 private static final SessionFactory sessionFactory = buildSessionFactory();
	  
	    private static SessionFactory buildSessionFactory() {
	        try {
	        	
	        	PrintWriter writer = new PrintWriter(new FileOutputStream(
	        		    new File("../logs/erro_hibernate.log"), 
	        		    true /* append = true */)); 
	        	
	        	writer.println("DB Configuring...");
	        	
	        	
	        	String hibernatePropsFilePath = "../applications/UnityServer/WEB-INF/classes/hibernate.cfg.xml";
	        	String hibernatePropsFilePathEclipse = "../eclipseApps/UnityServer/WEB-INF/classes/hibernate.cfg.xml";
	        	//String hibernatePropsFilePath = "/home/admin/hibernate.cfg.xml";
	        	
	        	// System.out.println("Working Directory = " +
	            //         System.getProperty("user.dir"));
	        	
	        	File hibernatePropsFile = new File(hibernatePropsFilePath);
	        	
	        	if( !hibernatePropsFile.exists() ) { 
	        		hibernatePropsFile = new File(hibernatePropsFilePathEclipse);
	        	}
	        	
	        	Configuration cfg= new Configuration();  
	  		    
	        	cfg.configure(hibernatePropsFile);
	            
	        	// Create the SessionFactory from hibernate.cfg.xml
	        	
	        	logger.warn("leaving the build session factory");
	        	
	        	SessionFactory session =  cfg.buildSessionFactory();
	        	
        		writer.println("DB Configured OK");
        		writer.close();
			
        		return session;
	        } catch (HibernateException | IOException ex) {
	            // Make sure you log the exception, as it might be swallowed
	            //System.err.println("Initial SessionFactory creation failed." + ex);
	        	
/*	        	try {
	        		PrintWriter writer = new PrintWriter(new FileOutputStream(
		        		    new File("//home//admin//erro_hibernate.log"), 
		        		    true *//* append = true *//*));
	        		writer.println("Database Error:" + ex.toString());
	        		writer.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	        	
	            //throw new ExceptionInInitializerError(ex);
	            return null;
	        }
	    }
	    
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	  
	    public static void shutdown() {
	        // Close caches and connection pools
	        getSessionFactory().close();
	    }
	
}
