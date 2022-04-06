package com.ondersahin.app.auth.domain;

import com.ondersahin.app.pdb.repository.domain.AbstractEntity;
import com.ondersahin.app.pdb.roles.ApplicationUserRoleType;

import javax.persistence.*;

/**
 * @author onder sahin
 */

@Entity
@Table(name = "t_application_user_roles")
public class ApplicationUserRole extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private ApplicationUserRoleType roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_user_id",nullable = false)
    private ApplicationUser applicationUser;


    public ApplicationUserRoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRoleType roleName) {
        this.roleName = roleName;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
