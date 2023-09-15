package org.example.util;


import lombok.Getter;
import org.example.entities.Ticket;
import org.hibernate.SessionFactory;
import org.example.entities.Client;
import org.example.entities.Planet;

import org.hibernate.cfg.Configuration;

@Getter
public class HibernateUtil {
    private static final HibernateUtil INSTANCE;


    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil(){
        sessionFactory = new Configuration()
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .buildSessionFactory();
    }

    public static HibernateUtil getInstance(){
        return INSTANCE;
    }

//    public static SessionFactory getConfiguration() {
//        return sessionFactory;
//    }

    public void close(){
        sessionFactory.close();
    }
}
