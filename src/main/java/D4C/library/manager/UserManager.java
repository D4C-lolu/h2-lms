package D4C.library.manager;

import D4C.library.user.*;
import D4C.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.List;


/**
 * Class to manage CRUD operations on User sub-classes
 */
public class UserManager {
    private static final UserManager _instance;
    private static EntityManager entityManager;

    static {
        try {
            _instance = new UserManager();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong creating the UserManager");
        }
    }

    private UserManager() {
        entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        if (entityManager == null) {
            throw new NullPointerException("Unable to locate entity manager");
        }
    }

    public static UserManager getInstance() {
        return _instance;
    }

    public Student createStudent(String firstName, String lastName, long matricNo, Year year) {
        Student s = new Student(firstName, lastName, matricNo, year);
        StudentDAO.insertStudent(entityManager, s);
        return s;
    }

    public Student getStudent(Long id) {
        Student s = StudentDAO.getStudent(entityManager, id);
        return s;
    }

    public List<Student> getAllStudents() {
        return StudentDAO.getAllStudents(entityManager);
    }

    public String resetStudentPassword(Long id) {
        String newPassword = StudentDAO.updateStudentPassword(entityManager, id);
        return newPassword;

    }

    public void deleteStudent(Long id) {
        StudentDAO.deleteStudent(entityManager, id);
    }

    public Student findStudentByMatric(Long matricNo) {
        return StudentDAO.getStudentByMatric(entityManager, matricNo);
    }

    public Admin createAdmin(String firstName, String lastName) {
        Admin a = new Admin(firstName, lastName);
        AdminDAO.insertAdmin(entityManager, a);
        return a;
    }

    public Admin getAdmin(Long id) {
        Admin a = AdminDAO.getAdmin(entityManager, id);
        return a;
    }

    public String resetAdminPassword(Long id) {
        String newPassword = AdminDAO.updateAdminPassword(entityManager, id);
        return newPassword;
    }

    public void revokeAdmin(Long id) {
        AdminDAO.revokeAdmin(entityManager, id);
    }

    public boolean ComparePassword(User u, String passcode) {
        return u.comparePassword(passcode);
    }

}
