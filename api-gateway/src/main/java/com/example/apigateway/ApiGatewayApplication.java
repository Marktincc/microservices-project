package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("service-proveedores", r -> r.path("/proveedores/**")
						.uri("http://localhost:8081"))
				.route("service-productos", r -> r.path("/productos/**")
						.uri("http://localhost:8082"))
				.route("service-categorias", r -> r.path("/categorias/**")
						.uri("http://localhost:8083"))
				.route("service-usuarios", r -> r.path("/usuarios/**")
						.uri("http://localhost:8084"))
				.route("service-ventas", r -> r.path("/ventas/**")
						.uri("http://localhost:8085"))
				.route("service-relaciones-ventas", r -> r.path("/relaciones-ventas/**")
						.uri("http://localhost:8086"))
				.route("service-reportes", r -> r.path("/reportes/**")
						.uri("http://localhost:8087"))
				.build();
	}
}