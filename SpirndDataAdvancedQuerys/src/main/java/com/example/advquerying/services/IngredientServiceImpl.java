package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public List<Ingredient> selectByName(String name) {
        return this.ingredientsRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Ingredient> selectByNames(List<String> ingredients) {
        return this.ingredientsRepository.findByNameInOrderByPrice(ingredients);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        this.ingredientsRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void updatePrice(){
        this.ingredientsRepository.updateAllPrice();
    }

}
