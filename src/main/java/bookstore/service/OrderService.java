package bookstore.service;

import bookstore.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public interface OrderService {

    public boolean addOrderByID(long userID);
    public boolean addOrderByName(String username);
    public boolean removeOrder(long orderID);

    public Order getOrderByID(long orderID);
    public List<Order> getAllOrders();

    public boolean finishOrder(long orderID);
    public boolean unfinishOrder(long orderID);

    public List<Order> getMyOrders();
    public Order getMyOrder(long id);

}
