package main.security;

import main.security.jwt.JwtSecurityConfigurer;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/registration/singing").permitAll()

                .antMatchers(HttpMethod.GET, "/library/clients").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/books").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/bookTypes").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/journal").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/clients/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/book/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/bookType/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/journal/{id}").hasRole("USER")

                .antMatchers(HttpMethod.POST, "/library/addClients").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/library/deleteClient/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/library/updateClient/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/library/addBook").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/library/deleteBook/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/library/updateBook/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/library/addBookType").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/library/deleteBookType/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/library/updateBookType/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}
