package dfsp.controllers;

import dfsp.models.raport.LocalFile;
import dfsp.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static dfsp.commons.Naming.*;


/**
 * Klasa obsługująca żądania na warstwe zarządzania plikami lokalnymi.
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @GetMapping(FILES)
    public List<LocalFile> getLocalFiles() throws IOException {
        return fileService.getFiles();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @GetMapping(FILES + DOWNLOAD_PATH + "/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Resource resource = fileService.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFile().getName() + "\"")
                .contentLength(resource.getFile().length())
                .body(resource);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(FILES + DELETE_PATH + "/{filename}")
    public boolean deleteFile(@PathVariable String filename) {
        return fileService.deleteFile(filename);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping(FILES + UPLOAD_PATH)
    public void uploadFiles(@RequestParam("file") MultipartFile upload) {
        fileService.storeFile(upload);
    }


    /** permitAll()*/
    @GetMapping(FILES + DOWNLOAD_PATH + "logo/{filename}")
    public ResponseEntity<Resource> getLogoImg(@PathVariable String filename) throws IOException {
        Resource resource = fileService.getImg(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + resource.getFile().getName() + "\"")
                .contentLength(resource.getFile().length())
                .body(resource);
    }
}
