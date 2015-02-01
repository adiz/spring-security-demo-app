package ro.cti.ssa.twittboost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.cti.ssa.twittboost.model.Role;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 31/01/2014
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query
    Role findByName(String roleName);

    @Query(value = "SELECT role FROM Role role WHERE role.name!='Admin'")
    List<Role> getRoles();

}
