package org.csystem.app.api.customer.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_phone_types")
public class PhoneType extends AbstractEntity {

    @Column(nullable = false)
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
