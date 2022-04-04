package org.csystem.util.security.web.data.repository;


import org.csystem.util.security.web.data.entity.User;
import org.csystem.util.security.web.data.global.BeanName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(BeanName.USER_REPOSITORY)
public interface IUserRepository extends CrudRepository<User, String> {

}
