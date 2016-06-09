package bookstore.util;


import bookstore.entity.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class AdminUtil {
    public static boolean isAdmin() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");
        if (user==null || user.getAdmin()==false) return false;
        else return true;
    }
}