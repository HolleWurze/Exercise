package xyz.ITMO.Exercise.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String[] SWAGGER_ENDPOINT = {
            "/**swagger**/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    private static final String[] WHITELIST = {
            "/h2-console/**/**",
            "/docs/**",
            "/csrf/**",
            "/webjars/**"
    };

    @Order(1)
    @Configuration
    public class SwaggerConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("exerciseadmin")
                    .password(passwordEncoder().encode("exerciseadmin"))
                    .authorities("ROLE_ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers().antMatchers(SWAGGER_ENDPOINT)
                    .and()
                    .authorizeRequests().anyRequest().hasAuthority("ROLE_ADMIN")
                    .and()
                    .httpBasic();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Order(2)
    @Configuration
    @RequiredArgsConstructor
    public static class RestConfiguration extends WebSecurityConfigurerAdapter {

//        @Bean
//        @Override
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/houses").permitAll()
                    .antMatchers(HttpMethod.POST, "/houses").hasRole("USER")
                    .anyRequest().authenticated();

        }

        @Override
        public void configure(WebSecurity web) {
            web
                    .ignoring()
                    .antMatchers(WHITELIST)
                    .antMatchers();
        }
    }
}
