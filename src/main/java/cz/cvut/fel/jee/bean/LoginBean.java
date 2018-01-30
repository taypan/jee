package cz.cvut.fel.jee.bean;

import lombok.Data;
import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Data
@ManagedBean(name = "loginBean")
public class LoginBean {
    private String username;
    private String password;

    @Inject
    private FacesContext context;

    public String login () {
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
        return "index";
    }

    public void logout() {
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }

    public String loggedUser(){
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) request.getUserPrincipal();
            KeycloakSecurityContext con = (KeycloakSecurityContext) request.getSession().getAttribute(KeycloakSecurityContext.class.getName());
            System.out.println(con.getToken().getName());
            System.out.println(con.getToken().getEmail());
            System.out.println(con.getToken().getPreferredUsername());
            System.out.println(con.getToken().getRealmAccess().getRoles());

            return principal==null ? "NOT LOGGED IN" : principal.getName();
        }
        catch (Exception e) {
            return "NOT LOGGED IN";
        }

    }

}