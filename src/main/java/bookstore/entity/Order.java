package bookstore.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.text.DateFormat;

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
    private int status=0;

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
        BigDecimal b = new BigDecimal(totalMoney);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
