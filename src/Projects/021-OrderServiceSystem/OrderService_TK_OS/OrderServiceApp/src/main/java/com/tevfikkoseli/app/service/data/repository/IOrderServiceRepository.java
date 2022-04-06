package com.tevfikkoseli.app.service.data.repository;
import com.tevfikkoseli.app.service.data.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderServiceRepository extends CrudRepository<Order, Long> {

}
