package id.sch.lantabur.ltmsbackend.config;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.security.web.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityStrategies {

    @Configuration
    @Order(1)
    public static class JwtAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            final SecurityFilter filter = new SecurityFilter(config, "HeaderClient");

            http
                    .antMatcher("/api/**")
                    .addFilterBefore(filter, BasicAuthenticationFilter.class)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER);
        }
    }


    // Add more auth here if needed
    // https://github.com/pac4j/spring-security-pac4j-boot-demo/blob/master/src/main/java/org/pac4j/demo/spring/SecurityConfig.java
}
