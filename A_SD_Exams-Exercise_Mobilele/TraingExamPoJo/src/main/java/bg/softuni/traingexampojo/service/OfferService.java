package bg.softuni.traingexampojo.service;


import jakarta.xml.bind.JAXBException;

import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface OfferService {

    boolean areImported();

    String readOffersFileContent() throws IOException;
	
	String importOffers() throws IOException, JAXBException;
}
