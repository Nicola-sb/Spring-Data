package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarSeedDto;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private static final String PATH_FILE = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_FILE));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        StarSeedDto[] starSeedDtos = gson.fromJson(readStarsFileContent(), StarSeedDto[].class);

        for (StarSeedDto starSeedDto : starSeedDtos) {
            Optional<Star>optionalStar = starRepository.findByName(starSeedDto.getName());
            if(!validationUtil.isValid(starSeedDto) || optionalStar.isPresent()){
                stringBuilder.append("Invalid star\n");
                continue;
            }

            Star star = modelMapper.map(starSeedDto, Star.class);
            star.setStarType(starSeedDto.getStarType());
            star.setConstellation(this.constellationRepository.findById(starSeedDto.getConstellation()).get());
            starRepository.saveAndFlush(star);
            stringBuilder.append(String.format
                    ("Successfully imported star %s - %.2f light years\n",star.getName(),star.getLightYears()));
        }
        return stringBuilder.toString();
    }

    @Override
    public String exportStars() {

       return this.starRepository.findAllByStarTypeOrderByLightYears()
               .stream()
               .map( s -> String.format("Star: %s\n" +
                       "   *Distance: %.2f light years\n" +
                       "   **Description: %s\n" +
                       "   ***Constellation: %s\n",
                       s.getName(),s.getLightYears(),s.getDescription(),s.getConstellation().getName())).collect(Collectors.joining());
    }
}
