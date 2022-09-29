package org.csystem.app.api.customer.repository;

import org.csystem.app.api.customer.domain.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IPhoneTypeRepository extends JpaRepository<PhoneType,Integer> {
}
