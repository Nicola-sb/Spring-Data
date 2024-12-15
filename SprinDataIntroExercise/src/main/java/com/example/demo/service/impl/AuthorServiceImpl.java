package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String AUTHORS_FILE_PATH="src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
      if(authorRepository.count() > 0){
          return;
      }

                       Files.readAllLines(Path.of(AUTHORS_FILE_PATH)).
                       forEach(row -> {

                       String[] fullName=row.split("\\s+");
                       Author author=new Author(fullName[0],fullName[1]);

                       authorRepository.save(author);
                   });
    }

    @Override
    public Author getRandomAuthor() {
        //Имаме authorRepositori - > т.е. всичко необходимо да намерим един Author
        //Kak Можем да намерим randomAuthor --> по ид
        // Рандом; Можем да ползваме Math.random, Което вдига инстанция от класа     Random random = new Random
        long randomId = ThreadLocalRandom.current().nextLong(1,authorRepository.count() + 1); // Мога да му задам долна и горна граница

        //Мога да пробвам с Math.random или създаване на инстанцията Radnom ranond = new Radnodm и от него next.Int

        return authorRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<String> getAllAuthorsOrderByCountOfTheirBooks() {
        return authorRepository.findAllByBooksSizeDESC()
                .stream().map(author ->
                        String.format("%s %s %d",author.getFirstName(),author.getLastName(),author.getBooks().size()))
                .collect(Collectors.toList());
    }
}
