package softuni.exam.instagraphlite.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> T fromFile(String filePath,Class<T>tClass) throws JAXBException, FileNotFoundException;
}
