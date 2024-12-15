package bg.softuni.traingexampojo.service.impl;

import bg.softuni.traingexampojo.entity.Offfer;
import bg.softuni.traingexampojo.entity.dto.OfferSeedRootDto;
import bg.softuni.traingexampojo.repository.OfferRepository;
import bg.softuni.traingexampojo.service.CarService;
import bg.softuni.traingexampojo.service.OfferService;
import bg.softuni.traingexampojo.service.SellerService;
import bg.softuni.traingexampojo.util.ValidationUtil;
import bg.softuni.traingexampojo.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private final CarService carService;
    private final SellerService sellerService;

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public OfferServiceImpl(CarService carService, SellerService sellerService, OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.carService = carService;
        this.sellerService = sellerService;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        StringBuilder stringBuilder = new StringBuilder();

        xmlParser.fromFile(OFFERS_FILE_PATH,OfferSeedRootDto.class).getOffers().stream()
                .filter( offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto);
                    stringBuilder.append( isValid ? String.format("Successfully import offer %s - %s",
                            offerSeedDto.getAddedOn(),offerSeedDto.getHasGoldStatus())
                            : "Invalid offer").append(System.lineSeparator());

                    return isValid;
                }).map(offerSeedDto -> {
                    Offfer offfer = modelMapper.map(offerSeedDto,Offfer.class);
                    offfer.setCar(carService.findById(offerSeedDto.getCar().getId()));
                    offfer.setSeller(sellerService.findById(offerSeedDto.getSeller().getId()));

                    return offfer;
                }).forEach(offerRepository::save);


    //        OfferSeedRootDto offerSeedRootDto = xmlParser.fromFile(OFFERS_FILE_PATH,OfferSeedRootDto.class); за дебъгване

        return stringBuilder.toString();
    }
}
