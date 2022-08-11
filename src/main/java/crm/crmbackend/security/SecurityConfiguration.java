package crm.crmbackend.security;

import crm.crmbackend.security.jwt.JwtAuthenticationEntryPoint;
import crm.crmbackend.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtFilter jwtFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                    .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/subscribers/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers("/api/subscriptions/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers("/api/tickets/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers(HttpMethod.GET,"/api/cities").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers(HttpMethod.POST,"/api/cities").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/publications/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers(HttpMethod.POST,"/api/publications/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT,"/api/publications").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/subscription-types/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers(HttpMethod.POST,"/api/subscription-types/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT,"/api/subscription-types").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/users/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers(HttpMethod.POST,"/api/users/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT,"/api/users").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter(@Value("${frontend-address}") String frontendAddress) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.addAllowedOrigin(frontendAddress);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
