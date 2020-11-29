package es.vn.sb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITE_LIST = {
			// spring actuator
			"/actuator/health" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers(AUTH_WHITE_LIST).permitAll()
				.antMatchers("/actuator/**").hasRole("ENDPOINT_ADMIN")
				.anyRequest().permitAll().and().httpBasic();
				
	}

}
