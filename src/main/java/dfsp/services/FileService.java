package dfsp.services;

import dfsp.commons.DirectoryCreator;
import dfsp.models.raport.LocalFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

import static dfsp.commons.Naming.*;

/**
 * klasa odpowiedzialna za operacje na plikach.
 */

@Service
public class FileService {

    public FileService() {
        DirectoryCreator.createDirecotry();
    }

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

    public void storeFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths.get(LOCAL_PATH + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
