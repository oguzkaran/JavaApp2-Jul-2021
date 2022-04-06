package com.tevfikkoseli.app.service.data.repository;

import com.tevfikkoseli.app.service.data.entity.OrderProducts;
import org.springframework.data.repository.CrudRepository;

public interface IOrderProductsServiceRepository extends CrudRepository<OrderProducts, Long> {
}
