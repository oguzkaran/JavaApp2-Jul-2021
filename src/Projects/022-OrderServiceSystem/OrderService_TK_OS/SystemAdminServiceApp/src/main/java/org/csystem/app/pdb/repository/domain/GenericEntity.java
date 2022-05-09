package org.csystem.app.pdb.repository.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author onder sahin
 */
public interface GenericEntity extends Serializable {

    int getId();

    LocalDateTime getCreatedDateTime();

    LocalDateTime getUpdatedDateTime();

    LocalDateTime getDeletedDateTime();

    Boolean isDeleted();

}
