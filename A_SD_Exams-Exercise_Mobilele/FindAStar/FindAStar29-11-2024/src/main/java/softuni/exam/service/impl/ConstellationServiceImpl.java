package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String PATH_FILE = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtill;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtill) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtill = validationUtill;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(PATH_FILE));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ConstellationSeedDto[] constellationSeedDtos = this.gson.fromJson(new FileReader(PATH_FILE), ConstellationSeedDto[].class);

        for (ConstellationSeedDto constellationSeedDto : constellationSeedDtos) {
            Optional<Constellation> optionalConstellation = this.constellationRepository.findByName(constellationSeedDto.getName());

            if(!validationUtill.isValid(constellationSeedDto) || optionalConstellation.isPresent()){
                stringBuilder.append("Invalid constellation\n");
                continue;
            }

            Constellation constellation = modelMapper.map(constellationSeedDto, Constellation.class);
            this.constellationRepository.saveAndFlush(constellation);
            stringBuilder.append(String.format
                    ("Successfully imported constellation %s - %s\n",constellation.getName(),constellation.getDescription()));
        }


        return stringBuilder.toString();
    }
}
