package org.example.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entities.Planet;
import org.example.util.HibernateUtil;

import java.util.List;

public class PlanetCrudService {
    public String create(Planet planet) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return planet.getId();
    }
    public Planet getById(String id) {
        try (Session session = openSession()) {
            Query<Planet> query = session.createQuery("from Planet where id = :id", Planet.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);

        }
    }

    public void update(Planet planet) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Planet> listAll() {
        try (Session session = openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }

    public void deleteById(String id) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    private Session openSession() {
        return HibernateUtil
                .getInstance()
                .getSessionFactory()
                .openSession();
    }
}
