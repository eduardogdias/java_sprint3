package br.com.fiap.sprint3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http       		
        		.authorizeHttpRequests(       				
        				authorizeConfig -> {    					
        					authorizeConfig
                            	.requestMatchers(HttpMethod.GET, "/web/*/listar").hasAnyRole("ADMIN", "USER");

        					authorizeConfig
	                            .requestMatchers("/web/*/cadastrar").hasRole("ADMIN")
	                            .requestMatchers("/web/*/salvar").hasRole("ADMIN")
	                            .requestMatchers("/web/*/editar/*").hasRole("ADMIN")
	                            .requestMatchers("/web/*/excluir/*").hasRole("ADMIN");
        					
        					//authorizeConfig.requestMatchers("/logout").permitAll();
        					authorizeConfig.anyRequest().authenticated();
        					
        				})
        		.formLogin(form -> form
                        .defaultSuccessUrl("/web/sensores/listar", true) 
                        .permitAll()      
                )
        		.exceptionHandling(exception -> exception
                        .accessDeniedPage("/403")
                )
        		.logout(Customizer.withDefaults())
        		.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // senha salva em texto puro
	}

}
