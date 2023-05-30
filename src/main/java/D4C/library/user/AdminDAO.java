package D4C.library.user;

import jakarta.persistence.EntityManager;

/**
 * Class containing the methods for accesssing Admin objects
 */
public class AdminDAO {

    public static void insertAdmin(EntityManager entityManager, Admin a) {
        entityManager.getTransaction().begin();
        // save the Student object
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }
    public static Admin getAdmin(EntityManager entityManager, Long id) {
        // Get Student by ID
        entityManager.getTransaction().begin();
        Admin admin = entityManager.getReference(Admin.class,id);
        entityManager.detach(admin);
        entityManager.getTransaction().commit();
        return admin;
    }


    public  static String updateAdminPassword(EntityManager entityManager, Long id) {

        entityManager.getTransaction().begin();
        Admin admin = entityManager.find(Admin.class,id);
        admin.updatePassword();
        entityManager.merge(admin);
        entityManager.getTransaction().commit();
        return admin.getPassword();
    }

    public static void revokeAdmin(EntityManager entityManager,Long id) {
        entityManager.getTransaction().begin();
        Admin admin = entityManager.getReference(Admin.class,id);
        entityManager.remove(admin);
        entityManager.detach(admin);
        entityManager.getTransaction().commit();
    }

}
