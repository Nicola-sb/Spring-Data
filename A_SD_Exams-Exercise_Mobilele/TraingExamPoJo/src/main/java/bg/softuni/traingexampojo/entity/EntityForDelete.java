package bg.softuni.traingexampojo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "za_triene_bace")
public class EntityForDelete extends BaseEntity{

    private String firstName;
    private String lastName;
}
