package ro.cti.ssa.twittboost.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 30/01/2014
 */
@Component
public class AuthenticationSuccessHandlerService implements AuthenticationSuccessHandler{

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // send the role of the authenticated user
        List<SimpleGrantedAuthority> oneItemAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(oneItemAuthorityList.get(0).getAuthority());
        response.getWriter().flush();

    }

}
