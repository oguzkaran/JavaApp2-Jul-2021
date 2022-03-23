package org.csystem.app.api.customer.repository;

import org.csystem.app.api.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

}
