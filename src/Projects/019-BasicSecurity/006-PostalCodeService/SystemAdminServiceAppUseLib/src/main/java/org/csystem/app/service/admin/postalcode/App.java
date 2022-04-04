package org.csystem.app.service.admin.postalcode;

import org.csystem.util.security.web.data.annotation.EnableRepositoryScan;
import org.csystem.util.security.web.data.global.PackageInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {PackageInfo.BASE_NAME, "org.csystem"})
@EnableRepositoryScan
public class App {
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
