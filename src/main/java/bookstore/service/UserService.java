package bookstore.service;

import bookstore.entity.User;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public interface UserService {

    public boolean addUser(User user);
    public boolean removeUser(long userID);
    public boolean modifyUser(User user);

    public User getUserById(long id);
    public List<User> getAllUsers();

    public boolean loginUser(String username, String password);
    public boolean logoutUser();

    public User self();
    public boolean modifySelf(User user);
    public boolean register(User user);

}
