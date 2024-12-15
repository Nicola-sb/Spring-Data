package com.example.demo.service.impl;

import com.example.demo.model.entity.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.service.CategoryService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category>categories=new HashSet<>();

        int randomInt= ThreadLocalRandom.current().nextInt(1,3);

        long catRepoCount = categoryRepository.count();

        for(int i=0 ;i<randomInt ;i++){
           long randomIdLong = ThreadLocalRandom.current().nextLong(1,catRepoCount + 1);
           Category category = categoryRepository.findById(randomIdLong).orElse(null);

           categories.add(category);
        }
        return categories;
    }
}
