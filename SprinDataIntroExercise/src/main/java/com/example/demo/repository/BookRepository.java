package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

   //1.	Get all books after the year 2000. Print only their titles.
    //Трябва да намерим всички книги след 2000 -> правя си един Лист<Книги>
    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);
    //Сега вече в нашето репозитори ще върне точно Сета от тези Книги
    //Всичко което може още на ниво База го филтрираме,сортираме

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

}
