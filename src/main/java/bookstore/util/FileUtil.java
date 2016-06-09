package bookstore.util;

import java.io.*;

/**
 * Created by Jachin on 6/9/16.
 */
public class FileUtil {
    public static byte[] fileToBytes(File file) {
        byte[] buffer=null;
        FileInputStream fs;
        try {
            fs = new FileInputStream(file);
            try {
                buffer = new byte[fs.available()];
                fs.read(buffer);
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
                buffer=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            buffer=null;
        }

        return buffer;
    }
}
