package com.explore.technologies.service.view.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${api.gateway.client.id}")
    private String apiGatewayClientId;

    private final DiscoveryClient discoveryClient;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable);
        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        security.authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                .requestMatchers(request -> {
                    ServiceInstance service = discoveryClient.getInstances("api-gateway").get(0);
                    String clientId = request.getHeader("X-Client-Id");
                    String remoteHost = request.getRemoteHost() + ":" + request.getRemotePort();
                    boolean requestCameFromApiGateway = true; //remoteHost.equals(service.getHost() + ":" + service.getPort());
                    boolean requestClientIdMatchApiGatewayClientId = clientId.equals(apiGatewayClientId);
                    return requestCameFromApiGateway && requestClientIdMatchApiGatewayClientId;
                }).permitAll()
                .anyRequest().denyAll());

        return security.build();
    }
}
