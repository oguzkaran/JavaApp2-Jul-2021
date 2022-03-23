package org.csystem.app.api.product.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_product_categories")
public class ProductCategory extends AbstractEntity {

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
