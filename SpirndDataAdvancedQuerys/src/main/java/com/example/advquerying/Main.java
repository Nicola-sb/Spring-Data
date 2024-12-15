package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
//        String size = scanner.nextLine(); // 1ва задача
//        for (Shampoo shampoo: this.shampooService.findBySize( size)) {
//            System.out.println(shampoo);
//        }


//        String size = scanner.nextLine();
//        long labelId = Long.parseLong(scanner.nextLine());
//                for (Shampoo shampoo: this.shampooService.findBySizeOrLabelId(size,labelId)) {
//            System.out.println(shampoo);
//        }

//        String nextLine=scanner.nextLine();
//        List<String> ingredients = new ArrayList<>();
//        while (nextLine.isBlank()){
//            ingredients.add(nextLine);
//            nextLine = scanner.nextLine();
//        }
//        for (Shampoo shampoo: this.shampooService.findByIngredient(ingredients)) {
//            System.out.println(shampoo);
//        }

        //4ta zadacha
//        String name=scanner.nextLine();
//
//        for (Ingredient ingredient : ingredientService.selectByName(name)) {
//            System.out.println(ingredient);
//        }

        //5ta
//        String nextLine=scanner.nextLine();
//        List<String> ingredients = new ArrayList<>();
//        while (!nextLine.isBlank()){
//            ingredients.add(nextLine);
//            nextLine=scanner.nextLine();
//        }
//        for (Ingredient ingredient : this.ingredientService.selectByNames(ingredients)) {
//            System.out.println(ingredient);
//        }

//6.	Count Shampoos by Price
//Create a method that counts all shampoos with price lower than a given price.
//        String price = scanner.nextLine();
//        System.out.println(shampooService.countByPriceLessThan(price));

        //8. Select Shampoos by Ingredients Count
        //Create a method that selects all shampoos with ingredients less than a given number.
       int count = Integer.parseInt(scanner.nextLine());

      for(Shampoo shampoo : this.shampooService.findWithIngredientCountLessThan(count)){
          System.out.println(shampoo);
      }

        //9. Delete Ingredients by Name
        //Create a method that deletes ingredients by a given name. Use named query
//        String name=scanner.nextLine();
//        this.ingredientService.deleteByName(name);

        //10.	Update Ingredients by Price
        //Create a method that increases the price of all ingredients by 10%. Use named query.
//        this.ingredientService.updatePrice();
    }
}
