package ro.cti.ssa.twittboost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.cti.ssa.twittboost.exception.ControllerException;
import ro.cti.ssa.twittboost.exception.RoleCreationException;
import ro.cti.ssa.twittboost.framework.IRoleService;
import ro.cti.ssa.twittboost.model.Role;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 01/31/2014
 */
@Controller
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping
    @ResponseBody
    public List<Role> getRoles(){

        return roleService.getRoles();

    }

    @RequestMapping(value = "/roleInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getRoleInfo(@RequestParam String roleName) throws ControllerException{

        try{
            return roleService.getRoleInfo(roleName);
        } catch (RoleCreationException e) {
            throw new ControllerException(e.getMessage());
        }

    }

}
