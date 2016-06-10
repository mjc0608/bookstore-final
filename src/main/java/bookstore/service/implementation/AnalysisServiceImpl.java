package bookstore.service.implementation;

import bookstore.entity.AnalysisPair;
import bookstore.entity.Order;
import bookstore.entity.OrderItem;
import bookstore.entity.User;
import bookstore.service.AnalysisService;
import bookstore.util.HibernateUtil;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jachin on 6/10/16.
 */
public class AnalysisServiceImpl implements AnalysisService {

    private Map<String, AnalysisPair> category = new HashMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> day = new TreeMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> month = new TreeMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> year = new TreeMap<String, AnalysisPair>();
    private Map<String, AnalysisPair> limit = new TreeMap<String, AnalysisPair>();
    List cateList = null;
    private List<Order> orders;

    public boolean init() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            cateList = session.createQuery(
                    "select distinct category,count(category) from Book b group by category")
                    .list();
            orders = session.createQuery(
                    "select o from Order o order by time desc")
                    .list();

            session.getTransaction().commit();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean init(long userID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            cateList = session.createQuery(
                    "select distinct category,count(category) from Book b group by category")
                    .list();
            User u = (User)session.get(User.class, userID);
            if (u==null) {
                session.getTransaction().commit();
                return false;
            }
            orders = session.createQuery(
                    "select o from Order o where o.user=:user order by time desc")
                    .setParameter("user", u)
                    .list();

            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean init(String startTime, String endTime) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        int startYear = Integer.parseInt(startTime.substring(0, 4));
        int startMonth = Integer.parseInt(startTime.substring(5, 7));
        int startDay = Integer.parseInt(startTime.substring(8, 10));
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startYear, startMonth-1, startDay, 23, 59, 59);

        int endYear = Integer.parseInt(endTime.substring(0, 4));
        int endMonth = Integer.parseInt(endTime.substring(5, 7));
        int endDay = Integer.parseInt(endTime.substring(8, 10));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(endYear, endMonth-1, endDay, 23, 59, 59);

        try {
            session.beginTransaction();

            cateList = session.createQuery(
                    "select distinct category,count(category) from Book b group by category")
                    .list();
            orders = session.createQuery(
                    "select o from Order o where o.time<=:endTime and o.time>=:startTime order by time desc")
                    .setParameter("endTime", endCalendar)
                    .setParameter("startTime", startCalendar)
                    .list();

            session.getTransaction().commit();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean init(long userID, String startTime, String endTime) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        int startYear = Integer.parseInt(startTime.substring(0, 4));
        int startMonth = Integer.parseInt(startTime.substring(5, 7));
        int startDay = Integer.parseInt(startTime.substring(8, 10));
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startYear, startMonth-1, startDay, 23, 59, 59);

        int endYear = Integer.parseInt(endTime.substring(0, 4));
        int endMonth = Integer.parseInt(endTime.substring(5, 7));
        int endDay = Integer.parseInt(endTime.substring(8, 10));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(endYear, endMonth-1, endDay, 23, 59, 59);

        try {
            session.beginTransaction();

            cateList = session.createQuery(
                    "select distinct category,count(category) from Book b group by category")
                    .list();
            User u = (User)session.get(User.class, userID);
            if (u==null) {
                session.getTransaction().commit();
                return false;
            }
            orders = session.createQuery(
                    "select o from Order o where o.user=:user and o.time<=:endTime and o.time>=:startTime order by time desc")
                    .setParameter("user", u)
                    .setParameter("endTime", endCalendar)
                    .setParameter("startTime", startCalendar)
                    .list();

            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public Map<String, AnalysisPair> getAnalysisCategory() {
        if (orders==null || orders.isEmpty()) {
            return null;
        }
        else if (category!=null && !category.isEmpty()) {
            return category;
        }

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

        return category;
    }

    public Map<String, AnalysisPair> getAnalysisDay() {
        if (orders==null || orders.isEmpty()) {
            return null;
        }
        else if (day!=null && !day.isEmpty()) {
            return day;
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

        return day;
    }

    public Map<String, AnalysisPair> getAnalysisMonth() {
        if (orders==null || orders.isEmpty()) {
            return null;
        }
        else if (month!=null && !month.isEmpty()) {
            return month;
        }

        Calendar curr = (Calendar)orders.get(0).getTime().clone();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

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

        return month;
    }

    public Map<String, AnalysisPair> getAnalysisYear() {
        if (orders==null || orders.isEmpty()) {
            return null;
        }
        else if (year!=null && !year.isEmpty()) {
            return year;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy");

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

        return year;
    }

    public Map<String, AnalysisPair> getAnalysisLimit() {
        if (orders==null || orders.isEmpty()) {
            return null;
        }
        else if (limit!=null && !limit.isEmpty()) {
            return limit;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (Order order: orders) {
            AnalysisPair ap = limit.get(df.format(order.getTime().getTime()));
            if (ap==null) {
                ap = new AnalysisPair();
                ap.setQuantity(order.getBookNumber());
                ap.setMoney(order.getTotalMoney());
                limit.put(df.format(order.getTime().getTime()),ap);
            }
            ap.addMoney(order.getTotalMoney());
            ap.addQuantity(order.getBookNumber());
        }

        return limit;
    }

    public void print() {
        System.out.print("orders:");
        System.out.println(orders);
        System.out.print("cate");
        System.out.println(cateList);
    }
}
