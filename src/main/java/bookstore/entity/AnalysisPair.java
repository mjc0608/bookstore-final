package bookstore.entity;

/**
 * Created by Jachin on 5/10/16.
 */
public class AnalysisPair {
    private long bookNumber;
    private long quantity;
    private double money;

    public double getMoney() {
        return money;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void addMoney(double money) {
        this.money+=money;
    }

    public void addQuantity(long quantity) {
        this.quantity+=quantity;
    }

    public void setBookNumber(long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public long getBookNumber() {
        return bookNumber;
    }

    public void addBookNumber(long bookNumber) {
        this.bookNumber+=bookNumber;
    }
}
