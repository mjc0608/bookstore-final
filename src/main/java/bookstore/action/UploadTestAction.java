package bookstore.action;

import bookstore.service.ImageService;
import bookstore.service.implementation.ImageServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

/**
 * Created by Jachin on 6/9/16.
 */
public class UploadTestAction extends ActionSupport {
    private File file;
    ImageService imageService = new ImageServiceImpl();

    @Override
    public String execute() throws Exception {
        String str;
        str = imageService.storeImage(file);

        if (str==null) {
            return ERROR;
        }
        else {
            System.out.println(str);
            return SUCCESS;
        }
    }

    public ImageService getImageService() {
        return imageService;
    }

    public File getFile() {
        return file;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setFile(File file) {
        this.file = file;
    }

//    public String getFileContentType() {
//        return fileContentType;
//    }
//
//    public String getFileFileName() {
//        return fileFileName;
//    }
//
//    public void setFileContentType(String fileContentType) {
//        this.fileContentType = fileContentType;
//    }
//
//    public void setFileFileName(String fileFileName) {
//        this.fileFileName = fileFileName;
//    }
}
