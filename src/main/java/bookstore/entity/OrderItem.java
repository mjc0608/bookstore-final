package bookstore.entity;

/**
 * Created by Jachin on 5/4/16.
 */
public class OrderItem{
    private Book book;
    private long quantity;

    public OrderItem() {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
