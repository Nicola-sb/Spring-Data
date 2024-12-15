package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;

import java.util.List;

public interface ShampooService {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, String size);

    List<Shampoo> findBySize(String size);

    List<Shampoo> findByIngredient(String ingredient);

    List<Shampoo> findByIngredient(List<String> ingredients);

    //    @Override
    //    public List<Shampoo> findBySizeOrLabelId(String size, long labelId) {
    //        Size parsed = Size.valueOf(size.toUpperCase());
    //
    //        return this.shampooRepository.findBySizeOrLabelId(parsed,labelId);
    //    }

        //3ta zadacha
    List<Shampoo> findWithPriceGreaterThan(String price);

    List<Shampoo> findBySizeOrLabelId(String size, long labelId);


    long countByPriceLessThan(String price);

    List<Shampoo> findWithIngredientCountLessThan(int count);
}
