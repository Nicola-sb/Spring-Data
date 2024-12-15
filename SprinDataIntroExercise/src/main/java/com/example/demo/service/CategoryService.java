package com.example.demo.service;

import com.example.demo.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();

//    void seedAuthors() throws IOException;
    //Ще бъде void метод, защото ще четем и ще записваме в базата; няма да връща нищо

    //Имплементираме този метод в нашата имплементация -> CategoryServiceImpl
}
