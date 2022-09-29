package org.csystem.app.api.auth.domain;


import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_application_users")
public class ApplicationUser extends AbstractEntity {

    @Column(length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ApplicationUserRole> applicationUserRoles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<ApplicationUserRole> getApplicationUserRoles() {
        return applicationUserRoles;
    }

    public void setApplicationUserRoles(Set<ApplicationUserRole> applicationUserRoles) {
        this.applicationUserRoles = applicationUserRoles;
    }
}
