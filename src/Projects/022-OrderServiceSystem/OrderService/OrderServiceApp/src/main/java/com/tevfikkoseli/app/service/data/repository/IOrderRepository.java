package com.tevfikkoseli.app.service.data.repository;
import com.tevfikkoseli.app.service.data.entity.Order;
import org.csystem.util.data.repository.ICrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface IOrderRepository extends ICrudRepository<Order, Long> {
    Iterable<Order> findByClientId(int clientId);
    Iterable<Order> findByDateTimeBetween(LocalDateTime begin, LocalDateTime end); //
    Iterable<Order> findByMonthAndYear(int month, int year);
    Iterable<Order> findByYearBetween(int begin, int end);
    Iterable<Order> findByDate(LocalDate date);
    Iterable<Order> findByProductId(int productId);
}
