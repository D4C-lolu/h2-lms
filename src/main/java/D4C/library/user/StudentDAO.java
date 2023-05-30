package D4C.library.user;

import D4C.library.book.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Data Access Object for the Student class
 * Contains methods to carry out queries on the user table
 */
public class StudentDAO {


    public static void insertStudent(EntityManager entityManager, Student s) {
        entityManager.getTransaction().begin();
        // save the Student object
        entityManager.persist(s);
        entityManager.getTransaction().commit();
    }

    public static List<Student> getAllStudents(EntityManager entityManager) {

        List<Student> students;
        entityManager.getTransaction().begin();
        students = entityManager.createQuery("select s from Student s", Student.class).getResultList();
        entityManager.getTransaction().commit();
        return students;

    }

    public static Student getStudent(EntityManager entityManager, Long id) {
        // Get Student by ID
        entityManager.getTransaction().begin();
        Student student = entityManager.getReference(Student.class,id);
        entityManager.detach(student);
        entityManager.getTransaction().commit();
        return student;
    }

    public static Student getStudentByMatric(EntityManager entityManager,Long matricNo){entityManager.getTransaction().begin();
        String queryStr = "SELECT S FROM Student s WHERE s.matric_no=:m ";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        Student s = query.setParameter("m",matricNo).getSingleResult();
        entityManager.detach(s);
        entityManager.getTransaction().commit();
        return s;
    }

    public  static String updateStudentPassword(EntityManager entityManager, Long id) {

        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class,id);
        student.updatePassword();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        return student.getPassword();

    }

    public static void deleteStudent(EntityManager entityManager,Long id) {
        entityManager.getTransaction().begin();
        Student student = entityManager.getReference(Student.class,id);
        entityManager.remove(student);
        entityManager.detach(student);
        entityManager.getTransaction().commit();
    }

    public static boolean checkStatus(EntityManager entityManager,Long id) {
        Student student = getStudent(entityManager,id);
        return student.isDefaulting();
    }

    public static void borrowBook(EntityManager entityManager, Long studentId, Long bookId){
        entityManager.getTransaction().begin();
        Student student = entityManager.getReference(Student.class,studentId);
        Book book = entityManager.getReference(Book.class,bookId);
        student.borrowBook(book);
        entityManager.merge(student);
        entityManager.getTransaction().commit();

    }

    public static void returnBook(EntityManager entityManager, Long studentId){
        entityManager.getTransaction().begin();
        Student student = entityManager.getReference(Student.class,studentId);
        student.returnBook();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }
}
