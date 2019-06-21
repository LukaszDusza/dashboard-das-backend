package dfsp.services;

import dfsp.models.raport.LocalFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

/** klasa odpowiedzialna za operacje na plikach. */

@Service
public class FileService {

    private static final String LOCAL_PATH = "c:\\files\\";
    private static final String FILES = "/api/v1/files";
    private static final String DOWNLOAD_PATH = "/download";
    private static final String DELETE_PATH = "/delete";

    public List<LocalFile> getFiles() throws IOException {

        return Files.walk(Paths.get(LOCAL_PATH))
                .filter(Files::isRegularFile)
                .map(f -> {

                    try {
                        BasicFileAttributes bs = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);

                        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(FILES + DOWNLOAD_PATH)
                                .path(f.getFileName().toString())
                                .toUriString();

                        String fileDeleteUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(FILES + DELETE_PATH)
                                .path(f.getFileName().toString())
                                .toUriString();

                        return LocalFile.builder()
                                .name(f.getFileName().toString())
                                .creationTime(bs.creationTime().toString())
                                .size(bs.size())
                                .lastModified(bs.lastModifiedTime().toString())
                                .fileDeleteUri(fileDeleteUri)
                                .downloadUri(fileDownloadUri)
                                .fileType(Files.probeContentType(f.toAbsolutePath()))
                                .owner("anonymous")
                                .build();

                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    public Resource downloadFile(String filename) throws IOException {
        return new UrlResource(Paths.get(LOCAL_PATH + filename).toUri());
    }

    public boolean deleteFile(String file) {
        return new File(LOCAL_PATH + file).delete();
    }

}
