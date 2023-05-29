package D4C.library.book;

import D4C.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Implementation class for BookDAO
 */
public class BookDAOImpl implements BookDAO {

    private static final Logger logger = LogManager.getLogger(BookDAOImpl.class);

    public void insert(Book b) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book object
            session.save(b);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
    }

    public void insert(Book... books) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book object
            for (Book b : books) {
                session.save(b);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        Transaction transaction = null;
        List<Book> books;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Get all books
            books = session.createQuery("from Book", Book.class).list();
            // commit transaction
            transaction.commit();
            return books;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Book> getBook(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Get book by ISBN
            String queryString = "from Book B where B.isbn =:n";
            Query q = session.createQuery(queryString);
            q.setParameter("n", id);
            List<Book> results = q.list();
            // commit transaction
            transaction.commit();
            return results;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }


}
