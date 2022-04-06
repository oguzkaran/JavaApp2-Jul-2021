package com.ondersahin.app.auth.repository;

import com.ondersahin.app.auth.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author onder sahin
 */

public interface IApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {

//    @Query(value = "insert into t_application_users (username, password, enabled,deleted, created_date_time, updated_date_time, deleted_date_time, description)\n" +
//            "values (:username, :password, :enabled, null, current_timestamp, current_timestamp, null, :description);", nativeQuery = true)
//    void saveApplicationUser(String username, String password, boolean enabled, String description);
}
