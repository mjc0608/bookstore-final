package bookstore.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by Jachin on 5/4/16.
 */
public class Order {
    private long id;
    private User user;
    private Set orderItems = new HashSet<OrderItem>();
    private Calendar time = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
    private double totalMoney;
    private long bookNumber;

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(long bookNumber) {
        this.bookNumber = bookNumber;
    }
}
