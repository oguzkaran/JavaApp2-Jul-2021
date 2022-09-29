package com.ondersahin.app.auth.repository;

import com.ondersahin.app.auth.domain.ApplicationUserRole;
import org.springframework.data.repository.CrudRepository;

/**
 * @author onder sahin
 */
public interface IApplicationUserRoleRepository extends CrudRepository<ApplicationUserRole, Integer> {

//    @Query(value = "insert into t_application_user_roles (application_user_id, role_name, deleted, created_date_time, updated_date_time, deleted_date_time, description)\n" +
//            "values (:applicationUserId, :roleName,null, current_timestamp, current_timestamp, null, :description);", nativeQuery = true)
//    void saveApplicationUserRoleByUserId(int applicationUserId, String roleName, String description);

}
