package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Seller;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findSellerByFirstNameAndLastName (String firstName, String lastName);
    Optional<Seller> findSellerByLastName (String lastName);
    Optional<Seller> findSellerByPersonalNumber (String personalNumber);

}
