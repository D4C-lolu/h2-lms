package D4C.library.manager;

import D4C.library.user.User;
import D4C.library.book.Book;
import D4C.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookManager {
    private static final BookManager _instance;

    static {
        try {
            _instance = new BookManager();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong creating the BookManager");
        }
    }

    private BookManager(){

    }

    public static BookManager getInstance() {
        return _instance;
    }

    public boolean checkAvailabilty(String ISBN, User user) {
        return true;
    }


}
