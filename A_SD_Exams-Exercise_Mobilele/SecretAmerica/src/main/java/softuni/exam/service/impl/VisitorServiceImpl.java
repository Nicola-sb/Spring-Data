package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataRootDto;
import softuni.exam.models.dto.VisitorDto;
import softuni.exam.models.dto.VisitorsRootDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.service.CountryService;
import softuni.exam.service.PersonalDataService;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidationUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class VisitorServiceImpl implements VisitorService {

    private static final String FILE_PATH = "src/main/resources/files/xml/visitors.xml";

    private final VisitorRepository visitorRepository;
    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;
    private final PersonalDataRepository personalDataRepository;
    private final AttractionService attractionService;
    private final CountryService countryService;
    private final PersonalDataService personalDataService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VisitorServiceImpl(VisitorRepository visitorRepository, AttractionRepository attractionRepository, CountryRepository countryRepository, PersonalDataRepository personalDataRepository, AttractionService attractionService, CountryService countryService, PersonalDataService personalDataService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.visitorRepository = visitorRepository;
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
        this.personalDataRepository = personalDataRepository;
        this.attractionService = attractionService;
        this.countryService = countryService;
        this.personalDataService = personalDataService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVisitors() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(VisitorsRootDto[].class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


        VisitorsRootDto visitorsRootDto = (VisitorsRootDto) unmarshaller.unmarshal(new File(FILE_PATH));

        for (VisitorDto visitorDto : visitorsRootDto.getVisitorDtos()) {
            Optional<Visitor> optionalVisitor = visitorRepository.findByFirstNameAndLastName(visitorDto.getFirstName(),visitorDto.getLastName());
            Optional<Visitor> optionalVisitorId = visitorRepository.findByPersonalDataId(visitorDto.getPersonalData());

            if(!validationUtil.isValid(visitorDto) || optionalVisitor.isPresent() || optionalVisitorId.isPresent()){
                stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            }else{
                stringBuilder.append(String.format("Successfully imported visitor %s %s",visitorDto.getFirstName(),visitorDto.getLastName())).append(System.lineSeparator());
                Visitor visitor = modelMapper.map(visitorDto, Visitor.class);

                Attraction attraction22 = attractionService.findAttractionById(visitorDto.getAttraction());
                Country country = countryService.findCountryById(visitorDto.getCountry());
                PersonalData personalData = personalDataService.findPersonById(visitorDto.getPersonalData());

                visitor.setAttraction(attraction22);
                visitor.setCountry(country);
                visitor.setPersonalData(personalData);

//                visitor.setAttraction(attractionRepository.findById(visitorDto.getAttraction()).get());
//                visitor.setCountry(countryRepository.findById(visitorDto.getCountry()).get());
//                visitor.setPersonalData(personalDataRepository.findById(visitorDto.getPersonalData()).get());

                visitorRepository.save(visitor);
            }

        }


        return stringBuilder.toString();
    }
}
