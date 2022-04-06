package com.ondersahin.app.pdb.roles;

/**
 * @author onder sahin
 */
public enum ApplicationUserRoleType {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String roleName;

    private ApplicationUserRoleType(String roleName){
        this.roleName = roleName;
    }

    public String getName(){
        return roleName;
    }
}
