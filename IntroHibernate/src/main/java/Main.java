import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        //За да си създамем  entityManger първо трявба да си създаме  EntityManagerFactory
        //За да го създаден използвам статичния метод от класа Persistence
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("softuni");

        EntityManager entityManager = emf.createEntityManager();

        Engine engine=new Engine(entityManager);
        engine.run();
        //нашия entityManager клас няма да прави нищо повече освен да каже run() на един клас
        //т.е. създаваме си един нов клас (Engine), който ще имплементира интерфейса Runnable(който има един метод - run)
        //Какво ще има нашият Engine? ---> Идеята е да изнесем логиката и нашия Main метод да си използва единствено за стартова точка на нашата програма
    }
}
