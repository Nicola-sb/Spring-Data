//
//package softuni.exam.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//import softuni.exam.service.BorrowingRecordsService;
//import softuni.exam.service.BookService;
//import softuni.exam.service.LibraryMemberService;
//
//import javax.xml.bind.JAXBException;
//import java.io.IOException;
//
//@Service
//public class Init implements CommandLineRunner {
//    private final BookService bookService;
//    private final LibraryMemberService libraryMemberService;
//    private final BorrowingRecordsService borrowingRecordsService;
//
//    public Init(BookService bookService, LibraryMemberService libraryMemberService, BorrowingRecordsService borrowingRecordsService) {
//        this.bookService = bookService;
//        this.libraryMemberService = libraryMemberService;
//        this.borrowingRecordsService = borrowingRecordsService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        initializeBooks();
////        initializeLibraryMembers();
////        initializeBorrowingRecords();
////        exportBorrowingRecords();
//    }
//
//    private void exportBorrowingRecords() {
//        System.out.println(borrowingRecordsService.exportBorrowingRecords());
//    }
//
//    private void initializeBorrowingRecords() throws IOException, JAXBException {
//        System.out.println(borrowingRecordsService.importBorrowingRecords());
//    }
//
//    private void initializeLibraryMembers() throws IOException {
//        System.out.println(libraryMemberService.importLibraryMembers());
//    }
//
//    private void initializeBooks() throws IOException {
//        System.out.println(bookService.importBooks());
//    }
//}
//
