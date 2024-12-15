package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {


    private static final String PICTURES_FILE_PATH ="D:\\Работен плот\\Spring Data Projects\\Insta Alone\\src\\main\\resources\\files\\pictures.json";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        PictureSeedDto[] pictureSeedDtos = gson.fromJson(readFromFileContent(), PictureSeedDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (PictureSeedDto pictureSeedDto : pictureSeedDtos) {
            Optional<Picture> optPicture = pictureRepository.findPictureByPath(pictureSeedDto.getPath());

            boolean isValid = validationUtil.isValid(pictureSeedDto) && optPicture.isEmpty();

            stringBuilder.append(isValid ? String.format("Successfully import Picture,with size %.2f"
            ,pictureSeedDto.getSize())
                    : "Invalid Picture").append(System.lineSeparator());

            if(isValid){
                Picture picture = modelMapper.map(pictureSeedDto,Picture.class);
                pictureRepository.save(picture);
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String exportPictures() {
        return null;
    }

    @Override
    public boolean isEntityExist(String path) {
        return pictureRepository.existsByPath(path);
    }
}
