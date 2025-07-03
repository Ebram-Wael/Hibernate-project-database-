package org.example.dao;


import org.example.criteria.FilterQuery;
import org.example.criteria.Operator;
import org.example.models.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DBProduct {

    public List<Product> getAllProduct() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            return session.createQuery("FROM Product").list();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public List<Product> filterProduct(List<FilterQuery> filters) {
        if (filters == null || filters.isEmpty()) return new ArrayList<>();

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            List<Predicate> predicates = new ArrayList<>();

            for (FilterQuery filter : filters) {
                String attribute = filter.getAttribute();
                Object value = filter.getValue();

                switch (filter.getOp()) {
                    case GT -> predicates.add(cb.greaterThan(root.get(attribute), (Comparable) value));
                    case EQ -> predicates.add(cb.equal(root.get(attribute), value));
                    case LS -> predicates.add(cb.lessThan(root.get(attribute), (Comparable) value));
                }
            }

            cr.select(root);
            if (!predicates.isEmpty()) {
                cr.where(cb.and(predicates.toArray(new Predicate[0])));
            }

            return session.createQuery(cr).getResultList();
        } catch (Exception e) {
            System.err.println("Error filtering products: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Integer insert(Product product) {

        Transaction transaction = null;
        int prodId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            prodId = (Integer) session.save(product);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return prodId;
    }

    public static void main(String[] args) {
        DBProduct dbProduct = new DBProduct();
//        Product[] p = new Product[10];
//        for (int i = 0; i < 10; i++) {
//            p[i] = new Product();
//            p[i].setProductId(i+10);
//            p[i].setProductPrice((i+1)*100);
//            p[i].setProductName("newProduct"+(i+1));
//            p[i].setCategoryId(50);
//        }
//        for (int i = 0; i < 10; i++) {
//            dbProduct.insert(p[i]);
//        }
        List<FilterQuery> f =new ArrayList<>();
        f.add(new FilterQuery("categoryId",50, Operator.EQ));
        f.add(new FilterQuery("productPrice",500, Operator.EQ));
        System.out.println(dbProduct.filterProduct(f));

        DBConfig.shutDown();
    }
}