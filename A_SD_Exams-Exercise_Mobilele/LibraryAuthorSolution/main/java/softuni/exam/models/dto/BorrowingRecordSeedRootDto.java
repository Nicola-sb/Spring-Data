package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordSeedRootDto {

    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordSeedDto> borrowingRecordSeedDtos;

    public List<BorrowingRecordSeedDto> getBorrowingRecordSeedDtos() {
        return borrowingRecordSeedDtos;
    }

    public BorrowingRecordSeedRootDto setBorrowingRecordSeedDtos(List<BorrowingRecordSeedDto> borrowingRecordSeedDtos) {
        this.borrowingRecordSeedDtos = borrowingRecordSeedDtos;
        return this;
    }
}
