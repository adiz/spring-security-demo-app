package ro.cti.ssa.twittboost.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author adrian.zamfirescu
 * @since 22/01/2014
 */
@Component
public class AuthenticationFailureHandlerService extends SimpleUrlAuthenticationFailureHandler {

    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("messages");

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().print(messagesResourceBundle.getString("authentication.failed"));
        response.getWriter().flush();

    }

}