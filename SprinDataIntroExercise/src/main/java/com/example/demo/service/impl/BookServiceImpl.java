package com.example.demo.service.impl;

import com.example.demo.model.entity.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_FILE_PATH="src/main/resources/files/books.txt";
    private final AuthorService authorService;

    //Трябва да прочетем файлса, който държи книгите -> Обръщаме ги в Ентитита -> Подаваме тези ентитита на нашите репозиторита и ги записваме в базата

    //Щом се нуждаем от Репозитори -> си го създаваме ->
    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookServiceImpl(AuthorService authorService, BookRepository bookRepository, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if(bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(BOOK_FILE_PATH)).
                forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book=createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year,12,31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        //В имплементацията трябва да викнем от repositorito нещо --> отиваме в BookRepository за да си напишем нашия метод
        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(year,1,1))
                .stream().map( book -> String.format("%s %s",book.getAuthor().getFirstName(),book.getAuthor().getLastName()))
                .distinct().collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
//Как можем да създадем Енумерация от индекс?
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate localDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category>categories=categoryService.getRandomCategories();

       return new Book(editionType,localDate,copies,price,ageRestriction,title,author,categories);

    }
}
