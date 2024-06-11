package comm;

import comm.config.DatabaseProperties;
import comm.database.entity.Company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBean("connection"));
        System.out.println(context.getBean(DatabaseProperties.class));

//        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
//        var userRepository = context.getBean(UserRepository.class);
//        var companyService = context.getBean(CompanyService.class);
//        companyService.findById(1);
        //var userService = context.getBean(UserService.class);
        //System.out.println(userRepository);
//        context.close();
    }
}
