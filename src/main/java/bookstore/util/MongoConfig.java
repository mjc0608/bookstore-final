package bookstore.util;

/**
 * Created by Jachin on 6/11/16.
 */
public class MongoConfig {
    private String hostname;
    private int port;
    private String dbName;

    public int getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    public String getDbName() {
        return dbName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
