package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordSeedRootDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.entity.enums.Genre;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.service.BookService;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private static final String BORROWING_RECORDS_FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final LibraryMemberService libraryMemberService;
    private final BookService bookService;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                                       XmlParser xmlParser,
                                       ModelMapper modelMapper,
                                       ValidationUtil validationUtil,
                                       LibraryMemberService libraryMemberService,
                                       BookService bookService) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.libraryMemberService = libraryMemberService;
        this.bookService = bookService;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files
                .readString(Path.of(BORROWING_RECORDS_FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(BORROWING_RECORDS_FILE_PATH, BorrowingRecordSeedRootDto.class)
                .getBorrowingRecordSeedDtos()
                .stream()
                .filter(borrowingRecordSeedDto -> {
                    boolean isValid = validationUtil.isValid(borrowingRecordSeedDto);

                    LibraryMember libraryMember = libraryMemberService.findMemberById(borrowingRecordSeedDto.getMember().getId());
                    if (libraryMember == null) {
                        isValid = false;
                    }

                    Book bookByTitle = bookService.getBookByTitle(borrowingRecordSeedDto.getBook().getTitle());

                    if (bookByTitle == null) {
                        isValid = false;
                    }

                    sb
                            .append(isValid
                                    ? String.format("Successfully imported borrowing record %s - %s",
                                    borrowingRecordSeedDto.getBook().getTitle(),
                                    borrowingRecordSeedDto.getBorrowDate())
                                    : "Invalid borrowing record")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(borrowingRecordDto -> {
                    BorrowingRecord borrowingRecord = modelMapper.map(borrowingRecordDto, BorrowingRecord.class);

                    LibraryMember libraryMember = libraryMemberService.findMemberById(borrowingRecordDto.getMember().getId());
                    Book bookByTitle = bookService.getBookByTitle(borrowingRecordDto.getBook().getTitle());

                    borrowingRecord.setBook(bookByTitle);
                    borrowingRecord.setMember(libraryMember);

                    return borrowingRecord;
                })
                .forEach(borrowingRecordRepository::save);


        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {
        Set<BorrowingRecord> allByReturnDateIsNull = borrowingRecordRepository
                .findAllByBorrowDateBeforeAndBook_GenreOrderByBorrowDateDesc(LocalDate.parse("2021-09-10"),Genre.SCIENCE_FICTION);

        StringBuilder stringBuilder = new StringBuilder();

        allByReturnDateIsNull.forEach(borrowRecord -> {
            stringBuilder.append(String.format("Book title: %s\n" +
                            "*Book author: %s\n" +
                            "**Date borrowed: %s\n" +
                            "***Borrowed by: %s %s",
                    borrowRecord.getBook().getTitle(),
                    borrowRecord.getBook().getAuthor(),
                    borrowRecord.getBorrowDate().toString(),
                    borrowRecord.getMember().getFirstName(),
                    borrowRecord.getMember().getLastName()))
                    .append(System.lineSeparator());
        });

        return stringBuilder.toString().trim();
    }
}
