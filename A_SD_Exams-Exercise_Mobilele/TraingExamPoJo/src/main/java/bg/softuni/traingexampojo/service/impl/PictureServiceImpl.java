package bg.softuni.traingexampojo.service.impl;

import bg.softuni.traingexampojo.entity.Picture;
import bg.softuni.traingexampojo.entity.dto.PictureSeedDto;
import bg.softuni.traingexampojo.repository.PictureRepository;
import bg.softuni.traingexampojo.service.CarService;
import bg.softuni.traingexampojo.service.PictureService;
import bg.softuni.traingexampojo.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public PictureServiceImpl(PictureRepository pictureRepository, CarService carService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
//        PictureSeedDto[]  pictureSeedDtos = gson.fromJson(readPicturesFromFile(),PictureSeedDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readPicturesFromFile(),PictureSeedDto[].class))
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto);
                    stringBuilder.append(isValid ? String.format("Successfully import picture - %s",
                            pictureSeedDto.getName()) : "Invalid picture").append(System.lineSeparator());
                    return isValid;
                }).map( pictureSeedDto -> {
                    Picture picture = modelMapper.map(pictureSeedDto,Picture.class);
                    picture.setCar(carService.findById(pictureSeedDto.getCarId()));
                    return picture;
                }).forEach(pictureRepository::save);

        return stringBuilder.toString();
    }
}
