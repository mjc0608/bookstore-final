package bookstore.action.admin;

import java.text.SimpleDateFormat;
import java.util.*;

import bookstore.entity.*;

import bookstore.util.AdminUtil;
import org.hibernate.Session;

import bookstore.util.HibernateUtil;

import com.opensymphony.xwork2.ActionSupport;

public class AnalysisAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private Map<String, AnalysisPair> category = new HashMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> day = new TreeMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> month = new TreeMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> year = new TreeMap<String, AnalysisPair>();
    private long userID=-1;

    @Override
    public String execute() throws Exception {
        if (!AdminUtil.isAdmin()) return ERROR;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            List cateList = session.createQuery(
                    "select distinct category,count(category) from Book b group by category")
                    .list();
            List<Order> orders;
            if (userID<0) {
                orders = session.createQuery(
                    "select o from Order o order by time desc")
                    .list();
            }
            else {
                User u = (User)session.get(User.class, userID);
                if (u==null) {
                    session.getTransaction().commit();
                    return ERROR;
                }
                orders = session.createQuery(
                        "select o from Order o where o.user=:user order by time desc")
                        .setParameter("user", u)
                        .list();
            }

            session.getTransaction().commit();

            if (orders==null || orders.isEmpty()) return SUCCESS;


            for (int i=0; i<cateList.size();i++) {
                AnalysisPair emptyPair = new AnalysisPair();
                emptyPair.setQuantity(0);
                emptyPair.setMoney(0);
                Object[] obj = (Object[])cateList.get(i);

                emptyPair.setBookNumber((Long)obj[1]);
                category.put((String)obj[0],emptyPair);

            }

            for (Order order: orders) {
                for (OrderItem item: order.getOrderItems()) {
                    AnalysisPair apc=category.get(item.getBook().getCategory());
                    apc.addQuantity(item.getQuantity());
                    apc.addMoney(item.getQuantity()*item.getBook().getPrice());
                    order.setBookNumber(order.getBookNumber()+item.getQuantity());
                    order.setTotalMoney(order.getTotalMoney()+item.getQuantity()*item.getBook().getPrice());
                }
            }

            Calendar curr = (Calendar)orders.get(0).getTime().clone();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for (int i=0;i<31;i++) {
                AnalysisPair ap = new AnalysisPair();
                ap.setQuantity(0);
                ap.setMoney(0);

                day.put(df.format(curr.getTime()),ap);
                curr.add(Calendar.DAY_OF_MONTH,-1);
            }

            for (Order order: orders) {
                AnalysisPair ap = day.get(df.format(order.getTime().getTime()));
                if (ap==null) break;
                ap.addMoney(order.getTotalMoney());
                ap.addQuantity(order.getBookNumber());
            }

            curr = (Calendar)orders.get(0).getTime().clone();
            df = new SimpleDateFormat("yyyy-MM");

            for (int i=0;i<12;i++) {
                AnalysisPair ap = new AnalysisPair();
                ap.setMoney(0);
                ap.setQuantity(0);

                month.put(df.format(curr.getTime()),ap);
                curr.add(Calendar.MONTH,-1);
            }

            for (Order order: orders) {
                AnalysisPair ap = month.get(df.format(order.getTime().getTime()));
                if (ap==null) break;
                ap.addMoney(order.getTotalMoney());
                ap.addQuantity(order.getBookNumber());
            }

            df = new SimpleDateFormat("yyyy");

            for (Order order: orders) {
                AnalysisPair ap = year.get(df.format(order.getTime().getTime()));
                if (ap==null) {
                    ap = new AnalysisPair();

                    ap.setMoney(order.getTotalMoney());
                    ap.setQuantity(order.getBookNumber());

                    year.put(df.format(order.getTime().getTime()),ap);
                }
                else {
                    ap.addMoney(order.getTotalMoney());
                    ap.addQuantity(order.getBookNumber());
                }
            }

            for (Order order: orders) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return ERROR;
        }
        return SUCCESS;

    }

    public Map<String, AnalysisPair> getCategory() {
        return category;
    }

    public Map<String, AnalysisPair> getDay() {
        return day;
    }

    public Map<String, AnalysisPair> getMonth() {
        return month;
    }

    public Map<String, AnalysisPair> getYear() {
        return year;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void validate(){

    }

}