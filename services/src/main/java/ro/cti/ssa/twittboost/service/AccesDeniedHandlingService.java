package ro.cti.ssa.twittboost.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AccesDeniedHandlingService implements AccessDeniedHandler{

    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("messages");

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String errorMessage;

        switch (request.getServletPath()){
            case "/admin/users" : errorMessage = messagesResourceBundle.getString("authorization.admin.users.list"); break;
            case "/admin/register" : errorMessage = messagesResourceBundle.getString("authorization.admin.user.register"); break;
            case "/admin/role" : errorMessage = messagesResourceBundle.getString("authorization.admin.role.register"); break;
            default: errorMessage = messagesResourceBundle.getString("authorization.failed.default");
        }

        response.getWriter().print(errorMessage);
        response.getWriter().flush();

    }

}
