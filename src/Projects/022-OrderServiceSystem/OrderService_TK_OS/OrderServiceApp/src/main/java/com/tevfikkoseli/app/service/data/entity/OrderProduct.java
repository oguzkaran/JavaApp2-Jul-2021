package com.tevfikkoseli.app.service.data.entity;


public class OrderProduct {//POJO (Plain Old Java Object)
    public long id;
    public long orderId;
    public int productId;
    public double quantity;
    public double unitPrice;

    public OrderProduct(long id, long orderId, int productId, double quantity, double unitPrice)
    {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
