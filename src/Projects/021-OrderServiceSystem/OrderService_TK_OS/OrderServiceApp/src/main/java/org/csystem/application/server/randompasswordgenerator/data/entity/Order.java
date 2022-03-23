package org.csystem.application.server.randompasswordgenerator.data.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {//POJO (Plain Old Java Object)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public long id;

    @Column(name = "o_date_time", nullable = false)
    public LocalDateTime oDateTime= LocalDateTime.now();

    @Column(name = "clinet_id", nullable = false)
    public int clinetId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<OrderProducts> orderProducts;






}
