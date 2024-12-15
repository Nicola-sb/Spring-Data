package com.example.advquerying.repository;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

    public interface ShampooRepository extends JpaRepository<Shampoo,Long> {

        List<Shampoo> findByBrand(String brand);

        List<Shampoo> findByBrandAndSize(String brand, Size size);

        List<Shampoo> findBySizeOrderByIdDesc(Size parsed);

        @Query(value = "SELECT s FROM Shampoo AS s "+
                         "JOIN s.ingredients AS i " +
                          "WHERE i.name = :name")
        List<Shampoo> findBYIngredient(@Param("name")String ingredient);

        @Query(value = "SELECT s FROM Shampoo AS s "+
                "JOIN s.ingredients AS i " +
                 "WHERE i IN :ingredients")
        List<Shampoo> findBYIngredients(List<String> ingredients);

        List<Shampoo> findBySizeOrLabelId(Size parsed, long labelId);
        //Когато имаме методи, те търсят по позиция ( Не е задължително имената ни в скобите да съвпадат)

        //3та задача
        List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

        //6.ta
        long countByPriceLessThan(BigDecimal price);

        @Query("SELECT s FROM Shampoo AS s WHERE s.ingredients.size < :count")
        List<Shampoo> findByIngredientCountLessThan(int count);
    }
