package bg.softuni.traingexampojo.service.impl;

import bg.softuni.traingexampojo.entity.Seller;
import bg.softuni.traingexampojo.entity.dto.SellerSeedRootDto;
import bg.softuni.traingexampojo.repository.SellerRepository;
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
public class SellerServiceImpl implements SellerService {

    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

//        SellerSeedRootDto sellerSeedRootDto = xmlParser.fromFile(SELLERS_FILE_PATH,SellerSeedRootDto.class);
        xmlParser.fromFile(SELLERS_FILE_PATH,SellerSeedRootDto.class).getSellers()
                .stream().filter(sellerSeedDto -> {
                    boolean isValid = validationUtil.isValid(sellerSeedDto);
                    stringBuilder.append(isValid ? String.format("Successfully import seller %s - %s",
                    sellerSeedDto.getLastName(),sellerSeedDto.getEmail()) : "Invalid seller").append(System.lineSeparator());

                    return isValid;
                }).map(sellerSeedDto -> modelMapper.map(sellerSeedDto, Seller.class))
                .forEach(sellerRepository::save);


        return stringBuilder.toString();
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }
}
