package com.tevfikkoseli.app.service.data.repository;
import com.tevfikkoseli.app.service.data.entity.Order;
import org.csystem.util.data.repository.ICrudRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



public interface IOrderServiceRepository extends ICrudRepository<Order, Long> {

}
