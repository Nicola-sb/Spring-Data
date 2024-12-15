package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    //Нашият CategoryService за да може да работи се нуждае от

    private static final String CATEGORIES_FILE_PATH="src/main/resources/files/categories.txt";
//    private static final String AUTHORS_FILE_PATH="src/main/resources/files/authors.txt";

    private final CategoryRepository categoryRepository;
    //правя си   private final CategoryRepository categoryRepository и ще го  инджектна през Конструктора
    //Да му кажам: Аз имам нужда от този констурктор
    // ДАЙ МИ categoryRepository ЗАЩОТО АЗ ИМАМ НУЖДА ОТ НЕГО ЗА ДА ЗАПИСВАМ В БАЗАТА

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0){
            return;
        }
        //Как можем да прочетем файловете(които се намирам в resources-> files
        //Имам един метод Files.readAllLines които се нуждат от един път(path) Path.of(), които се нуждае от стринг
        //Десен бутон върху категориите и CopyPath -> Path From Content Root
        //ЗАЩО? ЗА да можем нашият проект независимо дали го рънваме при нас или при наш колега, когато имам подобно структура на проложението той да рънва адекватно
        //Понеже пътя(path) е константа,няма да се промени -> Можем да си го сложим в една константа - private static final String
         Files.readAllLines(Path.of(CATEGORIES_FILE_PATH)).
                stream().
                filter(row -> !row.isEmpty()).
                 forEach(categoryName -> {
                     //Създавам си нова категория(Category)
                     //Редовете, които получвам след филтрацията и форийча са реално имената на Категориите
                     // и мога директно в new Category() да му подам categoryName и да му кажа -> това ти е името на категорията
                     Category category = new Category(categoryName);
                     //Като му дам Alt+Entar за създаването на нов Конструктор да внимавам ДА НЕ МИ ГО АДНЕ КАТО ПАРАМЕТЪР(Трябав да бъде нов констуркор)

                     categoryRepository.save(category);
                 });
        System.out.println();

    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category>categories = new HashSet<>();
        //Трябва да решим колко категории да си имаме -> за това си правим един рандомИнт randInt и ще си пуснем фор цикъл по него
        int randInt = ThreadLocalRandom.current().nextInt(1,3);

        long catRepoCount = categoryRepository.count();

        for(int i=0 ;i<randInt ;i++){
            //Тук когато кажем Рандом категории ще ми дава от 1 до 3 след което ще ми трябва рандомИд
            //Всеки път искам цикъла да се върти за да бъде различен
            long randomId = ThreadLocalRandom.current().nextLong(1,catRepoCount + 1);

            Category category = categoryRepository.findById(randomId).orElse(null);
            //Намери ми от categoryRepository

            categories.add(category);
        }
        return categories;
    }
    //Ako ne му сложа @Service и имаме само имплементация на интерфейса, но по никакъв начин не сме казали на Spring, че той трябва да се грижи
    //за този Service и при поискване на този Service да ни го инджектне през Конструктора.
    //Това ще стане чрез анотацията Service
    //По този начин Spring ще се грижи за него ще менажира CategoryServiceImpl и когато някъде ние поискаме Обект от типа CategoryService той ще го инджектне за нас
//
//    @Override
//    public void seedAuthors() throws IOException {
//           List<String>authors=Files.readAllLines(Path.of(AUTHORS_FILE_PATH)).
//                   stream().forEach(author -> {
//                       Author author1=new Author(author);
//                   });
//
//    }



}
