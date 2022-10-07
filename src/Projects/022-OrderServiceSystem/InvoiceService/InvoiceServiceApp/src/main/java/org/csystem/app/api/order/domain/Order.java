package org.csystem.app.api.order.domain;

import org.csystem.app.api.invoice.domain.Invoice;
import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_orders")
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn
    private Invoice invoice;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float quantity;

    @Column(name = "unit_price", nullable = false)
    private float unitPrice;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }
}
