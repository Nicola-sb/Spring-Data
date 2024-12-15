package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectByName(String name);

    List<Ingredient> selectByNames(List<String> namesIng);


    void deleteByName(String name);

    void updatePrice();
}
