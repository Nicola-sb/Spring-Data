package softuni.exam.models.dto;

import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.LibraryMember;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordSeedDto {

    @XmlElement(name = "book")
    private BookDTO book;
    @XmlElement(name = "member")
    private LibraryMemberDTO member;
    @XmlElement(name = "borrow_date")
    private String borrowDate;
    @XmlElement(name = "return_date")
    private String returnDate;
    @XmlElement(name = "remarks")
    private String remarks;

    @NotNull
    public BookDTO getBook() {
        return book;
    }

    public BorrowingRecordSeedDto setBook(BookDTO book) {
        this.book = book;
        return this;
    }

    @NotNull
    public LibraryMemberDTO getMember() {
        return member;
    }

    public BorrowingRecordSeedDto setMember(LibraryMemberDTO member) {
        this.member = member;
        return this;
    }

    @NotNull
    public String getBorrowDate() {
        return borrowDate;
    }

    public BorrowingRecordSeedDto setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    @NotNull
    public String getReturnDate() {
        return returnDate;
    }

    public BorrowingRecordSeedDto setReturnDate(String returnDate) {
        this.returnDate = returnDate;
        return this;
    }



    @Size(min = 3,max = 100)
    public String getRemarks() {
        return remarks;
    }

    public BorrowingRecordSeedDto setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}
