package bookstore.util;


import bookstore.entity.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static boolean isAdmin() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");
        if (user==null || user.getAdmin()==false) return false;
        else return true;
    }

    public static User getCurrentUser() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");
        return user;
    }

    public static void setCurrentUser(User user) {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        httpSession.removeAttribute("loginUser");
        httpSession.setAttribute("loginUser", user);
    }

    public static boolean isLogin() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");
        if (user==null) {
            return false;
        }
        else {
            return true;
        }
    }
}
