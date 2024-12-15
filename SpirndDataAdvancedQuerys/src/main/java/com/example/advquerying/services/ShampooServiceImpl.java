package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repository.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, String size) {
        //Трябва да преобразуваме от този Стринг размер към Енумерация
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findByBrandAndSize(brand,parsed);
    }

    @Override
    public List<Shampoo> findBySize(String size) {
        Size parsed = Size.valueOf(size.toUpperCase());

        return this.shampooRepository.findBySizeOrderByIdDesc(parsed);
    }

    @Override
    public List<Shampoo> findByIngredient(String ingredient) {

        return this.shampooRepository.findBYIngredient(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredient(List<String> ingredients) {
        return this.shampooRepository.findBYIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelId(String size, long labelId) {
        Size parsed = Size.valueOf(size.toUpperCase());

        return this.shampooRepository.findBySizeOrLabelId(parsed,labelId);
    }
    //3ta zadacha
    @Override
    public List<Shampoo> findWithPriceGreaterThan(String price){
        BigDecimal parsed = new BigDecimal(price);

        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(parsed);
    }

    @Override
    public long countByPriceLessThan(String price) {
        BigDecimal parsed = new BigDecimal(price);

        return shampooRepository.countByPriceLessThan(parsed);
    }

    @Override
    public List<Shampoo> findWithIngredientCountLessThan(int count) {
        return shampooRepository.findByIngredientCountLessThan(count);
    }

}
