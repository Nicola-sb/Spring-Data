package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord extends BaseEntity {

    private Book book;
    private LibraryMember member;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String remarks;

    @ManyToOne
    public Book getBook() {
        return book;
    }

    public BorrowingRecord setBook(Book book) {
        this.book = book;
        return this;
    }

    @ManyToOne
    public LibraryMember getMember() {
        return member;
    }

    public BorrowingRecord setMember(LibraryMember member) {
        this.member = member;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public BorrowingRecord setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BorrowingRecord setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    @Column
    public String getRemarks() {
        return remarks;
    }

    public BorrowingRecord setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}
