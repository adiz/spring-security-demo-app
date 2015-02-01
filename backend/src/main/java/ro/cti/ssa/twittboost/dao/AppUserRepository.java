package ro.cti.ssa.twittboost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.cti.ssa.twittboost.model.AppUser;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer>{

    @Query
    AppUser findByUsername(String username);

    @Query(value = "SELECT user FROM AppUser user WHERE user.role.name!='Admin'")
    List<AppUser> findApplicationUsers();

}
