package org.csystem.application.server.randompasswordgenerator.data.repository;
import org.csystem.application.server.randompasswordgenerator.data.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderServiceRepository extends CrudRepository<Order, Long> {

}
