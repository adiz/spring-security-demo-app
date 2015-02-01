package ro.cti.ssa.twittboost.framework;

import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.model.AppUser;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public interface IAppUserService {

    AppUser getUserByUsername(String username);

    List<AppUser> getAppUsers();

    AppUser registerAppUser(AppUser appUser) throws AppUserCreationException;

}
