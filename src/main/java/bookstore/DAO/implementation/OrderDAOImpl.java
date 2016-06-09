package bookstore.DAO.implementation;

import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.entity.User;
import bookstore.DAO.OrderDAO;
import bookstore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class OrderDAOImpl implements OrderDAO {

    public boolean addOrderByID(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User user = (User) session.get(User.class, userID);

            if (user==null) {
                session.getTransaction().commit();
                return false;
            }
            Order order = new Order();
            order.setUser(user);

            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }

        return true;
    }

    public boolean addOrderByName(String username) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User user;
            if (username==null || username.equals("")) {
                session.getTransaction().commit();
                return false;
            }
            else {
                user = (User) session.createQuery(
                        "from User where username=:username")
                        .setParameter("username",username)
                        .uniqueResult();
            }
            if (user==null) {
                session.getTransaction().commit();
                return false;
            }
            Order order = new Order();
            order.setUser(user);

            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }

        return true;
    }

    public boolean removeOrder(long orderID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Order order = (Order)session.get(Order.class, orderID);
            if (order!=null) session.delete(order);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public Order getOrderByID(long orderID) {
        Order order;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            order = (Order)session.get(Order.class, orderID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> orders;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            orders = session.createQuery(
                    "select o from Order o").list();

            if (orders==null || orders.isEmpty()) return null;

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
            return null;
        }
        return orders;
    }

}
