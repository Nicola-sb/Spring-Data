package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.service.AuthorService;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH="src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author getRandomAuthor() {
      long randomId= ThreadLocalRandom.current().nextLong(1,authorRepository.count() + 1);

      return authorRepository.findById(randomId).orElse(null);
    }
}
