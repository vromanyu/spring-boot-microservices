package com.vromanyu.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api-gateway/employees/info")
                        .filters(f -> f.rewritePath("/api-gateway/employees/info", "/actuator/info"))
                        .uri("lb://EMPLOYEE-MS"))
                .route(p -> p.path("/api-gateway/departments/info")
                        .filters(f -> f.rewritePath("/api-gateway/departments/info", "/actuator/info"))
                        .uri("lb://DEPARTMENT-MS"))
                .route(p -> p.path("/api-gateway/employees", "/api-gateway/employees/**")
                        .filters(f -> {
                            f.rewritePath("/api-gateway/employees(?<segment>.*)", "/api/employees${segment}");
                            f.addRequestHeader("GATEWAY-REQUEST", "1");
                            f.addResponseHeader("GATEWAY-RESPONSE", "1");
                            f.circuitBreaker(config -> config.setName("GATEWAY-CircuitBreaker"));
                            return f;
                        })
                                .uri("lb://EMPLOYEE-MS"))
                .route(p -> p.path("/api-gateway/departments", "/api-gateway/departments/**")
                        .filters(f -> {
                            f.rewritePath("/api-gateway/departments(?<segment>.*)", "/api/departments${segment}");
                            f.addRequestHeader("GATEWAY-REQUEST", "1");
                            f.addResponseHeader("GATEWAY-RESPONSE", "1");
                            return f;
                        })
                        .uri("lb://DEPARTMENT-MS")).build();
    }
}
