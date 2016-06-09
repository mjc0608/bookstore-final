package bookstore.DAO;

import bookstore.entity.Order;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public interface OrderDAO {

    public boolean addOrderByID(long userID);
    public boolean addOrderByName(String username);
    public boolean removeOrder(long orderID);

    public Order getOrderByID(long orderID);
    public List<Order> getAllOrders();

}
