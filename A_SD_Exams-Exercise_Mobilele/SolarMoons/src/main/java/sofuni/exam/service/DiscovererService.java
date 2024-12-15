package sofuni.exam.service;

import org.springframework.stereotype.Service;
import sofuni.exam.models.entity.Discoverer;

import java.io.IOException;

public interface DiscovererService {

    boolean areImported();

    String readDiscovererFileContent() throws IOException;

    String importDiscoverers() throws IOException;

    Discoverer findById(Long discoverer);
}
