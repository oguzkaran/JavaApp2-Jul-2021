package org.csystem.application.server.randompasswordgenerator.data.repository;

import org.csystem.application.server.randompasswordgenerator.data.entity.Order;
import org.csystem.application.server.randompasswordgenerator.data.entity.OrderProducts;
import org.springframework.data.repository.CrudRepository;

public interface IOrderProductsServiceRepository extends CrudRepository<OrderProducts, Long> {
}
