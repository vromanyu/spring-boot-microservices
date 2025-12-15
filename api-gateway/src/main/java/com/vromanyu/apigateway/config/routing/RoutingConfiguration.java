package com.vromanyu.apigateway.config.routing;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;

@Configuration
public class RoutingConfiguration {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter, KeyResolver keyResolver) {
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
                            f.circuitBreaker(config -> {
                                config.setName("GATEWAY-EMPLOYEE-CIRCUIT-BREAKER");
                                config.setFallbackUri("/unavailable");
                            });
                            f.requestRateLimiter(config -> {
                                config.setRateLimiter(redisRateLimiter);
                                config.setKeyResolver(keyResolver);
                            });
                            return f;
                        })
                        .uri("lb://EMPLOYEE-MS"))
                .route(p -> p.path("/api-gateway/departments", "/api-gateway/departments/**")
                        .filters(f -> {
                            f.rewritePath("/api-gateway/departments(?<segment>.*)", "/api/departments${segment}");
                            f.addRequestHeader("GATEWAY-REQUEST", "1");
                            f.addResponseHeader("GATEWAY-RESPONSE", "1");
                            f.retry(config -> {
                                config.setRetries(3);
                                config.setMethods(HttpMethod.GET);
                                config.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true);
                            });
                            f.requestRateLimiter(config -> {
                                config.setRateLimiter(redisRateLimiter);
                                config.setKeyResolver(keyResolver);
                            });
                            return f;
                        })
                        .uri("lb://DEPARTMENT-MS")).build();
    }
}
