package cz.cvut.fel.jee.bean;

import cz.cvut.fel.jee.model.Account;
import lombok.Data;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;

@Data
@ManagedBean(name = "loginBean")
@RequestScoped
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
            context.getExternalContext().redirect("index.xhtml");
        } catch (ServletException | IOException  e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }

    public Account loggedAccount(){
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) request.getUserPrincipal();
            KeycloakSecurityContext con = (KeycloakSecurityContext) request.getSession().getAttribute(KeycloakSecurityContext.class.getName());
            Account loggedAccount = new Account(
                    hash(con.getToken().getId()),
                    con.getToken().getPreferredUsername(),
                    con.getToken().getEmail(),
                    new HashSet<>()
            );
            System.out.println("name: " + con.getToken().getName());
            System.out.println("email: " + con.getToken().getEmail());
            System.out.println("username: " + con.getToken().getPreferredUsername());
            System.out.println("roles: " + con.getToken().getRealmAccess().getRoles());

            return loggedAccount;
        }
        catch (Exception e) {
            return null;
        }
    }

    private static long hash(String string) {
        long h = 1125899906842597L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 31*h + string.charAt(i);
        }
        return h;
    }


}