package bookstore.util;

public class MessageStore {

    private String message;
    private long userid;

    public String getMessage() {
        //return entity.getMessage();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

}