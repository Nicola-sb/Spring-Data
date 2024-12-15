package bg.softuni.traingexampojo.service;


import bg.softuni.traingexampojo.entity.Seller;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface SellerService {
    
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException, JAXBException;

    Seller findById(Long id);
}
