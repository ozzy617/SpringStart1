package comm.config;

import comm.database.repository.UserRepository;
import comm.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

//@ImportResource("classpath:application.xml")
@Configuration
public class ApplicationConfiguration {

    @Bean("connection")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool connectionPool1(@Value("username") String username) {
        return new ConnectionPool(username, "root", 20, "url");
    }

    @Bean
    public ConnectionPool connectionPool2() {
        return new ConnectionPool("mysql", "1222", 100, "---");
    }

//    @Bean
//    @Profile("prod")
//    public UserRepository userRepository() {
//        //return new UserRepository(connectionPool2());
//    }
}
