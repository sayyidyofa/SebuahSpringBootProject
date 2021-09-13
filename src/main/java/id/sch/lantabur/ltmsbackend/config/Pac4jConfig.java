package id.sch.lantabur.ltmsbackend.config;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.JEESessionStore;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.util.FindBest;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class Pac4jConfig {

    @Value("${secret}")
    private String secret;

    @Bean
    public Config config() {
        final SecretSignatureConfiguration secretSignatureConfiguration = new SecretSignatureConfiguration(secret);
        final SecretEncryptionConfiguration secretEncryptionConfiguration = new SecretEncryptionConfiguration(secret);
        final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.setSignatureConfiguration(secretSignatureConfiguration);
        jwtAuthenticator.setEncryptionConfiguration(secretEncryptionConfiguration);
        HeaderClient jwtClient = new HeaderClient("Authorization", "Bearer", jwtAuthenticator);

        // Add more clients here if needed
        // https://github.com/pac4j/spring-security-pac4j-boot-demo/blob/master/src/main/java/org/pac4j/demo/spring/Pac4jConfig.java

        return new Config(jwtClient);
    }

    @Bean
    public JwtGenerator jwtGenerator() {
        final SecretSignatureConfiguration secretSignatureConfiguration = new SecretSignatureConfiguration(secret);
        final SecretEncryptionConfiguration secretEncryptionConfiguration = new SecretEncryptionConfiguration(secret);
        return new JwtGenerator(secretSignatureConfiguration, secretEncryptionConfiguration);
    }

    // Replacement for spring-webmvc-pac4j ComponentConfig
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Bean
    @RequestScope
    public SessionStore getSessionStore() {
        return FindBest.sessionStore(null, config(), JEESessionStore.INSTANCE);
    }

    @Bean
    @RequestScope
    public JEEContext getWebContext() {
        return new JEEContext(request, response);
    }

    @Bean
    @RequestScope
    public ProfileManager getProfileManager() {
        return new ProfileManager(getWebContext(), getSessionStore());
    }

}
