package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.DiscovererDto;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class DiscovererServiceImpl implements DiscovererService {

    private static final String FILE_PATH = "src/main/resources/files/json/discoverers.json";
    private final DiscovererRepository discovererRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public DiscovererServiceImpl(DiscovererRepository discovererRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.discovererRepository = discovererRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return discovererRepository.count() > 0;
    }

    @Override
    public String readDiscovererFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importDiscoverers() throws IOException {
        DiscovererDto[] discovererDtos = gson.fromJson(readDiscovererFileContent(), DiscovererDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (DiscovererDto discovererDto : discovererDtos) {
            Optional<Discoverer> optionalDiscoverer = discovererRepository.findByFirstNameAndLastName(discovererDto.getFirstName(),discovererDto.getLastName());

            if(!validationUtil.isValid(discovererDto) || optionalDiscoverer.isPresent()){
                stringBuilder.append("Invalid discoverer").append(System.lineSeparator());
            }else{
                Discoverer discoverer = modelMapper.map(discovererDto, Discoverer.class);
                discovererRepository.save(discoverer);

                stringBuilder.append(String.format("Successfully imported discoverer %s %s",discovererDto.getFirstName(),discovererDto.getLastName())).append(System.lineSeparator());

            }

        }

        return stringBuilder.toString();
    }

    @Override
    public Discoverer findById(Long discoverer) {
        return discovererRepository.findById(discoverer).orElse(null);
    }
}
