package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerSeedDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String SELLER_FILE_PATH = "src/main/resources/files/json/sellers.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, SellerRepository sellerRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.sellerRepository = sellerRepository;
    }


    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files
                .readString(Path.of(SELLER_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder build = new StringBuilder();

        Arrays.stream(gson.fromJson(readSellersFromFile(), SellerSeedDto[].class))
                .filter(sellerSeedDto -> {
                    boolean isValid = validationUtil.isValid(sellerSeedDto);

                    Optional<Seller> sellerByName = sellerRepository
                            .findSellerByLastName(sellerSeedDto.getLastName());

                    Optional<Seller> sellerByNumber = sellerRepository
                            .findSellerByPersonalNumber(sellerSeedDto.getPersonalNumber());



                    if (sellerByName.isPresent() || sellerByNumber.isPresent()) {
                        isValid = false;
                    }
//
//                    Seller seller = sellerRepository.findSellerByFirstNameAndLastName(sellerSeedDto.getFirstName(),
//                            sellerSeedDto.getLastName()).orElse(null);
//                    if (seller != null) {
//                        isValid = false;
//                    }

                    build.append(isValid
                                    ? String.format("Successfully imported seller %s %s",
                                    sellerSeedDto.getFirstName(),
                                    sellerSeedDto.getLastName())
                                    : "Invalid seller")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(sellerSeedDto -> modelMapper.map(sellerSeedDto, Seller.class))
                .forEach(sellerRepository::save);

        return build.toString().trim();
    }

    @Override
    public Seller findSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    @Override
    public void saveAddedSaleToSeller(Seller seller) {
        sellerRepository.save(seller);
    }


}