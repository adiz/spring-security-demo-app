package ro.cti.ssa.twittboost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity{

    private String name;
    private String info;
    private String messageForScam;

    @NotNull(message = "Role name cannot be null!")
    @Size(min = 3, message = "Role name must have at least 3 characters!")
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Info text cannot be null!")
    @Size(min = 10, message = "Provide at least 10 characters to your role information!")
    @Column(name = "INFO")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @NotNull(message = "Message for scam cannot be null!")
    @Size(min = 10, message = "Provide at least 10 characters to your message for scams!")
    @Column(name = "MESSAGE_FOR_SCAM")
    public String getMessageForScam() {
        return messageForScam;
    }

    public void setMessageForScam(String messageForScam) {
        this.messageForScam = messageForScam;
    }

}
