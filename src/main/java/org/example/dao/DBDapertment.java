package org.example.dao;


import org.example.models.Department;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBDapertment {
    public List<Department> get() {
        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
            //return session.createSQLQuery("select * from departments").addEntity(Department.class).list();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
    public Department get(int id){
        try (Session session = DBConfig.SESSION_FACTORY.openSession()){
           return session.get(Department.class , id);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
    public int insert(Department department){
        Transaction transaction = null;
        int departmentId = 0;
        try (Session session = DBConfig.SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            departmentId = (int)session.save(department);
            transaction.commit();

        }
        catch (Exception e){
            if(transaction != null)
                transaction.rollback();

            System.err.println(e.getMessage());
        }
        return departmentId;
    }
    public void update (Department department){
        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
    public void delete (int id){
        Transaction transaction = null;
        try (Session session = DBConfig.SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            Department d = get(id);
            session.delete(d);
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
}
