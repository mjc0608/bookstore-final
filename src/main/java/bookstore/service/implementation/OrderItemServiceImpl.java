package bookstore.service.implementation;

import bookstore.entity.Book;
import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.service.OrderItemService;
import bookstore.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Jachin on 6/9/16.
 */
public class OrderItemServiceImpl implements OrderItemService {

    public boolean addOrderItem(long orderID, long bookID, long quantity) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (quantity<=0) return true;
        try{
            session.beginTransaction();

            Order order = (Order)session.get(Order.class, orderID);
            Book b = (Book)session.get(Book.class, bookID);

            if (b==null) {
                session.getTransaction().commit();
                return false;
            }

            for (OrderItem item: order.getOrderItems()) {
                if (item.getBook().getId()==bookID) {
                    item.setQuantity(item.getQuantity()+quantity);
                    session.save(order);
                    session.getTransaction().commit();
                    return true;
                }
            }

            OrderItem item = new OrderItem();
            item.setBook((Book)session.get(Book.class, bookID));
            item.setQuantity(quantity);
            order.getOrderItems().add(item);

            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }

        return true;
    }

    public boolean modifyOrderItem(long orderID, long bookID, long quantity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Order order = (Order)session.get(Order.class, orderID);
            for (OrderItem item: order.getOrderItems()) {
                if (item.getBook().getId()==bookID) {
                    if (quantity>0) {
                        item.setQuantity(quantity);
                        break;
                    }
                    else {
                        order.getOrderItems().remove(item);
                    }
                }
            }

            session.save(order);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean removeOrderItem(long orderID, long bookID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Order order = (Order)session.get(Order.class, orderID);

            if (order==null) {
                session.getTransaction().commit();
                return false;
            }

            for (OrderItem item: order.getOrderItems()) {
                if (item.getBook().getId()==bookID) {
                    order.getOrderItems().remove(item);
                    break;
                }
            }
            session.save(order);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

}
