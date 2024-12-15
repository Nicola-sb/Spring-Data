package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.Optional;
import java.util.Set;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano,Long> {

    Optional<Volcano> findByName(String name);

//•	Filter only volcanoes that are active with an elevation of more than 3000m. and have information about the last eruption.
// Order the results descending by elevation•

//    Set<Volcano>findAllByActiveIsTrueAndElevationGreaterThanAndLastEruptionIsNotEmptyOrderByElevationDesc(int elevation);
//    Set<Volcano>findAllByActiveIsTrueAndElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(int elevation);

//    Set<Volcano>findByElevationGreaterThanAndActiveIsTrueAndLastEruptionIsNotNullOrderByElevationDesc(int elevation);


//Export all active volcanoes with elevation above 3000m. and information about the last eruption from the Database
//•	Extract from the database, the volcano name, location (country name) and the date of last eruption.
//•	Filter only volcanoes that are active with an elevation of more than 3000m.
// and have information about the last eruption. Order the results descending by elevation.
    @Query(value = "SELECT * FROM volcanoes WHERE elevation > 3000" +
            " AND last_eruption IS NOT NULL AND is_active = 1 ORDER BY elevation DESC", nativeQuery = true)
    Set<Volcano>findByElevationGreaterThanAndActiveIsTrueAndLastEruptionIsNotNullOrderByElevationDesc();


//    @Query(value = "SELECT * FROM  volcanoes WHERE elevation > 3000 AND last_eruption is NOT NULL AND is_active = 1 ORDER BY elevation DESC",nativeQuery = true)
    @Query("SELECT  v FROM Volcano AS v  WHERE v.elevation > 3000 AND  v.lastEruption IS NOT NULL  AND v.isActive = true ORDER BY v.elevation DESC")
    Set<Volcano>findvoldanosAlone();

    @Query(value = "SELECT * FROM volca",nativeQuery = true)
    Set<Volcano>findaVoldla();

    @Query("SELECT v FROM Volcano as v WHERE v.elevation > 3000 AND v.lastEruption != null AND v.isActive = true ORDER BY v.elevation DESC ")
    Set<Volcano> findAlalaa();


//    Set<Volcano>



}
