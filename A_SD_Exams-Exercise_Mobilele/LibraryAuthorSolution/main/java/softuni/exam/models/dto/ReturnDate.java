package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReturnDate {

    @XmlElement
    private Long id;

    public Long getId() {
        return id;
    }

    public ReturnDate setId(Long id) {
        this.id = id;
        return this;
    }
}
