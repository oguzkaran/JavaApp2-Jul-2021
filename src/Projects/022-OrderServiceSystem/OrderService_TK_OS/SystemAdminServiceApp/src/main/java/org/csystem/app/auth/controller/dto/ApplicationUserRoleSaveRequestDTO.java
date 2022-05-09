package org.csystem.app.auth.controller.dto;

import org.csystem.app.pdb.roles.ApplicationUserRoleName;


/**
 * @author onder sahin
 */
public class ApplicationUserRoleSaveRequestDTO {

    public ApplicationUserRoleName roleName;
    public Integer applicationUser;


    public ApplicationUserRoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRoleName roleName) {
        this.roleName = roleName;
    }

    public Integer getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(Integer applicationUser) {
        this.applicationUser = applicationUser;
    }
}
