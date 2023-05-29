package D4C.library.book;

import D4C.library.user.User;
import jakarta.persistence.*;

/**
 * A class representing  a book object
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_name")
    private final String bookName;

    @Column(name = "author_name")
    private final String authorName;

    @Column(name = "subject")
    private final String subject;

    @Column(name = "isbn")
    private final String ISBN;

    @OneToOne(mappedBy = "book")
    private User user;

    /**
     * @param bookName   string representing the name of the book
     * @param authorName string containing the name of the book's author
     * @param subject    string containing the subject title
     * @param ISBN       A string to uniquely identify a type of book
     */
    public Book(String bookName, String authorName, String subject, String ISBN) {

        this.bookName = bookName;
        this.authorName = authorName;
        this.subject = subject;
        this.ISBN = ISBN;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getSubject() {
        return subject;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return user == null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", subject='" + subject + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
