package com.tevfikkoseli.app.service.data.repository;

import com.tevfikkoseli.app.service.data.entity.OrderProduct;
import org.csystem.util.data.repository.ICrudRepository;

public interface IOrderProductRepository extends ICrudRepository<OrderProduct, Long> {
    Iterable<OrderProduct> findByOrderId(long orderId);
    Iterable<OrderProduct> findByProductId(int productId);
}
