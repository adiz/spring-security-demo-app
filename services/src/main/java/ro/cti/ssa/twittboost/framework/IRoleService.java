package ro.cti.ssa.twittboost.framework;

import org.springframework.data.jpa.repository.Query;
import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.exception.RoleCreationException;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.Role;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public interface IRoleService {

    List<Role> getRoles();

    Role addRole(Role newRole) throws RoleCreationException;

    String getRoleInfo(String roleName) throws RoleCreationException;

}
