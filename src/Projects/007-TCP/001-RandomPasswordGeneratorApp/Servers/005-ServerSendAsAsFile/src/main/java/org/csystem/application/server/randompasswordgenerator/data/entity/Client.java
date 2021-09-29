package org.csystem.application.server.randompasswordgenerator.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client { //POJO (Plain Old Java Object)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    public long id;

    @Column(nullable = false)
    public String host;

    @Column(nullable = false)
    public int count;

    @Column(nullable = false)
    public int length;

    @Column(nullable = false)
    public boolean status;

    public String passwords;

    @Column(name = "connection_time", nullable = false)
    public LocalDateTime connectionTime = LocalDateTime.now();

    public String filename;
}
