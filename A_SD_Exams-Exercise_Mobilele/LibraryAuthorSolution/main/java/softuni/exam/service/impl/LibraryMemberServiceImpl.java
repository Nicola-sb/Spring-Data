package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberSeedDto;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BookService;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private static final String STARS_FILE_PATH = "src/main/resources/files/json/library-members.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final LibraryMemberRepository libraryMemberRepository;
    private final BookService bookService;

    public LibraryMemberServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, LibraryMemberRepository libraryMemberRepository, BookService bookService) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.libraryMemberRepository = libraryMemberRepository;
        this.bookService = bookService;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files
                .readString(Path.of(STARS_FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                .fromJson(readLibraryMembersFileContent(), LibraryMemberSeedDto[].class))
                .filter(libraryMembersDTO -> {
                    boolean isValid = validationUtil.isValid(libraryMembersDTO);

                    Optional<LibraryMember> byPhoneNumber = libraryMemberRepository.findByPhoneNumber(libraryMembersDTO.getPhoneNumber());
                    if (byPhoneNumber.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ? String.format("Successfully imported library member %s - %s",
                            libraryMembersDTO.getFirstName(),
                            libraryMembersDTO.getLastName())
                            : "Invalid library member")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(libraryMemberSeedDto -> {
                    LibraryMember libraryMember = modelMapper.map(libraryMemberSeedDto, LibraryMember.class);

//                    Book book = bookService.getBookById(libraryMemberSeedDto.getConstellation()).orElse(null);

//                    book.getStars().add(libraryMember);
//                    bookService.saveAddedStarInConstellation(book);

//                    libraryMember.setConstellation(book);
                    return libraryMember;
                })
                .forEach(libraryMemberRepository::save);

        return sb.toString();
    }

    @Override
    public LibraryMember findMemberById(Long id) {
        return libraryMemberRepository.findById(id).orElse(null);
    }

    @Override
    public void addAndSaveAddedStar(LibraryMember libraryMember, BorrowingRecord borrowingRecord) {
//        libraryMember.getObservers().add(borrowingRecord);
        libraryMemberRepository.save(libraryMember);
    }

    @Override
    public String exportLibraryMembers() {
        StringBuilder sb = new StringBuilder();

//        Set<LibraryMember> allByTypeAndObserversIsNull = starRepository.findAllByStarTypeAndObserversIsNullOrderByLightYearsAsc(Genre.RED_GIANT);

//        allByTypeAndObserversIsNull
//                .forEach(star -> {
//                    sb.append(String.format("LibraryMember: %s\n" +
//                                    "   *Distance: %.2f light years\n" +
//                                    "   **Description: %s\n" +
//                                    "   ***Book: %s",
//                            star.getName(),
//                            star.getLightYears(),
//                            star.getDescription(),
//                            star.getConstellation().getName()))
//                            .append(System.lineSeparator());
//                });

        return sb.toString().trim();
    }
}
