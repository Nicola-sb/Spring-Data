package sofuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sofuni.exam.models.entity.Moon;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoonRepository  extends JpaRepository<Moon,Long> {


    Optional<Moon> findByName(String name);

    //Export ALL moons that orbits planets of type GAS_GIANT and have radius between 700km and 2000km. from the Database
    //•	Extract from the database, the moon name, the name of the planet it orbits, moon radius and moon discoverer.
    //•	Filter only moons whose radius is more than or equal to 700km and equal to or less than 2000km. Order the results by moon name in ascending order.
    //•	You have to round the value of the radius to the second decimal digit.
    //•	Return the information in this format

    List<Moon>findAllByRadiusGreaterThanEqualAndRadiusLessThanEqual(Double radius, Double radius2);

    @Query("SELECT m FROM Moon AS m JOIN Planet AS p ON p.id = m.planet.id WHERE p.type = 'GAS_GIANT' AND m.radius >= 700 AND m.radius <= 2000 ORDER BY m.name")
    List<Moon>findAllByTypeand();
}
