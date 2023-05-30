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
import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testStudentIsAdded(){
        Student s = new Student("Temi","Lade",170402514,Year.SIX);
        StudentDAO.insertStudent(entityManager,s);
        //Check that student s is not null
        Student sr = StudentDAO.getStudent(entityManager,s.getId());
        assertNotNull(sr);
    }

    @Test
    void testStudentAddedisStudentQueried(){
        Student l = new Student("Demi","Lade",170122514,Year.SIX);
        StudentDAO.insertStudent(entityManager,l);
        Student sr = StudentDAO.getStudent(entityManager,l.getId());
        assertEquals(l,sr);
    }
}
