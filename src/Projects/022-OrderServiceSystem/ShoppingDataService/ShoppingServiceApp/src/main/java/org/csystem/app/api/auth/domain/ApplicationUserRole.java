package org.csystem.app.api.auth.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;
import org.csystem.app.pdb.roles.ApplicationUserRoleName;

import javax.persistence.*;

/**
 * @author onder sahin
 */

@Entity
@Table(name = "t_application_user_roles")
public class ApplicationUserRole extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private ApplicationUserRoleName roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_user_id",nullable = false)
    private ApplicationUser applicationUser;


    public ApplicationUserRoleName getRoleName()
    {
        return roleName;
    }

    public void setRoleName(ApplicationUserRoleName roleName)
    {
        this.roleName = roleName;
    }

    public ApplicationUser getApplicationUser()
    {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser)
    {
        this.applicationUser = applicationUser;
    }
}
