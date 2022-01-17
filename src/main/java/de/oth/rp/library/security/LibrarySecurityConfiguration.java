package de.oth.rp.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LibrarySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LibrarySecurityUtilities librarySecurityUtilities;

    private BCryptPasswordEncoder passwordEncoder(){
        return librarySecurityUtilities.passwordEncoder();
    }

    private static final String[] ALLOW_ACCESS_WITHOUT_AUTHENTICATION = {
          "/search", "/icon/**", "/css/**", "/images/**", "/static/**", "/styles/css/**", "/templates/**","/fonts/**", "/", "/books", "/login", "/forgotPassword", "/register", "/static/isbn_numbers/**"
    };

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http.authorizeRequests()
                    .antMatchers(ALLOW_ACCESS_WITHOUT_AUTHENTICATION)
                    .permitAll().anyRequest().authenticated();
            http
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/?logout")
                    .deleteCookies("remember-me")
                    .permitAll()
                    .and()
                    .rememberMe();

        }catch (Exception ex){
            System.out.println(ex.getStackTrace());
            System.out.println(ex);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}
