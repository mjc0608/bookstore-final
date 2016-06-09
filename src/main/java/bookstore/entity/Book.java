package bookstore.entity;

import java.io.File;

/**
 * Created by Jachin on 5/4/16.
 */
public class Book {
    private long id;
    private String name;
    private String description;
    private String photoUrl;
    private String category;
    private long price;
    private File image;

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getPrice() {
        return (double)price/100;
    }

    public void setPrice(double price) {
        this.price = (long)(price*100);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
