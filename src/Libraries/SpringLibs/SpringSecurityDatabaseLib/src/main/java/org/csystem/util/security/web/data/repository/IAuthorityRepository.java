package org.csystem.util.security.web.data.repository;

import org.csystem.util.security.web.data.entity.Authority;
import org.csystem.util.security.web.data.global.BeanName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(BeanName.AUTHORITY_REPOSITORY)
public interface IAuthorityRepository extends CrudRepository<Authority, Integer> {
}
