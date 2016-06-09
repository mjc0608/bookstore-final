package bookstore.DAO;

import bookstore.entity.Book;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public interface BookDAO {

    public boolean addBook(Book book);
    public boolean removeBook(long bookID);
    public boolean modifyBook(Book book);

    public Book getBookById(long id);
    public List<Book> getBooksByName(String name);
    public List<Book> getAllBooks();

}
