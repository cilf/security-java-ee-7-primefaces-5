package cz.cilf.security;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class PageBean implements Serializable {

    @Inject
    SecurityBean securityBean;

    private String name;
    private String password;

    @EJB
    private UserService userService;

    public void login() {
        if (StringUtils.isEmpty(name)) {
            error("name cannot be empty!");
            return;
        }

        if (StringUtils.isEmpty(password)) {
            error("password cannot be empty!");
            return;
        }

        User user = userService.findByName(name);

        if (user == null) {
            error("user not found");
            return;
        }
        // just a naive solution for storing and verifying password
        boolean correctPassword = password.equals(user.getPassword());
        if (!correctPassword) {
            error("wrong password");
        }

        securityBean.setLoggedInUser(user);
    }

    private void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }


    public void logout() {
        securityBean.logout();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
