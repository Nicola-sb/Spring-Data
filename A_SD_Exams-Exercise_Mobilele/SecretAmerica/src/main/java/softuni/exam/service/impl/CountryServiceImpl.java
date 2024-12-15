package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all the methods
@Service
public class CountryServiceImpl implements CountryService {

    private static final String FILE_PATH = "src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountryFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        CountryDto[] countryDtos = gson.fromJson(readCountryFileContent(), CountryDto[].class);

        StringBuilder stringBuilder = new StringBuilder();
        for (CountryDto countryDto : countryDtos) {
            Optional<Country> optionalCountry = countryRepository.findByName(countryDto.getName());

            if(!validationUtil.isValid(countryDto) || optionalCountry.isPresent()){
                stringBuilder.append("Invalid country").append(System.lineSeparator());
            }else{

                stringBuilder.append(String.format("Successfully imported country %s",countryDto.getName())).append(System.lineSeparator());
                Country country = modelMapper.map(countryDto, Country.class);
                countryRepository.save(country);
            }


        }


        return stringBuilder.toString();
    }

    @Override
    public Country findCountryById(long country) {
        return countryRepository.findById(country).orElse(null);
    }
}
