package id.sch.lantabur.ltmsbackend.rest.config;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.pac4j.core.config.Config;
import org.pac4j.springframework.web.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "org.pac4j.springframework.web")
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestConfig implements WebMvcConfigurer {

    @NonNull
    private Config config;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor(config)).addPathPatterns("/api/**");
    }

}
