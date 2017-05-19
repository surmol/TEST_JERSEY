package rinicom.unity.server.database;

import org.hibernate.Session;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import rinicom.unity.server.database.entities.Employee;

public class EmployeeFacade {

    public void addEmployee(Employee bean){
    	Session session = HibernateUtil.getSessionFactory().openSession();        
        Transaction tx = session.beginTransaction();
        addEmployee(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addEmployee(Session session, Employee bean){
        Employee employee = new Employee();
        
        employee.setName(bean.getName());
        employee.setAge(bean.getAge());
        
        session.save(employee);
    }
    
    public List<Employee> getEmployees(){
    	Session session = HibernateUtil.getSessionFactory().openSession();   
        Query query = session.createQuery("from Employee");
        List<Employee> employees =  query.list();
        session.close();
        return employees;
    }
 
    public int deleteEmployee(int id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Employee where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    

}