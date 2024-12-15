package sofuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "moons")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoonRootDto {

    @XmlElement(name = "moon")
    private List<MoonDto> moonDtos;

    public MoonRootDto() {
    }

    public List<MoonDto> getMoonDtos() {
        return moonDtos;
    }

    public void setMoonDtos(List<MoonDto> moonDtos) {
        this.moonDtos = moonDtos;
    }
}
