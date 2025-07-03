package org.example.deo;

import org.example.criteria.FilterQuery;
import org.example.models.Blog;
import org.example.models.Comment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DBComment {
    public List<Comment> getAll() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM comments").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
    public Integer insert(Comment comment) {

        Transaction transaction = null;
        int commentId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            commentId = (Integer) session.save(comment);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return commentId;
    }

    public List<Comment> filterComment(List<FilterQuery> filters) {
        if (filters == null || filters.isEmpty()) return new ArrayList<>();

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Comment> cr = cb.createQuery(Comment.class);
            Root<Comment> root = cr.from(Comment.class);

            List<Predicate> predicates = new ArrayList<>();

            for (FilterQuery filter : filters) {
                String attribute = filter.getAttribute();
                Object value = filter.getValue();

                switch (filter.getOp()) {
                    case GT -> predicates.add(cb.greaterThan(root.get(attribute), (Comparable) value));
                    case EQ -> predicates.add(cb.equal(root.get(attribute), value));
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
}
