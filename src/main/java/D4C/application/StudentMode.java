package D4C.application;

import D4C.library.book.Book;
import D4C.library.manager.BookManager;
import D4C.library.manager.UserManager;
import D4C.library.user.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Static class containing methods to run in regular mode
 */
public class StudentMode {
    private static final Logger logger = LogManager.getLogger(StudentMode.class);
    private static Scanner sc;
    private static final String backChar = "#";
    private static final String endChar = "0";
    private static final UserManager userManager = UserManager.getInstance();
    private static final BookManager bookManager = BookManager.getInstance();
    private static final String userMail = "admin@lms.com";
    public static Student studentlogin() {
        sc = new Scanner(System.in);
        logger.info("\nPlease enter your matric number to log in or '0' to exit: ");
        String input = "";
        D4C.library.user.Student student = null;
        do {
            try {
                input = sc.nextLine();
                if (input.equals(endChar)) {
                    logger.info("\nExiting program");
                    System.exit(0);
                }
                Long matricNo = Long.parseLong(input);
                student = userManager.findStudentByMatric(matricNo);
                //Get password to confirm login
                if (student != null){
                    logger.info("Please enter your password: ");
                    input = sc.nextLine();
                    if (userManager.ComparePassword(student, input)){
                        //Login successful
                        //Return to main program
                        break;
                    }
                    else{
                        logger.error("Invalid password");
                    }
                }
                //If account does not exist
                else{
                    logger.error("The number above does not match any user");
                    logger.info("Please enter a valid matric number: ");
                    logger.info("Or contact "+userMail+" to get an account");
                }
            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            } catch (final NullPointerException e) {
                logger.error("The number above does not match any user");
                logger.info("Please enter a valid user id: ");
            }
        }
        while (!input.equals(endChar));

        return student;
    }

    public static void displayStudentMenu() {
        logger.info("Enter '1' to see the list of available books.");
        logger.info("Enter '2' to see your library.");
        logger.info("Enter '3' to borrow a book.");
        logger.info("Enter '4' to return your current book. ");
        logger.info("Or enter 0 to end the program");
    }

    private static void checkLibrary(Long id){
        Book b = userManager.getStudent(id).getBorrowedBook();
        if (b==null){
            logger.info("Your library is currently empty");
        }
        else{
            logger.info("\n{} by {}, Subject: {}, ISBN:{} ", b.getBookName(), b.getAuthorName(), b.getSubject(), b.getISBN());
        }
         logger.info("==========================================");
    }

    private static void borrowBook(Long studentId ){
        sc = new Scanner(System.in);
        logger.info("Please enter the isbn of the book: ");
        String isbn = sc.nextLine();
        //check if isbn is valid
        Book b = bookManager.getBook(isbn);
        if(b==null){
            logger.info("No book with that ISBN exists in the library!");
            return;
        }
        boolean status = bookManager.assignBook(studentId,isbn);
        if (status){
            logger.info("Please return the currently borrowed book before borrowing another");
        }
        else{
            logger.info("The book {} with ISBN: {} has been added to your library",b.getBookName(),b.getISBN());
        }
    }

    private static void returnBook(Long id){
        Book b = userManager.getStudent(id).getBorrowedBook();
        if (b ==null){
            logger.info("You do not currently have any outstanding book ");
        }
        else{
            bookManager.returnBook(id);
        }
    }


    public static void run(Student student){
        int choice = 0;
        logger.info("Welcome Student User {}",student.getName());
        do {
            displayStudentMenu();
            logger.info("\nEnter your response here: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        Application.showAllBooks();
                        break;
                    case 2:
                        checkLibrary(student.getId());
                        break;
                    case 3:
                        borrowBook(student.getId());
                        break;
                    case 4:
                        returnBook(student.getId());
                        break;
                    default:
                        logger.warn("\nPlease enter a number between 0 and 7 ");
                }
            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }
        }
        while (choice !=0);
    }
}
