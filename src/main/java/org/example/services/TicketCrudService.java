package org.example.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class TicketCrudService {
    public long create(Ticket ticket) {
        if(!validation(ticket)){
            return -1;
        }
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ticket.getId();
    }
    public Ticket getById(String id) {
        try (Session session = openSession()) {
            Query<Ticket> query = session.createQuery("from Ticket where id = :id", Ticket.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);

        }
    }

    public long update(Ticket ticket) {
        if(!validation(ticket)){
            return -1;
        }
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ticket.getId();
    }
    public List<Ticket> listAll() {
        try (Session session = openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    public void deleteById(long id) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
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

    private boolean validation(Ticket ticket) {
        if (ticket == null || ticket.getClient() == null ||
                ticket.getFrom() == null ||
                ticket.getTo() == null) {
            return false;
        }
        Client client = new ClientCrudService().getById(ticket.getClient().getId());
        if (client == null)
            return false;
        Planet from = new PlanetCrudService().getById(ticket.getFrom().getId());
        if (from == null)
            return false;
        Planet to = new PlanetCrudService().getById(ticket.getTo().getId());
        if (to == null) {
            return false;
        }
        return true;
    }
}
