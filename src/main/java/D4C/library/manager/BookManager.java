package D4C.library.manager;

import D4C.library.book.BookDAO;
import D4C.library.user.Student;
import D4C.library.user.StudentDAO;
import D4C.library.book.Book;
import D4C.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

/**
 * Class to Manage CRUD operations on Book class
 */
public class BookManager {
    private static final BookManager _instance;
    private static EntityManager entityManager;

    static {
        try {
            _instance = new BookManager();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong creating the BookManager");
        }
    }

    private BookManager(){
        entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        if (entityManager == null){
            throw new NullPointerException("Unable to locate entity manager");
        }

    }

    public static BookManager getInstance() {
        return _instance;
    }

    public Book createBook(String bookName, String authorName, String subject, String ISBN){
        Book b = new Book(bookName,authorName,subject,ISBN);
        BookDAO.insertBook(entityManager,b);
        return b;
    }

    public Book getBook(String ISBN){
        return BookDAO.getBookByISBN(entityManager,ISBN);
    }

    public List<Book> getAllBooks(){
        return BookDAO.getAllBooks(entityManager);
    }

    public void deleteBook(String ISBN){
        BookDAO.deleteByISBN(entityManager,ISBN);
    }

    public  boolean assignBook(Long studentId, String ISBN){
        entityManager.getTransaction().begin();
        UserManager userManager = UserManager.getInstance();
        Book b = userManager.getStudent(studentId).getBorrowedBook();
        //Check if user has currently owned book
        if (b != null){
            return false;
        }
        else {
            Book c = BookManager.getInstance().getBook(ISBN);
            StudentDAO.borrowBook(entityManager,studentId,c.getId());

        }
        return true;
    }

    public boolean returnBook(Long studentId){
        UserManager userManager = UserManager.getInstance();
        //query Join table borrowed_book
        Student s = userManager.getStudent(studentId);
        Book b = s.getBorrowedBook();
        if (b == null){
            return false;
        }
        else {
            //Return the book
            StudentDAO.returnBook(entityManager,studentId);

        }
        return true;
    }
}
