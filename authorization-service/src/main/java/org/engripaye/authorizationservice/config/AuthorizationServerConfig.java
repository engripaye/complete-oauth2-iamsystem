package org.engripaye.authorizationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    @Configuration
    public static class RegisteredClientConfig {
        @Bean
        public RegisteredClientRepository registeredClientRepository() {
            RegisteredClient frontendClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("frontend-client")
                    .clientSecret("{noop}frontend-secret")
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .redirectUri("http://localhost:3000/callback") // must match frontend
                    .scope("read") // no "openid" unless doing OIDC
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .build();

            return new InMemoryRegisteredClientRepository(frontendClient);
        }


        @Bean
        public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
            OAuth2AuthorizationServerConfigurer configurer = new OAuth2AuthorizationServerConfigurer();

            http
                    .securityMatcher(configurer.getEndpointsMatcher())
                    .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                    .csrf(csrf -> csrf.ignoringRequestMatchers(configurer.getEndpointsMatcher()))
                    .with(configurer, c -> {
                        // enable all standard endpoints
                        c.tokenEndpoint(Customizer.withDefaults());
                        c.authorizationEndpoint(Customizer.withDefaults());
                    });

            return http.build();
        }

        @Bean
        public AuthorizationServerSettings authorizationServerSettings() {
            return AuthorizationServerSettings.builder()
                    .issuer("http://localhost:8081")
                    .build();
        }
    }}
