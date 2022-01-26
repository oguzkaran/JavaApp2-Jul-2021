package org.csystem.app.service.admin.sensor.data.repository;

import org.csystem.app.service.admin.sensor.data.entity.MemberRole;
import org.springframework.data.repository.CrudRepository;

public interface IMemberRoleRepository extends CrudRepository<MemberRole, Integer> {
    
}
