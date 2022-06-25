package org.csystem.app.pdb.roles;

/**
 * @author onder sahin
 */
public enum ApplicationUserRoleName {

    ROLE_SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_VIEWER("ROLE_VIEWER");
    private final String roleName;

    ApplicationUserRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getName(){
        return roleName;
    }
}
