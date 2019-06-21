package dfsp.controllers;

import dfsp.models.raport.LocalFile;
import dfsp.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;



/** Klasa obsługująca żądania na warstwe zarządzania plikami lokalnymi. */

@CrossOrigin
@RestController
public class FileController {

    private static final String  FILES = "/api/v1/files";
    private static final String DOWNLOAD_PATH = "/download";
    private static final String DELETE_PATH = "/delete";

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(FILES)
    public List<LocalFile> getLocalFiles() throws IOException {
        return fileService.getFiles();
    }

    @GetMapping(FILES + DOWNLOAD_PATH + "/{filename}")
    public Resource downloadFile(@PathVariable String filename) throws IOException {
        return fileService.downloadFile(filename);
    }

    @DeleteMapping(FILES + DELETE_PATH + "/{filename}")
    public boolean deleteFile(@PathVariable String filename) {
        return fileService.deleteFile(filename);
    }

    // todo upload file
}
