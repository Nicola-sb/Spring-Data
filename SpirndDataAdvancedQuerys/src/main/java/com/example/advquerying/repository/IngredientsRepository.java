package com.example.advquerying.repository;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient,Long> {

    List<Ingredient> findByNameStartsWith(String name);

    List<Ingredient> findByNameInOrderByPrice(List<String> ingredients);

    void deleteByName(String name);

    @Query("UPDATE Ingredient AS i "+
            "SET i.price = i.price * 1.10")
    @Modifying
    void updateAllPrice();
}
