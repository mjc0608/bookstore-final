package bookstore.entity;

import java.io.File;

/**
 * Created by Jachin on 5/4/16.
 */
public class User
{
    private long id;
    private String username;
    private String email;
    private String password;
    private String address;
    private boolean admin;
    private String imageID;
    private File image;

    public User() {
        admin=false;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String toString()
    {
        return "name: " + getUsername() +"; email: " + getEmail() + "; password: "
                + getPassword() + "; address: " + getPassword() + "; Admin: " + getAdmin();
    }
}
