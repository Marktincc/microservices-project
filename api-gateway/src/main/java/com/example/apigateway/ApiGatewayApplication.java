package com.example.apigateway;

import com.example.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.example.apigateway",
		"com.example.commonutils.security.jwt"
})
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, JwtAuthenticationFilter jwtAuthFilter) {
		return builder.routes()
				.route("service-proveedores", r -> r.path("/proveedores/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8081"))
				.route("service-productos", r -> r.path("/productos/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8082"))
				.route("service-categorias", r -> r.path("/categorias/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8083"))
				.route("service-usuarios", r -> r.path("/usuarios/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8084"))
				.route("service-ventas", r -> r.path("/ventas/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8085"))
				.route("service-relaciones-ventas", r -> r.path("/relaciones-ventas/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8086"))
				.route("service-reportes", r -> r.path("/reportes/**")
						.filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("http://localhost:8087"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		corsConfig.setAllowedHeaders(Arrays.asList("*"));
		corsConfig.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
