package D4C.application;

import D4C.library.book.Book;
import D4C.library.manager.BookManager;
import D4C.library.manager.UserManager;
import D4C.library.user.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import D4C.library.user.Admin;
import java.util.List;
import java.util.Scanner;


/**
 * Main Application class
 */
public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);
    private static Scanner sc;
    private static final String backChar = "#";
    private static final String endChar = "0";
    private static final UserManager userManager = UserManager.getInstance();
    private static final BookManager bookManager = BookManager.getInstance();
    private static final String userMail = "admin@lms.com";


    public static int chooseMode() {

        logger.info("Please Enter 1 to sign in as a regular user ");
        logger.info("or 2 to sign in as ab admin");
        logger.info("or Enter 0 to end the program");
        sc = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                String input = sc.nextLine();
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    default:
                        logger.warn("\nPlease enter either 1 or 2 ");
                        break;
                }

            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }
        }
        while (choice != 0);

        return -1;
    }

    public static void showAllBooks() {
        List<Book> bookList = bookManager.getAllBooks();
        if (bookList.size() == 0) {
            logger.info("There are no books currently available!");
        } else {
            logger.info("The books currently in the library are :");
            for (Book b : bookList) {
                logger.info("\n{} by {}, Subject: {}, ISBN:{} ", b.getBookName(), b.getAuthorName(), b.getSubject(), b.getISBN());
            }
        }
    }


    public static void start() {
        setUp();
        logger.info("Welcome to LMS");
        int mode = chooseMode();
        switch (mode) {
            case 1:
                Student student = StudentMode.studentlogin();
                StudentMode.run(student);
            case 2:
                Admin admin = AdminMode.adminLogin();
                AdminMode.run(admin);
            default:
                break;
        }
    }

    public static void setUp(){
        UserManager userManager  = UserManager.getInstance();
        Admin admin = userManager.createAdmin("Demilade","Okeowo");
        logger.info("Admin's password is {}",admin.getPassword());
    }
}
