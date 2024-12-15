package bg.softuni.traingexampojo.service.impl;

import bg.softuni.traingexampojo.entity.Car;
import bg.softuni.traingexampojo.entity.dto.CarSeedDto;
import bg.softuni.traingexampojo.repository.CarRepository;
import bg.softuni.traingexampojo.service.CarService;
import bg.softuni.traingexampojo.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private static final String RESOURCES_CAR_FILE_PATH = "src/main/resources/files/json/cars.json";


    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(RESOURCES_CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
       Arrays.stream(gson.fromJson(readCarsFileContent(), CarSeedDto[].class))
                .filter(carSeedDto -> {
                    boolean isValid = validationUtil.isValid(carSeedDto);
                    stringBuilder.append(isValid ? String.format("Successfully imported car - %s - %s"
                    ,carSeedDto.getMake(),carSeedDto.getModel()) : "Invalid car").append(System.lineSeparator());
                    return isValid;
                }).map(carSeedDto -> modelMapper.map(carSeedDto,Car.class)).forEach(carRepository::save);




//        List<Car> cars = Arrays.stream(carSeedDtos).filter(validationUtil::isValid)
//                .map(carSeedDto -> modelMapper.map(carSeedDto,Car.class)).collect(Collectors.toList());

        return stringBuilder.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
          StringBuilder stringBuilder = new StringBuilder();

          carRepository.findAllCarsOrderByPicturesCountThanByMake().forEach(car -> {
                      stringBuilder.append(String.format("Car make - %s, model - %s\n" +
                              "\tKilometers - %d\n" +
                              "\tRegistered on - %s\n" +
                              "\tNumber of pictures - %d",
                              car.getMake(),
                              car.getModel(),car.getKilometers(),car.getRegisteredOn(),car.getPictures().size())).append(System.lineSeparator());
                  });

        return stringBuilder.toString();
    }

    @Override
    public Car findById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }
}
