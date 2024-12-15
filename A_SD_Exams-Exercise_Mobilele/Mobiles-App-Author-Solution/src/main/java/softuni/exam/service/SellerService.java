package softuni.exam.service;

import softuni.exam.models.entity.Seller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

// TODO: Implement all methods
public interface SellerService {

    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException;

    Seller findSellerById(Long sellerId); //may cause error

    void saveAddedSaleToSeller(Seller seller);

//    String exportSellers();
}
