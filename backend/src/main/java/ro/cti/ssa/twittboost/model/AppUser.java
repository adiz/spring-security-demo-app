package ro.cti.ssa.twittboost.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Entity
@Table(name = "APPUSER")
public class AppUser extends BaseEntity{

    private String username;
    private String password;
    private Role role;

    @NotNull(message = "Username cannot be null!")
    @Size(min = 3, message = "Username must have at least 3 characters!")
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password cannot be null!")
    @Size(min = 4, message = "Password must have at least 4 characters!")
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
