package org.csystem.util.security.web.data.annotation;


import org.csystem.util.security.web.data.global.PackageInfo;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableJpaRepositories(PackageInfo.BASE_NAME)
@EntityScan(PackageInfo.BASE_NAME)
public @interface EnableRepositoryScan {

}
