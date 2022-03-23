package org.csystem.app.api.auth.repository;

import org.csystem.app.api.auth.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author onder sahin
 */

public interface IApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {

    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
