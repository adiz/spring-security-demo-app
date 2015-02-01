package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.cti.ssa.twittboost.model.AppUser;

import java.util.Arrays;

/**
 * @author adrian.zamfirescu
 * @since 01/28/2015
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private AppUserService appUserService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser loggingUser = appUserService.getUserByUsername(username);
        return new User(loggingUser.getUsername(),
                        loggingUser.getPassword(),
                        Arrays.asList(new SimpleGrantedAuthority(loggingUser.getRole().getName())));

    }

}
