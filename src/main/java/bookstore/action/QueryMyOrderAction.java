package bookstore.action;

import bookstore.entity.OrderItem;
import bookstore.entity.User;
import bookstore.entity.Order;
import bookstore.util.HibernateUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QueryMyOrderAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private List<Order> orders = new LinkedList<Order>();

    @Override
    public String execute() throws Exception {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);

        try {
            session.beginTransaction();
            User user = (User)httpSession.getAttribute("loginUser");

            orders = session.createQuery(
                    "from Order where user=:user")
                    .setParameter("user",user).list();

            if (orders==null || orders.isEmpty()) return SUCCESS;

            for (Order order: orders) {
                double totalPrice=0;
                long bookNumber=0;
                for (OrderItem item: order.getOrderItems()) {
                    if (item.getBook()!=null)
                        totalPrice+=item.getBook().getPrice()*item.getQuantity();
                    bookNumber+=item.getQuantity();

                }
                order.setTotalMoney(totalPrice);
                order.setBookNumber(bookNumber);
            }

            Collections.reverse(orders);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return ERROR;
        }
        return SUCCESS;

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void validate(){

    }

}