package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name="ticket")
public class Ticket {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "created_at")
//    private ZonedDateTime createdAt;
//
//    @Column(name = "client_id")
//    private long client;
//
//    @Column(name = "from_planet_id")
//    private String from;
//
//    @Column(name = "to_planet_id")
//    private String to;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",  nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet to;

}
