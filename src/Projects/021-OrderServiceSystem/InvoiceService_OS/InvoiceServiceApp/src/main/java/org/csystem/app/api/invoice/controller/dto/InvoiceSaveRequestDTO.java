package org.csystem.app.api.invoice.controller.dto;

import java.time.LocalDate;

/**
 * @author onder sahin
 */
public class InvoiceSaveRequestDTO {

    private String customerTitle;
    private String customerIdentity;
    private String address;
    private LocalDate invoiceDate;
    private float invoiceTaxRatio;

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
}
