package com.example.demo;

import com.example.demo.model.entity.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
//В CommandLineRunnera си поисквам обекта от CategoryService
    //Когато е финално поле и свети в червено и иска да го инджектнем през конструктора
    //Използвайки конструктор инджекшъна(в случая дипендъсни инджекшън ние успяваме да инджектнем нашето
    private final AuthorService authorService;
    //Нашият CommandLineRunnerImpl ще се нуждае,ще дипендва и на AuthorService.Защо? Защото ще искаме да сложим и нашите автори,да ги инсъртнем в базата
    private final BookService bookService;


    //Казвам на Спринг,че за моя клас CommandLineRunnerImpl аз се нуждая от categoryService;ТОЙ СЕ ГРИЖИ ЗА ИНСТАНЦИРАНЕ,СЪЗДАВАНЕ, ВСИЧКИ ДИПЕНДЪСИТА
    //НА categoryService, ИНДЖЕКТВА МИ ГО ПРЕЗ КОНСТРУКТОРА И АЗ ВЕЧЕ ИМАМ ДОСТЪП ДО ГОТОВА ИНСТАНЦИЯ categoryService и директно мога да му кажа
    //seedCategories(създавам си го този метод и повече нямам работа в CommandLineRunnerImpl) -> отивам и трябва да си имплементирам метода seedCategories в интерефейса CategoryService
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    //След като съм си анотирал със @Service CategoryServiceImpl и съм му направил констурктор и финално полето вече имам достъп до CategoryService

    //Този метод ще се стартира, когато Спринг се справи с всички beanowe,вдигне ги направи инстанция за всеки един от тях
    //Но за да му кажем, че и този клас ще бъде менажиран от Ioc контейнера трябва да му сложим @Component
    @Override
    public void run(String... args) throws Exception {
       seedData();
//       printAllBooksAfter2000(2000);
//        printAllAuthorsNamesWithBookBeforeReleaseDateBefore1990(1990);
         printAllAuthorsAndNumberOfTheirBooks();
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        //От къде ще го вземем - от authorService
        authorService.getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBookBeforeReleaseDateBefore1990(int year) {
        bookService.findAllAuthorsWithBooksWithReleaseDateBeforeYear(year).forEach(System.out::println);
    }

    private void printAllBooksAfter2000(int year) {
        //На това ниво вече имаме bookService
        bookService.findAllBooksAfterYear(year).
                stream().map(Book::getTitle).
                forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
    //Защо го кръщаваме така? Защото той ще имплементира интерфейса CommandLineRunner, който идва от Спринг
}
