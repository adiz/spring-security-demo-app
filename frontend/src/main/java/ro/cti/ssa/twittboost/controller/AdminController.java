package ro.cti.ssa.twittboost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.exception.ControllerException;
import ro.cti.ssa.twittboost.exception.RoleCreationException;
import ro.cti.ssa.twittboost.framework.IAppUserService;
import ro.cti.ssa.twittboost.framework.IRoleService;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.Role;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 01/31/2014
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<AppUser> getApplicationUsers(){

        return appUserService.getAppUsers();

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public AppUser registerAppUser(@Valid @RequestBody AppUser appUser, BindingResult result) throws ControllerException {

        if(result.hasErrors()){
            throw new ControllerException(result.getFieldErrors());
        }

        try {
            return appUserService.registerAppUser(appUser);
        } catch (AppUserCreationException e) {
            throw new ControllerException(e.getMessage());
        }

    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Role addRole(@Valid @RequestBody Role role, BindingResult result) throws ControllerException {

        if(result.hasErrors()){
            throw new ControllerException(result.getFieldErrors());
        }

        try {
            return roleService.addRole(role);
        } catch (RoleCreationException e) {
            throw new ControllerException(e.getMessage());
        }

    }

}

