package D4C.application;

import D4C.library.book.Book;
import D4C.library.manager.BookManager;
import D4C.library.manager.UserManager;
import D4C.library.user.Admin;
import D4C.library.user.Student;
import D4C.library.user.Year;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;


/**
 * Static class containing methods for running in Admin Mode
 */
public class AdminMode {
    private static final Logger logger = LogManager.getLogger(AdminMode.class);
    private static Scanner sc;
    private static final String endChar = "0";
    private static final UserManager userManager = UserManager.getInstance();
    private static final BookManager bookManager = BookManager.getInstance();

    public static Admin adminLogin(){
        sc = new Scanner(System.in);
        logger.info("\nPlease enter your admin id to log in or '0' to exit: ");
        String input = "";
        D4C.library.user.Admin admin = null;
        do {
            try {
                input = sc.nextLine();
                if (input.equals(endChar)) {
                    logger.info("\nExiting program");
                    System.exit(0);
                }
                Long id = Long.parseLong(input);
                admin = userManager.getAdmin(id);
                //Get password to confirm login
                if (admin != null){
                    logger.info("Please enter your password: ");
                    input = sc.nextLine();
                    if (userManager.ComparePassword(admin, input)){
                        //Login successful
                        //Return to main program
                        break;
                    }
                    else{
                        logger.error("Invalid password");
                    }
                }
                //If admin account does not exist
                else{
                    logger.error("The number above does not match any user");
                    logger.info("Please enter a valid id: ");
                }
            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            } catch (final NullPointerException e) {
                logger.error("The number above does not match any user");
                logger.info("Please enter a valid user id: ");
            }
        }
        while (!input.equals(endChar));

        return admin;
    }

    private static void displayAdminMenu() {
        logger.info("Enter '1' to see the list of available books.");
        logger.info("Enter '2' to see the list of students .");
        logger.info("Enter '3' to add a book.");
        logger.info("Enter '4' to delete a book.");
        logger.info("Enter '5' to add a user.");
        logger.info("Enter '6' to remove a user");
        logger.info("Enter '7' to reset a user's passwoerd");
        logger.info("Or enter 0 to end the program");
        logger.info("=============================================================");
    }

    private static void getAllStudents(){
        List<Student> studentList = userManager.getAllStudents();
        if (studentList.size()==0){
            logger.info("There are no currently enrolled srudents");
        }
        else{
            logger.info("The currently enrolled students are: ");
            for(Student s:studentList){
                logger.info("{} with matric num: {} in year {]",s.getName(),s.getMatricNo(),s.getYear());
            }
        }
        logger.info("=============================================================");
    }

    private static void addBook(String bookName, String authorName, String subject, String ISBN){
        Book b = bookManager.createBook(bookName,authorName,subject,ISBN);
        logger.info("Book {} with ISBN {} has been created",b.getBookName(),b.getISBN());
        logger.info("=============================================================");
    }

    private static void addBook(){
        sc = new Scanner(System.in);
        logger.info("Please enter the details of the book: ");
        logger.info("Book title: ");
        String bookName = sc.nextLine();
        logger.info("Author name:");
        String authorName = sc.nextLine();
        logger.info("Subject: ");
        String subject = sc.nextLine();
        logger.info("ISBN: ");
        String isbn = sc.nextLine();
        addBook(bookName,authorName,subject,isbn);
    }

    private static void deleteBook(String ISBN){
        Book b = bookManager.getBook(ISBN);
        bookManager.deleteBook(ISBN);
        logger.info("Book {} with ISBN {} has been deleted",b.getBookName(),b.getISBN());
        logger.info("=============================================================");
    }

    private static void deleteBook(){
        sc = new Scanner(System.in);
        logger.info("Please enter the ISBN of the book: ");
        logger.info("ISBN: ");
        String isbn = sc.nextLine();
        deleteBook(isbn);
    }

    private static void addUser(String firstName, String lastName, long matricNo, Year year){
        Student s = userManager.createStudent(firstName,lastName,matricNo,year);
        logger.info("Student user {} with Matric Number: {} and password: {} has been added.",s.getName(),s.getMatricNo(),s.getPassword());
        logger.info("=============================================================");
    }

    private static void addUser(){
        sc = new Scanner(System.in);
        logger.info("Please enter the details of the user: ");
        logger.info("First name: ");
        String firstName = sc.nextLine();
        logger.info("Last name:");
        String lastName = sc.nextLine();
        logger.info("Matric Number");
        String input = sc.nextLine();
        Long matricNo = Long.parseLong(input);
        logger.info("Year: ");
        String y = sc.nextLine();
        Year year = Year.valueOf(y);
        addUser(firstName,lastName,matricNo,year);
    }

    private static void removeUser(Long id){
        Student s = userManager.getStudent(id);
        userManager.deleteStudent(id);
        logger.info("Student user {} with matric number :{} has been removed",s.getName(),s.getMatricNo());
        logger.info("=============================================================");
    }

    private static void removeUser(){
        sc = new Scanner(System.in);
        logger.info("Please enter the id of the user: ");
        Long id = Long.parseLong(sc.nextLine());
        removeUser(id);
    }

    private static void resetStudentPassword(Long id){
        Student s = userManager.getStudent(id);
        String newPassword = userManager.resetStudentPassword(id);
        logger.info("Student user {}'s new password is {}",s.getName(),newPassword);
        logger.info("=============================================================");
    }

    private static void resetPassword(){
        sc = new Scanner(System.in);
        logger.info("Please enter the id of the user: ");
        Long id = Long.parseLong(sc.nextLine());
        resetStudentPassword(id);
    }

    public static void run(Admin admin){
        int choice = 0;
        logger.info("Welcome Admin {}",admin.getName());
        do {
            displayAdminMenu();
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
                        getAllStudents();
                        break;
                    case 3:
                        addBook();
                        break;
                    case 4:
                        deleteBook();
                        break;
                    case 5:
                        addUser();
                        break;
                    case 6:
                        removeUser();
                        break;
                    case 7:
                        resetPassword();
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
