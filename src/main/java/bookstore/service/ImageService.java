package bookstore.service;

import java.io.File;

/**
 * Created by Jachin on 6/9/16.
 */
public interface ImageService {
    public String storeImage(File file);
    public String storeImage(byte[] fbytes);
    public byte[] getImage(String imageID);
    public boolean removeImage(String imageID);
}
