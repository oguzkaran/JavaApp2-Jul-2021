package org.csystem.app.api.customer.repository;

import org.csystem.app.api.customer.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IPhoneRepository extends JpaRepository<Phone,Integer> {

}
