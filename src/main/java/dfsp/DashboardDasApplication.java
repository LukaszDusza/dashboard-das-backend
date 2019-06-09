package dfsp;

import dfsp.configs.ConfigLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Główna klasa uruchomieniowa aplikacji.
* */

@SpringBootApplication
@PropertySource("classpath:/config.properties")
public class DashboardDasApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardDasApplication.class, args);
    }


//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}

