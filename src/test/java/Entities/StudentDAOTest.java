package Entities;

import D4C.library.book.Book;
import D4C.library.book.BookDAO;
import D4C.library.user.Student;
import D4C.library.user.StudentDAO;
import D4C.library.user.User;
import D4C.library.user.Year;
import D4C.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentDAOTest {
    EntityManager entityManager;
    @BeforeEach
    void setUp() {
        entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        Student s1 = new Student("Demilade","Okeowo",170403512, Year.FIVE);
        Student s2  = new Student("Dare","Olumide",13040506,Year.ONE);
        StudentDAO.insertStudent(entityManager,s1);
        StudentDAO.insertStudent(entityManager,s2);
    }

    @AfterEach
    void tearDown(){
        HibernateUtil.shutdown();
    }

    
}
