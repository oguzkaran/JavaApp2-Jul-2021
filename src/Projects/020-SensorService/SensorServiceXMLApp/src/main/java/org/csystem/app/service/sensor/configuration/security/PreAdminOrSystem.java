package org.csystem.app.service.sensor.configuration.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SYSTEM')")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SYSTEM')")
public @interface PreAdminOrSystem {
}
