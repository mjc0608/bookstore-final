package bookstore.service.implementation;

import bookstore.entity.User;
import bookstore.service.UserService;
import bookstore.util.HibernateUtil;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class UserServiceImpl implements UserService {

    public boolean addUser(User user) {
        if (user==null) return false;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User utest = (User)session.createQuery(
                    "from User u where u.username=:username")
                    .setParameter("username",user.getUsername())
                    .uniqueResult();

            if (utest!=null) {
                session.getTransaction().commit();
                return false;
            }

            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean removeUser(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User user=(User)session.get(User.class, userID);
            if (user!=null) session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean modifyUser(User user) {
        if (user==null) return false;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();

            System.out.println(user.toString());
            session.update(user);
            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }

        return true;

    }

    public User getUserById(long id) {
        User user;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            user = (User)session.get(User.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            users = session.createQuery(
                    "select u from User u").list();


            for (User u: users) {
                System.out.println(u.toString());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return users;
    }

    public boolean loginUser(String username, String password) {
        if (username==null || password==null) return false;

        User user;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            user = (User) session.createQuery(
                    "select u from User u where username = :username and password = :password").
                    setParameter("username", username).
                    setParameter("password", password).uniqueResult();

            if (user==null) {
                session.getTransaction().commit();
                return false;
            }
            else {
                HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
                httpSession.setAttribute("loginUser", user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean logoutUser() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");

        if (user!=null) {
            httpSession.invalidate();
        }

        return true;
    }

    public User self() {
        HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
        User user = (User)httpSession.getAttribute("loginUser");
        return user;
    }

    public boolean register(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User utest = (User)session.createQuery(
                    "from User u where u.username=:username")
                    .setParameter("username",user.getUsername())
                    .uniqueResult();

            if (utest!=null) {
                session.getTransaction().commit();
                return false;
            }

            user.setAdmin(false);
            session.save(user);

            session.refresh(user);
            session.getTransaction().commit();

            HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
            httpSession.setAttribute("loginUser", user);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

}
