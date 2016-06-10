package bookstore.action;

import bookstore.service.implementation.CartServiceImpl;
import bookstore.entity.OrderItem;
import bookstore.service.CartService;
import com.opensymphony.xwork2.ActionSupport;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Created by Jachin on 6/9/16.
 */
public class CartAction extends ActionSupport {
    CartService cartService;
    private long bookID;
    private long quantity;
    private Set<OrderItem> items;
    private double totalPrice;

    public String add() {
        if (cartService.addCart(bookID, quantity)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String remove() {
        if (cartService.removeCart(bookID)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String modify() {
        if (cartService.modifyCart(bookID, quantity)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String query() {
        items = cartService.getAllItems();
        totalPrice=0;
        for (OrderItem item: items) {
            totalPrice+=(item.getQuantity()*item.getBook().getPrice());
        }
        return SUCCESS;
    }

    public String submit() {
        if (cartService.submitCart()) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public CartService getCartService() {
        return cartService;
    }

    public long getBookID() {
        return bookID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
