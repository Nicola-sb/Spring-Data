package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceSeedRootDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.enums.DeviceType;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String DEVICE_FILE_PATH = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private final SaleService saleService;

    public DeviceServiceImpl(DeviceRepository deviceRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, SaleService saleService) {
        this.deviceRepository = deviceRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.saleService = saleService;
    }

    @Override
    public boolean areImported() {
        return deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files
                .readString(Path.of(DEVICE_FILE_PATH));
    }

    @Override
    public String importDevices() throws JAXBException {

        StringBuilder build = new StringBuilder();

        xmlParser
                .fromFile(DEVICE_FILE_PATH, DeviceSeedRootDto.class)
                .getDeviceSeedDtos()
                .stream()
                .filter(deviceSeedDto -> {
                    boolean isValid = validationUtil.isValid(deviceSeedDto);


                    Sale sale = saleService.findSaleById(deviceSeedDto.getSale());

                    if (sale == null) {
                        isValid = false;
                    }


                    Device device = deviceRepository.findDeviceByModel(deviceSeedDto.getModel())
                            .orElse(null);
                    if (device != null) {
                        isValid = false;
                    }

                    build
                            .append(isValid
                                    ? String.format("Successfully imported device of type %s with brand %s",
                                    deviceSeedDto.getDeviceType(),
                                    deviceSeedDto.getBrand())
                                    : "Invalid device")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(deviceSeedDto -> {

                    Device device = modelMapper.map(deviceSeedDto, Device.class);

                    Sale sale = saleService.findSaleById(deviceSeedDto.getSale());
                    saleService.addAndSaveAddedDevice(sale, device);

                    device.setSale(sale);
                    return device;

                })
                .forEach(deviceRepository::save);

        return build.toString();
    }

    @Override
    public String exportDevices() {
        StringBuilder build = new StringBuilder();

        List<Device> foundDevices = deviceRepository.findAllSmartPhonesCheaperThan1000AndStorageMoreThan128();

        foundDevices.forEach(v -> {
            build.append(String.format("Device brand: %s\n" +
                                    "   *Model: %s\n" +
                                    "   **Storage: %d\n" +
                                    "   ***Price: %.2f",
                            v.getBrand(),
                            v.getModel(),
                            v.getStorage(),
                            v.getPrice()))
                    .append(System.lineSeparator());

        });
        return build.toString();
    }
}
