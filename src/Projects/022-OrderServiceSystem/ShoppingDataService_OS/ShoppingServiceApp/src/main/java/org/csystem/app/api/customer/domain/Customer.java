package org.csystem.app.api.customer.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_customers")
public class Customer extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String identity;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    private boolean active;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Phone> phones;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
