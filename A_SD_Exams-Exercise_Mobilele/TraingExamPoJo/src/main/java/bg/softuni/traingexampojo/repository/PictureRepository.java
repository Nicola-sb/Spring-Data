package bg.softuni.traingexampojo.repository;

import bg.softuni.traingexampojo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {

}
