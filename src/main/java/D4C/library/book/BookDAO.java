package D4C.library.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


/**
 * Data Access Object for the Book class
 * Contains nethods to carry out queries on the book table
 */
public class BookDAO {

    public static void insertBook(EntityManager entityManager, Book b) {
        entityManager.getTransaction().begin();
        // save the Student object
        entityManager.persist(b);
        entityManager.getTransaction().commit();
    }


    public static List<Book> getAllBooks(EntityManager entityManager) {

        List<Book> books;
        entityManager.getTransaction().begin();
        books = entityManager.createQuery("select b from Book b", Book.class).getResultList();
        entityManager.getTransaction().commit();
        return books;

    }


    public static Book getBook(EntityManager entityManager, Long id) {
        // Get Book by ID
        entityManager.getTransaction().begin();
        Book book = entityManager.getReference(Book.class,id);
        entityManager.detach(book);
        entityManager.getTransaction().commit();
        return book;
    }

    public static Book getBookByISBN(EntityManager entityManager, String isbn){
        // Get Book by ISBN
        entityManager.getTransaction().begin();
        String queryStr = "SELECT b FROM Book b WHERE b.isbn=:isbn ";
        TypedQuery<Book> query = entityManager.createQuery(queryStr, Book.class);
        Book book = query.setParameter("isbn",isbn).getSingleResult();
        entityManager.detach(book);
        entityManager.getTransaction().commit();
        return book;
    }

    public static void deleteBook(EntityManager entityManager,Long id) {

        entityManager.getTransaction().begin();
        Book book = entityManager.getReference(Book.class,id);
        entityManager.remove(book);
        entityManager.detach(book);
        entityManager.getTransaction().commit();
    }


    public static void deleteByISBN(EntityManager entityManager, String isbn){
        // Get Book by ISBN
        entityManager.getTransaction().begin();
        String queryStr = "DELETE  FROM Book b WHERE b.isbn=:isbn ";
        TypedQuery<Book> query = entityManager.createQuery(queryStr, Book.class);
        query.setParameter("isbn",isbn).executeUpdate();
        entityManager.getTransaction().commit();

    }

}
