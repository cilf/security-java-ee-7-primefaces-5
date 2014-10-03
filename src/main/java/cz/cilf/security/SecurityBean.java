package cz.cilf.security;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SecurityBean implements Serializable {
    private User loggedInUser;

    public Boolean isGuest() {
        return loggedInUser == null;
    }

    public Boolean isUser() {
        return loggedInUser != null;
    }

    public Boolean isAdmin() {
        if (isGuest()) {
            return false;
        }
        // this solution will be replaced by one obtaining the dynamic profile address from GET
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId.contains(loggedInUser.getPage());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public void logout() {
        loggedInUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
