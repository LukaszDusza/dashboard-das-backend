package dfsp.controllers;

import dfsp.models.raport.LocalFile;
import dfsp.services.FileService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static dfsp.configs.Naming.FILES;

/*
* Klasa obsługująca żądania na warstwe zarządzania plikami lokalnymi.
*
* */

@CrossOrigin
@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "${files.path.api}",  produces = "application/json", method = RequestMethod.GET)
    public List<LocalFile> getLocalFiles() throws IOException {
        return fileService.getFiles();
    }
}
