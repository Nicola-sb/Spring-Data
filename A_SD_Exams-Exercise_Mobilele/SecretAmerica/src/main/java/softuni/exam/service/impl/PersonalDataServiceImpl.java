package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataDto;
import softuni.exam.models.dto.PersonalDataRootDto;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.ValidationUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class PersonalDataServiceImpl implements PersonalDataService {



    private static final String FILE_PATH = "src/main/resources/files/xml/personal_data.xml";
    private final PersonalDataRepository personalDataRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.personalDataRepository = personalDataRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPersonalData() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(PersonalDataRootDto[].class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


         PersonalDataRootDto personalDataRootDto = (PersonalDataRootDto) unmarshaller.unmarshal(new File(FILE_PATH));

        for (PersonalDataDto personalDatum : personalDataRootDto.getPersonalData()) {
            Optional<PersonalData> personalDataOptional = personalDataRepository.findByCardNumber(personalDatum.getCardNumber());

            if(!validationUtil.isValid(personalDatum) || personalDataOptional.isPresent()){
                stringBuilder.append("Invalid personal data").append(System.lineSeparator());
            }else{
                stringBuilder.append(String.format("Successfully imported personal data for visitor with card number %s",personalDatum.getCardNumber()))
                        .append(System.lineSeparator());

                PersonalData personalData = modelMapper.map(personalDatum, PersonalData.class);

                personalDataRepository.save(personalData);
            }

        }


        return stringBuilder.toString();
    }

    @Override
    public PersonalData findPersonById(long personalData) {
        return personalDataRepository.findById(personalData).orElse(null);
    }
}
