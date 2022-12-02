package com.formacion.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {
   @Bean
  public RouteLocator configureRoute(RouteLocatorBuilder builder) {
     return builder.routes()
              .route("client", r->r.path("/client/**").uri("lb://BACKEND"))
             .route("trip", r->r.path("/trip/**").uri("lb://BACKEND"))
             .route("ticket", r->r.path("/ticket/**").uri("lb://FRONTEND"))
              .build();
   }
}
