package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostSeedDto;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private static final String POSTS_FILE_PATH = "D:\\Работен плот\\Spring Data Projects\\Insta Alone\\src\\main\\resources\\files\\posts.xml";

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, PictureRepository pictureRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POSTS_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        PostSeedRootDto postSeedRootDto = xmlParser.fromFile(POSTS_FILE_PATH, PostSeedRootDto.class);
        StringBuilder stringBuilder = new StringBuilder();

        for (PostSeedDto postDto : postSeedRootDto.getPosts()) {
            //f the picture or user is not in the database, do not add the post to the database and return "Invalid Post"
            Optional<Picture> isPictureInDb = pictureRepository.findPictureByPath(postDto.getPicture().getPath());
            boolean isUserinDb = userRepository.existsByUsername(postDto.getUser().getUsername());
            boolean isValid = validationUtil.isValid(postDto) && isUserinDb && isPictureInDb.isPresent();

           stringBuilder.append(isValid ? String.format("Successfully import Post made by: %s",postDto.getUser().getUsername())
                   : "Invalid Post").append(System.lineSeparator());

           if(isValid){
               Post post = modelMapper.map(postDto,Post.class);
               post.setPicture(pictureRepository.findPictureByPath(post.getPicture().getPath()).get());
               post.setUser(userRepository.findByUsername(postDto.getUser().getUsername()));

               postRepository.save(post);

           }

        }

        return stringBuilder.toString();
    }
}
