package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_PATH = "D:\\Работен плот\\Spring Data Projects\\Insta Alone\\src\\main\\resources\\files\\users.json";
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, PictureService pictureService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        UserSeedDto[] userSeedDtos = gson.fromJson(readFromFileContent(), UserSeedDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (UserSeedDto userSeedDto : userSeedDtos) {
//            boolean optPic = pictureService.isEntityExist(userSeedDto.getProfilePicture());
            // --> Може и по този начин като си извиквам pictureService - така е решено в оригиналното Instagraph
            boolean optPic = pictureRepository.existsByPath(userSeedDto.getProfilePicture());

            boolean isEntityExist = userRepository.existsByUsername(userSeedDto.getUsername());
            boolean isValid = validationUtil.isValid(userSeedDto) && !isEntityExist && optPic;


            stringBuilder.append(isValid ? String.format("Successfully import User: %s",userSeedDto.getUsername())
                    : "Invalid User").append(System.lineSeparator());

            if(isValid){
                User user = modelMapper.map(userSeedDto,User.class);
                user.setProfilePicture(pictureRepository.findPictureByPath(userSeedDto.getProfilePicture()).get());
                userRepository.save(user);
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return null;
    }
}
