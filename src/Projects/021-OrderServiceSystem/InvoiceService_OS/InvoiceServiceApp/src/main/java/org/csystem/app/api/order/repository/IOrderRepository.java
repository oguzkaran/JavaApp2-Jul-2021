package org.csystem.app.api.order.repository;

import org.csystem.app.api.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IOrderRepository extends JpaRepository<Order,Integer> {
}
