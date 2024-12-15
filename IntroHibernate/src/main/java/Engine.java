import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Engine implements Runnable{
    //Какво ще има нашия Engine? Идеята е да изнесем логиката и нашия Main метод да се използва единствено за стартова точка на нашата програма

    //Ще трябва да имаме и един private field - EntityManager, за да може всички методи да имат достъп до него
    //Направили сме го final, защото не се променя,получваме го наготово нашия EntityManager;няма да се смени,докато работим с нашия клас за това го направихме final
    private final EntityManager entityManager;
    private BufferedReader bufferedReader;
    //като съсъздаме  private final EntityManager entityManager ни свети в червено, защото ни казва final field трябва да се инициализира през Конструктора

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        //направихме си bufferedReader, защото ще трябва по някакъв начим да работим с нашия потребител; за момента знаем как се случва това през конзолата
    }
    //Имаме entityManger в констуктора с идеята, че искам да го получаваме отвън

    @Override
    public void run() {
        System.out.println("Select exercise number:");
        try {
          int exNum= Integer.parseInt(bufferedReader.readLine());

          switch (exNum){
              case 2 -> changeCasingEx2();
              case 3 -> containsEmployeeEx3();
              case 4 -> employeesWithSalaryover50000Ex4();
              case 5 -> employeesFromDepartmentEx5();
              case 6 -> addingNewAdrressAndUpdatingEmployeeEx6();
              case 7 -> addressWithEmployeeCountEx7();
              case 10 -> increaseSalariesEx10();
              case 12 -> employeesMaximumSalariesEx12();
              case 13 -> removeTownsEx13();
          }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            entityManager.close();
        }
    }

    private void removeTownsEx13() throws IOException {
        System.out.println("Enter town name:");
        String townName = bufferedReader.readLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :t_name",Town.class)
                .setParameter("t_name",townName)
                .getSingleResult();

        int affectedRows = removeAddressByTownId(town.getId());
//        entityManager.remove(town);
        System.out.printf("%d address in %s deleted",affectedRows,townName);

    }

    private int removeAddressByTownId(Integer id) {
        List<Address>addresses = entityManager.createQuery
                ("SELECT a FROM Address a "+
                        "WHERE a.town.id = :p_id",Address.class)
                        .setParameter("p_id",id)
                        .getResultList();

        entityManager.getTransaction().begin();
        addresses.forEach(entityManager::remove);
        entityManager.getTransaction().commit();

        return addresses.size();
    }

    @SuppressWarnings("unchecked")
    private void employeesMaximumSalariesEx12() {
        List<Object[]> rows = entityManager.createNativeQuery
                ("SELECT d.name,MAX(e.salary) as max_salary FROM departments as d " +
                        "JOIN employees AS e ON d.department_id = e.department_id " +
                        "GROUP BY d.name " +
                        "HAVING max_salary   NOT BETWEEN 30000 AND 70000")
                .getResultList();

    }

    private void increaseSalariesEx10() {
        entityManager.getTransaction().begin();
        int affectedRows = entityManager.createQuery("UPDATE Employee e " +
                "SET e.salary = e.salary * 1.2 " +
                "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1,2,4,11)).executeUpdate();

        entityManager.getTransaction().commit();
        System.out.println(affectedRows);
    }

    private void addressWithEmployeeCountEx7() {
        List<Address>addresses = entityManager
                .createQuery("SELECT a FROM Address a"+
                        " ORDER BY a.employees.size DESC",Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(address -> {
            System.out.printf("%s, %s - %d employees %n",address.getText()
                    ,address.getTown() == null ? "Unknown" : address.getTown().getName()
                    ,address.getEmployees().size());
        });
    }

    private void addingNewAdrressAndUpdatingEmployeeEx6() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = bufferedReader.readLine();

        Employee employee = entityManager.createQuery
                 ("SELECT e FROM Employee e "+
                "WHERE e.lastName = :l_name",Employee.class)
                .setParameter("l_name",lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 15");

        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    private void employeesFromDepartmentEx5() {
        List<Employee>employees = entityManager.createQuery("SELECT e FROM Employee e"+
                                                          " WHERE e.department.name = :d_name "+
                                                          " ORDER BY e.salary, e.id",Employee.class)
                                                         .setParameter("d_name","Research and Development")
                                                         .getResultList();
        employees.forEach(employee -> {
            System.out.printf("%s %s from %s - $%.2f%n",employee.getFirstName(),
                                                                                employee.getLastName(),
                                                                                employee.getDepartment().getName(),
                                                                                employee.getSalary());
        });
//        entityManager.createQuery("SELECT e FROM Employee e" +
//                                  " WHERE e.department.name = :d_name"+
//                                  " ORDER BY e.salary, e.id",Employee.class)
//                                  .setParameter("d_name","Research and Development")
//                                  .getResultStream()
//                                  .forEach(employee -> {
//                                      System.out.printf("%s %s from %s - $%.2f%n",employee.getFirstName(),
//                                                                                employee.getLastName(),
//                                                                                employee.getDepartment().getName(),
//                                                                                employee.getSalary());
//                                  });
    }

    private void employeesWithSalaryover50000Ex4() {
//        entityManager.createQuery("SELECT e FROM Employee e" +
////                                    " WHERE e.salary > :min_salary",Employee.class)
////                                    .setParameter("min_salary", BigDecimal.valueOf(50000L))
////                                    .getResultStream().map(Employee::getFirstName).forEach(System.out::println);
        entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.salary > :min_salary",Employee.class)
                .setParameter("min_salary", new BigDecimal(50000L))
                .getResultStream().map(Employee::getFirstName).forEach(System.out::println);
    }

    private void containsEmployeeEx3() throws IOException {
        System.out.println("Enter employee full name:");
        String[] fullName=bufferedReader.readLine().split("\\s+");

        String firstName=fullName[0];
        String lastName=fullName[1];
//        Employee employee = entityManager.createQuery("SELECT e FROM Employee e",Employee.class).getSingleResult();
        Long singleResult = entityManager.createQuery("SELECT count(e) FROM Employee e "
                        + " WHERE e.firstName = :f_name AND e.lastName = :l_name", Long.class)
                .setParameter("f_name", firstName)
                .setParameter("l_name", lastName)
                .getSingleResult();

        System.out.println(singleResult == 0 ? "No" : "Yes");

    }

    private void changeCasingEx2() {
//Имайки вече нашия EntityManger вече имам достъп до онези класове: getTransaction()
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t "
                + "SET t.name = upper(t.name) " +
                "WHERE length(t.name) <= 5");

//        int affectedRows=query.executeUpdate();
        System.out.println(query.executeUpdate());
        entityManager.getTransaction().commit();

    }
}
