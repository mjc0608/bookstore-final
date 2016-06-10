package bookstore.action;

import bookstore.service.ImageService;
import bookstore.service.implementation.ImageServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Jachin on 6/9/16.
 */
public class ImageAction extends ActionSupport{
    private String type = "image/jpeg";
    private InputStream imageStream;
    private String id;
    ImageService imageService;

    @Override
    public String execute() throws Exception {
        if (id==null || id.equals("")) {
            return ERROR;
        }

        byte[] fbytes = imageService.getImage(id);

        if (fbytes==null) {
            return ERROR;
        }

        imageStream = new ByteArrayInputStream(fbytes);
        return SUCCESS;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public void setType(String type) {
        this.type = type;
    }
}
