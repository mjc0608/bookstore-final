package bookstore.action;

import bookstore.service.implementation.OrderServiceImpl;
import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.service.OrderService;
import bookstore.util.UserUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by Jachin on 6/9/16.
 */
public class OrderAction extends ActionSupport {
    private OrderService orderService;
    private Order order;
    private Set<OrderItem> items;
    private List<Order> orders;
    private long userID=-1;
    private String username;
    private long id=-1;
    private double totalPrice=0;
    private long status=0;

    public String add() {
        String rs;
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        if (username==null || username.equals("")) {
            if (orderService.addOrderByID(userID)) {
                rs = SUCCESS;
            }
            else {
                rs = ERROR;
            }
        }
        else {
            if (orderService.addOrderByName(username)) {
                rs = SUCCESS;
            }
            else {
                rs = ERROR;
            }
        }

        return rs;
    }

    public String remove() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }
        else if (id<0) {
            return INPUT;
        }
        else if (orderService.removeOrder(id)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String info() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        order = orderService.getOrderByID(id);
        if (!isValidOrder(order)) {
            return ERROR;
        }
        else {
            items = order.getOrderItems();
            totalPrice=0;
            for (OrderItem item: items) {
                totalPrice+=item.getQuantity()*item.getBook().getPrice();
            }
            return SUCCESS;
        }
    }

    public String query() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        orders = orderService.getAllOrders();
        return SUCCESS;

    }

    public String setStatus() {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        if (status==0) {
            if (orderService.unfinishOrder(id)) {
                return SUCCESS;
            }
            else {
                return ERROR;
            }
        }
        else {
            if (orderService.finishOrder(id)) {
                return SUCCESS;
            }
            else {
                return ERROR;
            }
        }
    }

    public String queryMyOrder() {
        orders = orderService.getMyOrders();
        return SUCCESS;
    }

    public String myOrderInfo() {
        order = orderService.getMyOrder(id);
        if (!isValidOrder(order)) {
            return ERROR;
        }
        else {
            items = order.getOrderItems();
            totalPrice=0;
            for (OrderItem item: items) {
                totalPrice+=item.getQuantity()*item.getBook().getPrice();
            }
            return SUCCESS;
        }

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public double getTotalPrice() {
        BigDecimal b = new BigDecimal(totalPrice);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return userID;
    }

    public Order getOrder() {
        return order;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    private boolean isValidOrder(Order order) {
        if (order==null) {
            return false;
        }
        if (order.getUser()==null) {
            return false;
        }
        return true;
    }
}
