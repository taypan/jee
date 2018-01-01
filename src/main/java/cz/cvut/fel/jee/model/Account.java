package cz.cvut.fel.jee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="accounts")
public class Account implements Serializable,Identifiable{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    @NotNull
    private String salt;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @NotNull
    private String fullName;

    public Account(String username, String passwordHash, String salt, Set<Role> roles, String fullName) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.roles = roles;
        this.fullName = fullName;
    }

    public Account() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
