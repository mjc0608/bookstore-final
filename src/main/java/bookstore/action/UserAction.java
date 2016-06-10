package bookstore.action;

import bookstore.service.UserService;
import bookstore.service.implementation.UserServiceImpl;
import bookstore.entity.User;
import bookstore.util.UserUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class UserAction extends ActionSupport {
    private UserService userService;
    private User user;
    private List<User> users;
    private long id=-1;
    private String username;
    private String password;

    public String add() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (isValidUser(user)) {
            return INPUT;
        }
        else if (userService.addUser(user)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String modify() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (isValidUser(user)) {
            return INPUT;
        }
        else if (userService.modifyUser(user)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String remove() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (id<0) {
            return ERROR;
        }
        else if (userService.removeUser(id)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String query() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        users = userService.getAllUsers();
        return SUCCESS;
    }

    public String info() {
        if (!UserUtil.isAdmin() && UserUtil.getCurrentUser().getId()!=id) {
            return ERROR;
        }

        user = userService.getUserById(id);
        if (isValidUser(user)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String login() {
        if (userService.loginUser(username, password)) {
            return SUCCESS;
        }
        else {
            return INPUT;
        }
    }

    public String logout() {
        if (userService.logoutUser()) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String myInfo() {
        if (!UserUtil.isLogin()) {
            return ERROR;
        }

        user = UserUtil.getCurrentUser();
        return SUCCESS;
    }

    public String register() {
        if (!isValidUser(user)) {
            return ERROR;
        }
        if (userService.register(user)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public long getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public UserService getUserService() {
        return userService;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private boolean isValidUser(User user) {
        if (user==null) {
            return false;
        }

        if (user.getEmail()==null || user.getEmail().equals("")) {
            return false;
        }
        if (user.getUsername()==null || user.getUsername().equals("")) {
            return false;
        }
        if (user.getPassword()==null || user.getPassword().equals("")) {
            return false;
        }

        return true;
    }
}
