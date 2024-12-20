package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Country;

import java.util.Optional;

//ToDo:
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByName(String name);

    Country findCountryById(Long id);
}
