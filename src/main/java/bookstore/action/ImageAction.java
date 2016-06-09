package bookstore.action;

import bookstore.DAO.ImageDAO;
import bookstore.DAO.implementation.ImageDAOImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Jachin on 6/9/16.
 */
public class ImageAction extends ActionSupport{
    private String type = "image/jpeg";
    private InputStream imageStream;
    private String id;
    ImageDAO imageDAO = new ImageDAOImpl();

    @Override
    public String execute() throws Exception {
        if (id==null || id.equals("")) {
            return ERROR;
        }

        byte[] fbytes = imageDAO.getImage(id);

        if (fbytes==null) {
            return ERROR;
        }

        imageStream = new ByteArrayInputStream(fbytes);
        return SUCCESS;
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
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

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public void setType(String type) {
        this.type = type;
    }
}
