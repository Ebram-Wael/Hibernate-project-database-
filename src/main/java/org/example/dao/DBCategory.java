package org.example.dao;

import org.example.models.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBCategory {
    public List<Category> get(){
        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
    public int insert(Category c){
        Transaction transaction = null;
        int cId = 0;
        try (Session session = DBConfig.SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            cId = (int)session.save(c);
            transaction.commit();

        }
        catch (Exception e){
            if(transaction != null)
                transaction.rollback();

            System.err.println(e.getMessage());
        }
        return cId;
    }

    public static void main(String[] args) {
        System.out.println(new DBCategory().get());
    }
}
