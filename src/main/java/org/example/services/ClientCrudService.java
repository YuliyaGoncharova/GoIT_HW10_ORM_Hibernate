package org.example.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entities.Client;
import org.example.util.HibernateUtil;

import java.awt.desktop.SystemSleepEvent;
import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientCrudService {

    public long create(Client client) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            return client.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Client getById(long id) {
        try (Session session = openSession()) {
            Query<Client> query = session.createQuery("from Client where id = :id", Client.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);

        }
    }

    //    списку всіх сутностей
    public List<Client> listAll() {
        try (Session session = openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }
    public void update(Client client) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void deleteById(long id) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
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

