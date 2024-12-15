package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.PlanetDto;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    private static final String FILE_PATH = "src/main/resources/files/json/planets.json";
    private final PlanetRepository planetRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public PlanetServiceImpl(PlanetRepository planetRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.planetRepository = planetRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return planetRepository.count() > 0;
    }

    @Override
    public String readPlanetsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPlanets() throws IOException {
        PlanetDto[] planetDtos = gson.fromJson(readPlanetsFileContent(), PlanetDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (PlanetDto planetDto : planetDtos) {
            Optional<Planet> optionalPlanet = planetRepository.findByName(planetDto.getName());
            if(!validationUtil.isValid(planetDto) || optionalPlanet.isPresent()){
                stringBuilder.append("Invalid planet").append(System.lineSeparator());
            }else{
                Planet planet = modelMapper.map(planetDto, Planet.class);
                planetRepository.save(planet);

                stringBuilder.append(String.format("Successfully imported planet %s",planetDto.getName())).append(System.lineSeparator());

            }
        }


        return stringBuilder.toString();
    }

    @Override
    public Planet findById(Long planet) {
        return planetRepository.findById(planet).orElse(null);
    }
}
