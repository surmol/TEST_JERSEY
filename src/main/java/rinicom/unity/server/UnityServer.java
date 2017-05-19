package rinicom.unity.server;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import rinicom.unity.server.database.HibernateUtil;
import rinicom.unity.server.services.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;



@ApplicationPath("/Services")
public class UnityServer extends Application {
	 @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> s = new HashSet<Class<?>>();
         s.add(EmployeeService.class);

	        try {
	    		Session session = HibernateUtil.getSessionFactory().openSession();
	    		session.close();

			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}

	        return s;
	    }


}
