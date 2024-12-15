package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//ToDo - Implement all the methods
@Service

public class AttractionServiceImpl implements AttractionService {
    private static final String FILE_PATH = "src/main/resources/files/json/attractions.json";
    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AttractionServiceImpl(AttractionRepository attractionRepository, CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAttractions() throws IOException {
        AttractionDto[] attractionDtos = gson.fromJson(readAttractionsFileContent(), AttractionDto[].class);

        StringBuilder stringBuilder = new StringBuilder();
        for (AttractionDto attractionDto : attractionDtos) {
            Optional<Attraction> optionalAttraction = attractionRepository.findByName(attractionDto.getName());

            if(!validationUtil.isValid(attractionDto) || optionalAttraction.isPresent()){
                stringBuilder.append("Invalid attraction").append(System.lineSeparator());
            }else{
                stringBuilder.append(String.format("Successfully imported attraction %s",attractionDto.getName())).append(System.lineSeparator());
                Attraction attraction = modelMapper.map(attractionDto, Attraction.class);
                attraction.setCountry(countryRepository.findById(attractionDto.getCountry()).get());
                attractionRepository.save(attraction);

            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String exportAttractions() {
           StringBuilder stringBuilder = new StringBuilder();

//        List<Attraction> attractions = attractionRepository
//                .findAllByTypeInAndElevationGreaterThanEqualOrderByNameAscCountryNameAsc(
//                        List.of("historical", "archaeological"),
//                        300
//                );
//        List<Attraction> attractions = attractionRepository.findAttractionByTypeAndTypeAndElevationGreaterThanEqual
//                ("historical site", "archaeological site",300);

//        List<Attraction> attractions = attractionRepository
//                .findAttractionByTypeAndElevationMoreThanOrEqualTo300(); //С това ми дава 100 ТОЧКИ !!!
        List<Attraction> attractions = attractionRepository.findAttractionByTypeAndTypeAndElevationGreaterThanEqual

                ("historical site","archaeological site", 300);


        attractions.forEach( attraction -> {
            stringBuilder.append(String.format("Attraction with ID%d:\n" +
                    "***%s - %s at an altitude of %dm. somewhere in %s.\n",
                    attraction.getId(),attraction.getName(),attraction.getDescription(),attraction.getElevation(),attraction.getCountry().getName()));
        });

//        Set<Attraction> attractionSet =attractionRepository.
//                findAllByTypeContainingAndElevationGreaterThanOrderByNameAscCountryAsc("historical site",300);
//
//        attractionSet.forEach( attraction -> {
//            stringBuilder.append(String.format("Attraction with ID%d:\n" +
//                    "***%s - %s at an altitude of %dm. somewhere in %s.\n" +
//                    ". . .\"\n",attraction.getId(),attraction.getName(),attraction.getDescription(),
//                    attraction.getElevation(),attraction.getCountry().getName())).append(System.lineSeparator());
//        });

        //        allByTypeAndObserversIsNull
//                .forEach(star -> {
//                    sb.append(String.format("LibraryMember: %s\n" +
//                                    "   *Distance: %.2f light years\n" +
//                                    "   **Description: %s\n" +
//                                    "   ***Book: %s",
//                            star.getName(),
//                            star.getLightYears(),
//                            star.getDescription(),
//                            star.getConstellation().getName()))
//                            .append(System.lineSeparator());
//                });

        return stringBuilder.toString();
    }

    @Override
    public Attraction findAttractionById(long attraction) {
        return attractionRepository.findById(attraction).orElse(null);
    }
}
