package id.sch.lantabur.ltmsbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {

    @Bean
    public boolean isProd(Environment springEnv) {
        return springEnv.getActiveProfiles()[0].equals("production");
    }

}
