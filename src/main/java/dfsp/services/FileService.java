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

import static dfsp.configs.Naming.*;

@Service
public class FileService {
    
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

    public boolean deleteFile(@PathVariable("file") String file) {
        return new File(LOCAL_PATH + file).delete();
    }

}
