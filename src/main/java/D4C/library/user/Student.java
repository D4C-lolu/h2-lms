package D4C.library.user;

import D4C.library.book.Book;
import jakarta.persistence.*;

/**
 * Class represening Student object
 */
@Entity
@Table(name="student")
public class Student extends User{
    @Column(name = "matric_no")
    private final long matricNo;

    @Convert(converter = YearAttributeConverter.class)
    @Column(name = "year")
    private final Year year;

    @Column(name="defaulting")
    private boolean defaulting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "borrowed_book",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private Book borrowedBook;

    public Student (String firstName, String lastName, long matricNo, Year year){
        super(firstName,lastName);
        this.matricNo = matricNo;
        this.year = year;
        this.defaulting = false;
    }

    public long getMatricNo() {
        return matricNo;
    }

    public Year getYear() {
        return year;
    }
    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public boolean isDefaulting(){return defaulting;}

    public void borrowBook(Book book){
        this.borrowedBook = book;
        this.defaulting = true;
    }

    public void returnBook(){
        this.borrowedBook = null;
        this.defaulting = false;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name= "+ this.getName()+
                "matricNo= " + matricNo +
                ", year= " + year +
                ", borrowedBook= " + borrowedBook +
                ", defaulting= " + defaulting +
                " }";
    }
}
