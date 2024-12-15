package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction,Long> {
    Optional<Attraction> findByName(String name);

//    @Query("SELECT  FROM ")
//    Set<Attraction>findAllByNameAndDescriptionAndElevation(String name, String description, int elevation, Country country);

//    Set<Attraction>findAllByNameAndDescriptionAndElevationIsGreaterThanOrderByNameAscCountryAsc(String name,String description,int metres,Country country);

    //•	Filter only attractions that represent a historical or archaeological site with an elevation equal to or higher than 300m.
    // Order the results first by attraction name ascending and then by country name in ascending order.
//    Set<Attraction> findAllByTypeContainsElevationGreaterThanOrderByNameAscCountry(int metres);
//    Set<Attraction> findAllByTypeContainsAndElevationGreaterThanOrderByNameAscCountryAsc(String type,int metres);
//    Set<Attraction>findAllByTypeContainingAndElevationGreaterThanOrderByNameAscCountryAsc(String type,int metres);
//    List<Attraction> findAllByTypeInAndElevationGreaterThanEqualOrderByNameAscCountryNameAsc(
//            List<String> types,
//            int elevation
//    );

    Optional<Attraction> findAttractionByName(String name);
    Attraction findAttractionById(Long id);

    List<Attraction> findAttractionByTypeAndTypeAndElevationGreaterThanEqual(String type1, String type2, int elevation);

    @Query("select a from Attraction a join Country c on c.id = a.country.id " +
            "join Visitor v on v.attraction.id = a.id " +
            "where a.type like 'historical site' or a.type like 'archaeological site'" +
            "and a.elevation >= 300 " +
            "group by a.id, a.name, c.name " +
            "order by a.name, c.name") //COUNT(v.id) DESC,
    List<Attraction> findAttractionByTypeAndElevationMoreThanOrEqualTo300();
}
