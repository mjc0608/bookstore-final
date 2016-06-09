package bookstore.action;

import bookstore.DAO.BookDAO;
import bookstore.DAO.implementation.BookDAOImpl;
import bookstore.entity.Book;
import bookstore.util.AdminUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by Jachin on 6/9/16.
 */
public class BookAction extends ActionSupport {
    private BookDAO bookService = new BookDAOImpl();
    private Book book;
    private List<Book> books;
    private long id=-1;
    private String keyword;


    public String add() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidBook(book)) {
            return INPUT;
        }
        else if (bookService.addBook(book)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String modify() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (!isValidBook(book)) {
            return INPUT;
        }
        else if (bookService.modifyBook(book)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String remove() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }
        else if (id<0) {
            return ERROR;
        }
        else if (bookService.removeBook(id)) {
            return SUCCESS;
        }
        else {
            return ERROR;
        }
    }

    public String query() {
        books = bookService.getAllBooks();
        return SUCCESS;
    }

    public String info() {
        if (!AdminUtil.isAdmin()) {
            return ERROR;
        }

        book = bookService.getBookById(id);
        if (!isValidBook(book)) {
            return ERROR;
        }
        else {
            return SUCCESS;
        }
    }

    public String infoJSON() {
        book = bookService.getBookById(id);
        if (!isValidBook(book)) {
            return ERROR;
        }
        else {
            return SUCCESS;
        }
    }

    public String search() {
        books = bookService.getBooksByName(keyword);
        if (books==null) {
            return ERROR;
        }
        else {
            return SUCCESS;
        }
    }

    public Book getBook() {
        return book;
    }

    public BookDAO getBookService() {
        return bookService;
    }

    public List<Book> getBooks() {
        return books;
    }

    public long getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setBookService(BookDAO bookService) {
        this.bookService = bookService;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private boolean isValidBook(Book book) {
        if (book==null) {
            return false;
        }
        if (book.getName()==null || book.getName().equals("")) {
            return false;
        }
        if (book.getPrice()<=0) {
            return false;
        }

        return true;
    }
}
