package org.csystem.app.auth.controller.dto;

import org.csystem.app.pdb.roles.ApplicationUserRoleType;


/**
 * @author onder sahin
 */
public class ApplicationUserRoleSaveRequestDTO {

    private ApplicationUserRoleType roleName;
    private Integer applicationUser;


    public ApplicationUserRoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRoleType roleName) {
        this.roleName = roleName;
    }

    public Integer getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(Integer applicationUser) {
        this.applicationUser = applicationUser;
    }
}
