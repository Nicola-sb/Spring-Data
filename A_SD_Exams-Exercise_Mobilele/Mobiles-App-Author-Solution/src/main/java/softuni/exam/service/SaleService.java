package softuni.exam.service;

import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;

import java.io.IOException;

// TODO: Implement all methods
public interface SaleService {

    boolean areImported();

    String readSalesFileContent() throws IOException;

    String importSales() throws IOException;

    Sale findSaleById(Long saleId);
    void addAndSaveAddedDevice(Sale sale, Device device);

    String exportSales();
}
