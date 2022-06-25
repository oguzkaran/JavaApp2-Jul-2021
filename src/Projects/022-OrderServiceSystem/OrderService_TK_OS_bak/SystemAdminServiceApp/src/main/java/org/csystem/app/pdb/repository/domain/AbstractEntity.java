package org.csystem.app.pdb.repository.domain;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author onder sahin
 */
@MappedSuperclass
public class AbstractEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Access(AccessType.FIELD)
    @Column(name = "created_date_time",nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Access(AccessType.FIELD)
    @Column(name = "updated_date_time", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    @Access(AccessType.FIELD)
    @Column(name = "deleted_date_time")
    private LocalDateTime deletedDateTime;

    @Access(AccessType.FIELD)
    private Boolean deleted = false;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    @Override
    public LocalDateTime getDeletedDateTime() {
        return deletedDateTime;
    }

    @Override
    public Boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
