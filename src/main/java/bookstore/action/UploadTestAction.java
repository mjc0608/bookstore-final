package bookstore.action;

import bookstore.DAO.ImageDAO;
import bookstore.DAO.implementation.ImageDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Jachin on 6/9/16.
 */
public class UploadTestAction extends ActionSupport {
    private File file;
    private String fileContentType;
    private String fileFileName;
    ImageDAO imageDAO = new ImageDAOImpl();

    @Override
    public String execute() throws Exception {
        String str;
        str = imageDAO.storeImage(file);

        if (str==null) {
            return ERROR;
        }
        else {
            System.out.println(str);
            return SUCCESS;
        }
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    public File getFile() {
        return file;
    }

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
}
