package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceSeedRootDto {

    @XmlElement(name = "device")
    private List<DeviceSeedDto> deviceSeedDtos;

    public List<DeviceSeedDto> getDeviceSeedDtos() {
        return deviceSeedDtos;
    }

    public DeviceSeedRootDto setDeviceSeedDtos(List<DeviceSeedDto> deviceSeedDtos) {
        this.deviceSeedDtos = deviceSeedDtos;
        return this;
    }
}
