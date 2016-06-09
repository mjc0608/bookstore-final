package bookstore.service;

import bookstore.entity.OrderItem;

import java.util.Set;


/**
 * Created by Jachin on 6/9/16.
 */
public interface CartService {
    public boolean addCart(long bookID, long quantity);
    public boolean removeCart(long bookID);
    public boolean modifyCart(long bookID, long quantity);

    public Set<OrderItem> getAllItems();

    public boolean submitCart();

}
