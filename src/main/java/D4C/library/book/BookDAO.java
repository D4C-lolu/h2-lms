package D4C.library.book;
import java.util.List;

public interface BookDAO {
    public void insert(Book b);
    public void insert(Book... books);
    public List<Book> getBook(String id);
    public List<Book> getAllBooks();
}

