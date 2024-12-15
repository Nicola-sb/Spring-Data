package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceSeedDto;
import softuni.exam.models.dto.SaleSeedDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.SaleService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private static final String SALE_FILE_PATH = "src/main/resources/files/json/sales.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private SaleRepository saleRepository;

    private DeviceRepository deviceRepository;

    private SellerService sellerService;

    public SaleServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, SaleRepository saleRepository, DeviceRepository deviceRepository, SellerService sellerService) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.saleRepository = saleRepository;
        this.deviceRepository = deviceRepository;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of(SALE_FILE_PATH));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder build = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readSalesFileContent(), SaleSeedDto[].class))
                .filter(saleSeedDto -> {
                    boolean isValid = validationUtil.isValid(saleSeedDto);

                    Optional<Sale> bySaleName = saleRepository.findSaleByNumber(saleSeedDto.getNumber());

                    if (bySaleName.isPresent()) {
                        isValid = false;
                    }

//                    Sale sale = saleRepository.findSaleByNumber(saleSeedDto.getNumber()).orElse(null);
//                    if(sale != null) {
//                        isValid = false;
//                    }

                    build.append(isValid
                                    ? String.format("Successfully imported sale with number %s"
                                    , saleSeedDto.getNumber())
                                    : "Invalid sale")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(saleSeedDto -> {

                    Sale sale = modelMapper.map(saleSeedDto, Sale.class);

                    Seller seller = sellerService.findSellerById(saleSeedDto.getSeller());

                        seller.getSales().add(sale);
                        sellerService.saveAddedSaleToSeller(seller);


                    sale.setSeller(seller);
                    return sale;
                })
                .forEach(saleRepository::save);

        return build.toString();
    }

    @Override
    public Sale findSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }

    @Override
    public void addAndSaveAddedDevice(Sale sale, Device device) {

        sale.getDevices().add(device);
        saleRepository.save(sale);
    }


    @Override
    public String exportSales() {
        return null;
    }
}