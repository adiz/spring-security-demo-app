package ro.cti.ssa.twittboost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ro.cti.ssa.twittboost.service.AccesDeniedHandlingService;
import ro.cti.ssa.twittboost.service.AuthenticationFailureHandlerService;
import ro.cti.ssa.twittboost.service.AuthenticationSuccessHandlerService;
import ro.cti.ssa.twittboost.service.CustomUserDetailsService;

/**
 * @author adrian.zamfirescu
 * @since 17/01/2015
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationFailureHandlerService authenticationFailureHandlerService;
    @Autowired
    private AuthenticationSuccessHandlerService authenticationSuccessHandlerService;
    @Autowired
    private AccesDeniedHandlingService accesDeniedHandlingService;

    @Autowired
    public void configureSecurityApp(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService);

    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(accesDeniedHandlingService)
                .and()
            .formLogin()
                .loginPage("/login")
                .failureHandler(authenticationFailureHandlerService)
                .successHandler(authenticationSuccessHandlerService)
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
            .csrf()
                .disable();
    }

}
