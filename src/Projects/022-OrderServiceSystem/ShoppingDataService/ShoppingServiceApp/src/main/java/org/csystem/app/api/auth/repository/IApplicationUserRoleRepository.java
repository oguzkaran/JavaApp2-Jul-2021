package org.csystem.app.api.auth.repository;

import org.csystem.app.api.auth.domain.ApplicationUserRole;
import org.springframework.data.repository.CrudRepository;

/**
 * @author onder sahin
 */
public interface IApplicationUserRoleRepository extends CrudRepository<ApplicationUserRole, Integer> {

}
