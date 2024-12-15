package bg.codexio.customOrmDemo.entity;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

import java.time.LocalDate;
@Entity(tableName = "users")//ентитита от тип User ще се държавт в таблицата users

public class User {

    @Id//Ще анотираме филдът ид с анотацията Id
    private int id;
    @Column(name = "username", columnDefinition = "VARCHAR(100)")//Имаме колона;Колоната от тип username ще се казва username
    //( В последствие ще видим в ОРМ-ите,когато имената на колоните съвпадат, то ги взима автоматично)
    private String username;
    @Column(name = "age",columnDefinition = "INT")
    private int age;
    @Column(name = "registration",columnDefinition = "DATE")
    private LocalDate registration;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "address",columnDefinition = "VARCHAR(255)")
    private String address;

    public User() {
    }

    public User(String username, int age) {
        this.username=username;
        this.age=age;
        this.registration=LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }
}
