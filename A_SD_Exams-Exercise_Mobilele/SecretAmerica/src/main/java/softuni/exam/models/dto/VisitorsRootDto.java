package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "visitors")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorsRootDto {


    @XmlElement(name = "visitor")
    private List<VisitorDto> visitorDtos;

    public VisitorsRootDto() {
    }

    public List<VisitorDto> getVisitorDtos() {
        return visitorDtos;
    }

    public void setVisitorDtos(List<VisitorDto> visitorDtos) {
        this.visitorDtos = visitorDtos;
    }
}
