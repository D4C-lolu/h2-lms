package D4C.library.user;

import jakarta.persistence.*;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import D4C.library.book.Book;

/**
 * A class representing a user.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "matric_no")
    private final long matricNo;

    @Convert(converter = YearAttributeConverter.class)
    @Column(name = "year")
    private final Year year;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "borrowed_book",
            joinColumns = {@JoinColumn( name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private Book borrowedBook;

    public User(String firstName, String lastName, long matricNo, Year year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.matricNo = matricNo;
        this.year = year;
        this.password = generatePassword();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getMatricNo() {
        return matricNo;
    }

    public Year getYear() {
        return year;
    }

    public String getPassword() {
        return password;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    /**
     * Method to auto generate an 8 character long password
     *
     * @return Auto generated password
     */
    private static String generatePassword() {

        // create character rule for lower case
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        // set number of lower case characters
        LCR.setNumberOfCharacters(2);
        // create character rule for upper case
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        // set number of upper case characters
        UCR.setNumberOfCharacters(2);
        // create character rule for digit
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        // set number of digits
        DR.setNumberOfCharacters(2);
        // create character rule for special characters
        CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);
        // set number of special characters
        SR.setNumberOfCharacters(2);
        // create instance of the PasswordGenerator class
        PasswordGenerator passGen = new PasswordGenerator();
        // call generatePassword() method of PasswordGenerator class to get Passay generated password
        String password = passGen.generatePassword(8, SR, LCR, UCR, DR);
        // return Passay generated password to the main() method
        return password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricNo=" + matricNo +
                ", year=" + year +
                '}';
    }
}
