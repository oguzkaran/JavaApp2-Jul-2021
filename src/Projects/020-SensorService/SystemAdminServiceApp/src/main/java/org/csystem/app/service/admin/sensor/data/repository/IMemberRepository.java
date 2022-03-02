package org.csystem.app.service.admin.sensor.data.repository;

import org.csystem.app.service.admin.sensor.data.entity.Member;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

public interface IMemberRepository extends CrudRepository<Member, Integer> {
    @Procedure("insert_member")
    void insertMember(String username, String password, boolean enabled);
}
