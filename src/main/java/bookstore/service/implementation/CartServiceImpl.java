package bookstore.service.implementation;

import bookstore.entity.Book;
import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.entity.User;
import bookstore.service.CartService;
import bookstore.util.HibernateUtil;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jachin on 6/9/16.
 */
public class CartServiceImpl implements CartService{

    public boolean addCart(long bookID, long quantity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        if (httpSession.getAttribute("loginUser")==null) {
            return false;
        }

        Set<OrderItem> items = (Set)httpSession.getAttribute("cart");
        if (items==null) {
            items = new HashSet<OrderItem>();
        }

        boolean isAdded=false;
        for (OrderItem item: items) {
            if (item.getBook().getId()==bookID) {
                item.setQuantity(item.getQuantity()+quantity);
                isAdded=true;
                break;
            }
        }

        if (isAdded==false) {
            OrderItem newItem = new OrderItem();
            try {
                session.beginTransaction();
                Book newItemBook = (Book)session.get(Book.class, bookID);
                session.getTransaction().commit();

                newItem.setBook(newItemBook);
                newItem.setQuantity(quantity);
                items.add(newItem);
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }

        httpSession.setAttribute("cart", items);

        return true;
    }

    public boolean removeCart(long bookID) {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        if (httpSession.getAttribute("loginUser")==null) {
            return false;
        }

        Set<OrderItem> items = (Set)httpSession.getAttribute("cart");
        if (items==null) {
            return false;
        }

        for (OrderItem item: items) {
            if (item.getBook().getId()==bookID) {
                items.remove(item);
                break;
            }
        }

        httpSession.setAttribute("cart", items);

        return true;
    }

    public boolean modifyCart(long bookID, long quantity) {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        if (httpSession.getAttribute("loginUser")==null) {
            return false;
        }

        Set<OrderItem> items = (Set)httpSession.getAttribute("cart");
        if (items==null) {
            return false;
        }

        for (OrderItem item: items) {
            if (item.getBook().getId()==bookID) {
                if (quantity>0) {
                    item.setQuantity(quantity);
                    break;
                }
                else {
                    items.remove(item);
                }
            }
        }

        httpSession.setAttribute("cart", items);

        return true;
    }

    public Set<OrderItem> getAllItems() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        if (httpSession.getAttribute("loginUser")==null) {
            return null;
        }

        Set<OrderItem> items = (Set)httpSession.getAttribute("cart");
        if (items==null) {
            items = new HashSet<OrderItem>();
        }

        return items;
    }

    public boolean submitCart() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);

        User user=(User)httpSession.getAttribute("loginUser");
        if (user==null) {
            return false;
        }

        Set<OrderItem> items = (Set)httpSession.getAttribute("cart");
        if (items==null) {
            return true;
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(items);

        try {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        httpSession.removeAttribute("cart");

        return true;
    }

}
