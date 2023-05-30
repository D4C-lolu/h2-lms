package D4C.library.user;

import jakarta.persistence.*;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 * A class representing a user.
 */
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "password")
    private String password;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getName() {
        return firstName + " " + lastName;
    }


    public String getPassword() {
        return password;
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
        PasswordGenerator passGen = new PasswordGenerator();
        String password = passGen.generatePassword(8, SR, LCR, UCR, DR);

        return password;
    }

    /**
     * Method to reset password field
     *
     */
    public void updatePassword(){
        this.password = generatePassword();
    }

    public boolean comparePassword(String passcode){
        if (this.password.equals(passcode)){
            return true;
        }
        return false;
    }


}
