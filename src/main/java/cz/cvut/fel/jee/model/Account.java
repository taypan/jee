package cz.cvut.fel.jee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="acounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    @NotNull
    private String salt;

    private Set<Role> roles;

    @NotNull
    private String fullName;
}
