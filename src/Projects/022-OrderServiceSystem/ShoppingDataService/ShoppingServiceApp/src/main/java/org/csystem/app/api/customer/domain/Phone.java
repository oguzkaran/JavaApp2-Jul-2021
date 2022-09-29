package org.csystem.app.api.customer.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_phones")
public class Phone extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(targetEntity = PhoneType.class)
    @JoinColumn(name = "phone_type_id",nullable = false)
    private PhoneType phoneType;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public PhoneType getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType)
    {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


}
