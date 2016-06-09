package bookstore.action;

import bookstore.DAO.OrderItemDAO;
import bookstore.DAO.implementation.OrderItemDAOImpl;
import bookstore.util.AdminUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Jachin on 6/9/16.
 */
public class OrderItemAction extends ActionSupport {
    OrderItemDAO orderItemService = new OrderItemDAOImpl();
    long orderID=-1;
    long bookID=-1;
    long quantity=-1;

    public String add() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidOrderItem(orderID, bookID)) {
            return INPUT;
        }
        else if (orderItemService.addOrderItem(orderID, bookID, quantity)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String modify() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidOrderItem(orderID, bookID)) {
            return INPUT;
        }
        else if (orderItemService.modifyOrderItem(orderID, bookID, quantity)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String remove() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (orderItemService.removeOrderItem(orderID, bookID)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public long getBookID() {
        return bookID;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getQuantity() {
        return quantity;
    }

    public OrderItemDAO getOrderItemService() {
        return orderItemService;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public void setOrderItemService(OrderItemDAO orderItemService) {
        this.orderItemService = orderItemService;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    private boolean isValidOrderItem(long orderID, long bookID) {
        if (orderID<0 || bookID<0) {
            return false;
        }
        return true;
    }
}
