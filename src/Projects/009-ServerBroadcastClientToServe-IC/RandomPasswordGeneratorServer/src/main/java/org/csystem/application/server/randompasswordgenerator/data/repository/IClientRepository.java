package org.csystem.application.server.randompasswordgenerator.data.repository;

import org.csystem.application.server.randompasswordgenerator.data.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepository extends CrudRepository<Client, Long> {

}
