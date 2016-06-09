package bookstore.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import bookstore.entity.User;
import bookstore.util.HibernateUtil;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;

public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private User user;
    private String passwordConfirmation;


    @Override
    public String execute() throws Exception {
        if (user==null) return INPUT;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            User utest = (User)session.createQuery(
                    "from User u where u.username=:username")
                    .setParameter("username",user.getUsername())
                    .uniqueResult();

            if (utest!=null) {
                session.getTransaction().commit();
                return INPUT;
            }

            user.setAdmin(false);
            session.save(user);


    //        System.out.println(user.toString());

            session.refresh(user);
            session.getTransaction().commit();

            HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
            httpSession.setAttribute("loginUser", user);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return ERROR;
        }
        return SUCCESS;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void validate(){

        if ( user!=null && user.getUsername().length() == 0 ){

            addFieldError( "user.username", "username is required." );

        }


        if ( user!=null && user.getEmail().length() == 0 ){

            addFieldError( "user.email", "Email is required." );

        }

        if (user!=null && !user.getPassword().equals(getPasswordConfirmation())) {
            addFieldError("user.password", "password does not match");
        }


    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}