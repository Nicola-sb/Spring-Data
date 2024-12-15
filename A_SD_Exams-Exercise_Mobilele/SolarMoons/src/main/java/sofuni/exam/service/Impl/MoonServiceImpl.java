package sofuni.exam.service.Impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.MoonDto;
import sofuni.exam.models.dto.MoonRootDto;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.service.MoonService;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.XmlParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class MoonServiceImpl implements MoonService {

    private static final String FILE_PATH = "src/main/resources/files/xml/moons.xml";
    private final MoonRepository moonRepository;
    private final  DiscovererRepository discovererRepository;
    private final PlanetRepository planetRepository;
    private final DiscovererService discovererService;
    private final PlanetService planetService;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public MoonServiceImpl(MoonRepository moonRepository, DiscovererRepository discovererRepository, PlanetRepository planetRepository, DiscovererService discovererService, PlanetService planetService, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.moonRepository = moonRepository;
        this.discovererRepository = discovererRepository;
        this.planetRepository = planetRepository;
        this.discovererService = discovererService;
        this.planetService = planetService;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return moonRepository.count() > 0;
    }

    @Override
    public String readMoonsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importMoons() throws IOException, JAXBException {
        MoonRootDto moonRootDto = xmlParser.fromFile(FILE_PATH, MoonRootDto.class);
        StringBuilder stringBuilder = new StringBuilder();

        for (MoonDto moonDto : moonRootDto.getMoonDtos()) {
            Optional<Moon> optionalMoon = moonRepository.findByName(moonDto.getName());

            if(!validationUtil.isValid(moonDto) || optionalMoon.isPresent()){
                stringBuilder.append("Invalid moon").append(System.lineSeparator());
            }else{


                Moon moon = modelMapper.map(moonDto, Moon.class);
//                Discoverer discoverer = discovererRepository.findById(moonDto.getDiscoverer()).get();
//                Planet planet = planetRepository.findById(moonDto.getPlanet()).get();
//                moon.setPlanet(planet);
//                moon.setDiscoverer(discoverer);
                Discoverer discoverer = discovererService.findById(moonDto.getDiscoverer());
                Planet planet = planetService.findById(moonDto.getPlanet());
                moon.setDiscoverer(discoverer);
                moon.setPlanet(planet);
                moonRepository.save(moon);

                stringBuilder.append(String.format("Successfully imported moon %s",moonDto.getName())).append(System.lineSeparator());
            }
        }






        return stringBuilder.toString();
    }

    @Override
    public String exportMoons() {
        StringBuilder stringBuilder = new StringBuilder();

        List<Moon>moons = moonRepository.findAllByTypeand();

        for (Moon moon : moons) {
            stringBuilder.append(String.format("***Moon %s is a natural satellite of %s and has a radius of %.2f km.%n" +
                            "****Discovered by %s %s%n",
                    moon.getName(),moon.getPlanet().getName(),moon.getRadius(),
                    moon.getDiscoverer().getFirstName(),moon.getDiscoverer().getLastName())).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
