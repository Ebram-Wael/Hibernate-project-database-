package org.example.dao;

import org.example.criteria.FilterQuery;
import org.example.models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
//to run the application in trminal: mvn spring-boot:run
public class DBEmployee {

  public List<Employee> get() {

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      //return session.createQuery("FROM Employee", Employee.class).list();

      return session.createSQLQuery("select * from employees").addEntity(Employee.class).list();

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }

    return null;
  }

  public Employee get(Integer employeeId) {

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      return session.get(Employee.class, employeeId);

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }

    return null;
  }


  public Integer insert(Employee employee) {

    Transaction transaction = null;
    int employeeId = 0;

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      transaction = session.beginTransaction();

      employeeId = (Integer) session.save(employee);

      transaction.commit();

    } catch (Exception ex) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(ex.getMessage());
    }

    return employeeId;
  }

  public void update(Employee employee) {

    Transaction transaction = null;

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      transaction = session.beginTransaction();

      session.update(employee);

      transaction.commit();

    } catch (Exception ex) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(ex.getMessage());
    }
  }

  public void delete(Integer employeeId) {

    Transaction transaction = null;

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      transaction = session.beginTransaction();

      Employee employee = get(employeeId);

      session.delete(employee);

      transaction.commit();

    } catch (Exception ex) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println(ex.getMessage());
    }
  }

}
