package Entities;

import D4C.library.book.Book;
import D4C.library.book.BookDAO;
import D4C.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookDAOTest {

    EntityManager entityManager;
    @BeforeEach
    void setUp() {
        entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        Book book1 = new Book("ABC", "Chinua Achebe", "Historical Fiction", "10505", true);
        Book book2 = new Book("Arrow of God", "Chinua Achebe", "Historical Fiction", "23223", true);
        BookDAO.insertBook(entityManager,book1);
        BookDAO.insertBook(entityManager,book2);
    }

    @AfterEach
    void tearDown(){
        HibernateUtil.shutdown();
    }

    @Test
    void testBookisAddedSuccessfully(){
        Book b = new Book("Things fall apart","Chinua Achebe","Historical Fiction","0123456789");
        BookDAO.insertBook(entityManager,b);
        //Check that book is successfully added
        Book c = BookDAO.getBookByISBN(entityManager,b.getISBN());
        assertNotNull(c);
    }

    @Test
    void testBookAddedisBookQueried(){
        Book b = new Book("Things fall apart","Chinua Achebe","Historical Fiction","0123456789");
        BookDAO.insertBook(entityManager,b);
        //Check that book is successfully added
        Book c = BookDAO.getBookByISBN(entityManager,b.getISBN());
        assertEquals(b,c);
    }

    @Test
    void testNumberOfBooksAddedReflectsPreviousAdditons(){
        List<Book> bookList = BookDAO.getAllBooks();
        assertTrue(bookList.size()>0);
    }

    @Test
    void testBookDeletedisRemoved(){
        Book b = new Book("Black","Ted white","Fantasy","08178549629");
        BookDAO.insertBook(entityManager,b);
        BookDAO.deleteByISBN(entityManager,b.getISBN());
        //Verify book is deleted
        Book c = BookDAO.getBookByISBN(entityManager,b.getISBN());
        assertNull(c);
    }
}
