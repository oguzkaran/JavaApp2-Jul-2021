package org.csystem.application.server.randompasswordgenerator.data.entity;



import javax.persistence.*;

@Entity
@Table(name = "ordeorderproducts")
public class OrderProducts {//POJO (Plain Old Java Object)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    public long order_product_id;


    @Column(nullable = false)
    public long order_id;

    @Column(name = "product_id", nullable = false)
    public int productId;

    @Column(name = "quantity", nullable = false)
    public double quantity;

    @Column(name  = "unit_price", nullable = false)
    public double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    public Order order;

}
