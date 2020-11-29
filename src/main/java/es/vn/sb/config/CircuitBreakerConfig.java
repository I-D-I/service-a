package es.vn.sb.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class CircuitBreakerConfig {

	@Bean
	public CircuitBreakerConfigCustomizer someRemoteSvcCircuitBreaker() {
		return CircuitBreakerConfigCustomizer.of("serviceb", builder -> {
			builder.slidingWindowSize(5);
			builder.failureRateThreshold(3);
			builder.waitDurationInOpenState(Duration.ofSeconds(10));
			builder.slowCallDurationThreshold(Duration.ofSeconds(5));
			builder.slowCallRateThreshold(100);
			builder.permittedNumberOfCallsInHalfOpenState(2);
			builder.slidingWindowType(COUNT_BASED);
		});
	}

}
