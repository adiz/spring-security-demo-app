package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dao.AppUserRepository;
import ro.cti.ssa.twittboost.dao.RoleRepository;
import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.framework.IAppUserService;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.Role;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class AppUserService implements IAppUserService {

    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("messages");

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AppUser getUserByUsername(String username){

        return appUserRepository.findByUsername(username);

    }

    public List<AppUser> getAppUsers(){

        return appUserRepository.findApplicationUsers();

    }

    public AppUser registerAppUser(AppUser appUser) throws AppUserCreationException {

        AppUser existingUser = appUserRepository.findByUsername(appUser.getUsername());
        if (existingUser!=null)
            throw new AppUserCreationException(messagesResourceBundle.getString("error.user.exists"));

        Role userRole = roleRepository.findByName(appUser.getRole().getName());
        appUser.setRole(userRole);
        AppUser newUser = appUserRepository.save(appUser);
        if (newUser!=null)
            return newUser;
        else
            throw new AppUserCreationException(messagesResourceBundle.getString("error.user.creation.failed"));

    }

}
