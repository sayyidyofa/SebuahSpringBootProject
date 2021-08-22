package id.sch.lantabur.ltmsbackend.config;

import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityStrategiesConfig {

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

        return new Config(jwtClient);
    }

    @Bean
    public JwtGenerator jwtGenerator() {
        final SecretSignatureConfiguration secretSignatureConfiguration = new SecretSignatureConfiguration(secret);
        final SecretEncryptionConfiguration secretEncryptionConfiguration = new SecretEncryptionConfiguration(secret);
        return new JwtGenerator(secretSignatureConfiguration, secretEncryptionConfiguration);
    }

}
