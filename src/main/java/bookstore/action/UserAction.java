package bookstore.action;

import bookstore.service.AnalysisService;
import bookstore.service.UserService;
import bookstore.service.implementation.UserServiceImpl;
import bookstore.entity.User;
import bookstore.util.UserUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class UserAction extends ActionSupport {
    private UserService userService;
    private AnalysisService analysisService;
    private User user;
    private List<User> users;
    private long id=-1;
    private String username;
    private String password;
    private File image;
    private double totalSpent;
    private long totalOrder;

    public String add() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidUser(user)) {
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
        System.out.print("! modify: ");
        System.out.println(user);

        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidUser(user)) {
            return INPUT;
        }
        else if (userService.modifyUser(user)) {
                return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String modifySelf() {
        if (!UserUtil.isLogin() || user.getId()!=UserUtil.getCurrentUser().getId()) {
            return ERROR;
        }
        else if (!isValidUser(user)) {
            return INPUT;
        }
        else if (userService.modifySelf(user)) {
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
            totalOrder = analysisService.getUserTotalOrder(id);
            totalSpent = analysisService.getUserTotalSpent(id);
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
        totalOrder = analysisService.getUserTotalOrder(user.getId());
        totalSpent = analysisService.getUserTotalSpent(user.getId());
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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public AnalysisService getAnalysisService() {
        return analysisService;
    }

    public void setAnalysisService(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalOrder(long totalOrder) {
        this.totalOrder = totalOrder;
    }

    public long getTotalOrder() {
        return totalOrder;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    private boolean isValidUser(User user) {
        System.out.println(user.toString());
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
