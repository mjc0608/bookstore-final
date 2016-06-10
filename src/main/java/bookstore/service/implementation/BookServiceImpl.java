package bookstore.service.implementation;

import bookstore.service.ImageService;
import bookstore.entity.Book;
import bookstore.service.BookService;
import bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.mapping.Subclass;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class BookServiceImpl implements BookService {
    ImageService imageService = new ImageServiceImpl();
    public boolean addBook(Book book) {
        if (book==null) return false;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            String imageID;
            if (book.getImage()!=null) {
                imageID= imageService.storeImage(book.getImage());
                if (imageID==null) {
                    return false;
                }
                book.setImageID(imageID);
            }

            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean removeBook(long bookID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Book book = (Book) session.get(Book.class, bookID);
            if (book!=null) {
                String imageID = book.getImageID();
                session.delete(book);
                imageService.removeImage(imageID);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean modifyBook(Book book) {
        if (book==null) return false;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            if (book.getImage()!=null) {
                String imageID= imageService.storeImage(book.getImage());
                if (imageID!=null && !imageID.equals("")) {
                    imageService.removeImage(book.getImageID());
                    book.setImageID(imageID);
                }
            }

            session.beginTransaction();
            session.update(book);
            System.out.println("here2");

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public Book getBookById(long id) {
        Book book;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            book = (Book)session.get(Book.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return book;
    }

    public List<Book> getBooksByName(String name) {
        List<Book> books;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            books = session.createQuery(
                    "select b from Book b where b.name like :param")
                    .setParameter("param","%" + name + "%")
                    .list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return books;
    }

    public List<Book> getAllBooks() {
        List<Book> books;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            books = session.createQuery(
                    "select b from Book b").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return books;
    }
}
