package com.ondersahin.app.auth.controller.dto;

import com.ondersahin.app.pdb.roles.ApplicationUserRoleType;


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
