package org.csystem.app.auth.repository;

import org.csystem.app.auth.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author onder sahin
 */

public interface IApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {

    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
