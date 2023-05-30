package D4C.library.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Class to hold users with special privileges
 */
@Entity
@Table(name="admin")
public class Admin extends User {

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }



}
