package pl.nullpointerexeption.libraryspring.logger;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class PublisherLogger {

    public Stream<String> readLogFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }
}
