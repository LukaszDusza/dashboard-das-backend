package dfsp.controllers;


import dfsp.commons.ManagerXLS;
import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportTotal;
import dfsp.models.raport.RaportTotalDto;
import dfsp.services.RaportTotalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

/** Warstwa webowa aplikacji odpowiedzialna za REST API */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RaportTotalController {

    private RaportTotalService raportTotalService;

    public RaportTotalController(RaportTotalService raportTotalService) {
        this.raportTotalService = raportTotalService;
    }

    /** pobranie raportu według dat i parametrów.*/

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping("/api/v1/raport")
    public List<RaportTotalDto> getRaportTotalByParams(@RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                       @RequestParam(value = "dateTo", required = false) String dateTo,
                                                       @RequestBody RaportPropertiesIncome incomeProps) throws ParseException {
        return raportTotalService.getRaportTotalByProperties(dateFrom, dateTo, incomeProps);
    }

    /** endpoint wyłaczony **/
  //  @PreAuthorize("hasRole('ROLE_USER')")
  //  @GetMapping("/api/v1/raport/{dateFrom}/{dateTo}")
    public List<RaportTotalDto> getRaportTotalBetweenDates(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        return raportTotalService.getRaportTotalBetweenDates(dateFrom, dateTo);
    }

    /** aktualizacja raportu w bazie danych*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/raport/update")
    public void updateBaseRaport(@RequestParam("filename") String filename) {
        raportTotalService.updateBaseRaport(filename);
    }

    /** zapis kopii bazy danych do pliku XLS bez określenia parametrów*/

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/v1/raport/backup")
    public void backupBaseRaport(@RequestParam("filename") String filename) {
        raportTotalService.saveCurrentRaportToXLSFileIntoDirectory(filename);
    }

    /** zapis kopii bazy danych do pliku XLS z określeniem parametrów i zakresu dat*/

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/v1/raport/backup/{dateFrom}/{dateTo}")
    public void backupFilteredBaseRaport(@RequestParam("filename") String filename, @PathVariable String dateFrom, @PathVariable String dateTo, RaportPropertiesIncome incomeProps) {
        raportTotalService.saveFilteredRaportToXLSFileIntoDirectory(filename, dateFrom, dateTo, incomeProps);
    }

}
