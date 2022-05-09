package org.csystem.app.pdb.roles;

/**
 * @author onder sahin
 */
public enum ApplicationUserRoleName {

    ROLE_SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String roleName;

    private ApplicationUserRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getName(){
        return roleName;
    }
}
