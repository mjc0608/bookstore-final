package bookstore.service;

/**
 * Created by Jachin on 6/9/16.
 */
public interface OrderItemService {

    public boolean addOrderItem(long orderID, long bookID, long quantity);
    public boolean modifyOrderItem(long orderID, long bookID, long quantity);
    public boolean removeOrderItem(long orderID, long bookID);

}