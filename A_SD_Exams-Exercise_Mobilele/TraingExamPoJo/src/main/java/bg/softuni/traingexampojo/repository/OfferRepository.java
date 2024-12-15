package bg.softuni.traingexampojo.repository;


import bg.softuni.traingexampojo.entity.Offfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfferRepository extends JpaRepository<Offfer,Long> {
    
}
