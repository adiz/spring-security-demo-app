package ro.cti.ssa.twittboost.model;

import javax.persistence.*;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@MappedSuperclass
public abstract class BaseEntity {

    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
