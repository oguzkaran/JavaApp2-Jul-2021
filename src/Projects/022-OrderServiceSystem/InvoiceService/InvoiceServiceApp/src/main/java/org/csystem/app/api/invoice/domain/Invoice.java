package org.csystem.app.api.invoice.domain;

import org.csystem.app.api.order.domain.Order;
import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_invoices")
public class Invoice extends AbstractEntity {
    @Column(name = "customer_title", nullable = false)
    private String customerTitle;

    @Column(name = "customer_identity", nullable = false)
    private String customerIdentity;

    @Column(nullable = false)
    private String address;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "invoice_tax_ratio")
    private float invoiceTaxRatio;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<org.csystem.app.api.order.domain.Order> orders;

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerIdentity() {
        return customerIdentity;
    }

    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public float getInvoiceTaxRatio() {
        return invoiceTaxRatio;
    }

    public void setInvoiceTaxRatio(float invoiceTaxRatio) {
        this.invoiceTaxRatio = invoiceTaxRatio;
    }

    public Set<org.csystem.app.api.order.domain.Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<org.csystem.app.api.order.domain.Order> orders) {
        this.orders = orders;
    }
}
