package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dao.RoleRepository;
import ro.cti.ssa.twittboost.exception.RoleCreationException;
import ro.cti.ssa.twittboost.framework.IRoleService;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.Role;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class RoleService implements IRoleService{

    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("messages");

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {

        List<Role> roles = roleRepository.getRoles();
        // for security purposes, remove confidential "info"
        for (Role role : roles)
            role.setInfo(null);

        return roles;

    }

    @Override
    public Role addRole(Role newRole) throws RoleCreationException{

        Role existingRole = roleRepository.findByName(newRole.getName());
        if (existingRole!=null)
            throw new RoleCreationException(messagesResourceBundle.getString("error.role.exists"));

        Role role = roleRepository.save(newRole);
        if (role!=null)
            return role;
        else
            throw new RoleCreationException(messagesResourceBundle.getString("error.role.creation.failed"));

    }

    @Override
    public String getRoleInfo(String roleName) throws RoleCreationException{

        Role role = roleRepository.findByName(roleName);

        // if roles don't match, assume security breach (unless admin)
        String currentRole = SecurityContextHolder.getContext().getAuthentication()
                                                  .getAuthorities().iterator().next().getAuthority();
        if (!currentRole.equals("Admin") && !roleName.equals(currentRole))
            throw new RoleCreationException(role.getMessageForScam());

        return role.getInfo();
    }

}
