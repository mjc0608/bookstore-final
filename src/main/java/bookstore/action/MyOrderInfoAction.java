package bookstore.action;

import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.entity.User;
import bookstore.util.HibernateUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class MyOrderInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private long id;
    private Order order;
    private Set<OrderItem> items;
    private double totalPrice;

    @Override
    public String execute() throws Exception {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            order = (Order)session.get(Order.class, id);

            if (order.getUser().getId()!=user.getId()) return ERROR;

            items = order.getOrderItems();

            totalPrice=0;
            for (OrderItem item: items) {
                totalPrice+=item.getQuantity()*item.getBook().getPrice();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return ERROR;
        }

        return SUCCESS;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void validate(){

    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}