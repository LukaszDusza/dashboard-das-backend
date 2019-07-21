package dfsp.controllers;


import dfsp.models.raport.RaportAgregowany;
import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportTotalDto;
import dfsp.services.RaportTotalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Warstwa webowa aplikacji odpowiedzialna za REST API
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RaportTotalController {

    private RaportTotalService raportTotalService;

    public RaportTotalController(RaportTotalService raportTotalService) {
        this.raportTotalService = raportTotalService;
    }

    /**
     * pobranie raportu według dat i parametrów.
     */

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping("/api/v1/raport")
    public List<RaportTotalDto> getRaportTotalByParams(@RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                       @RequestParam(value = "dateTo", required = false) String dateTo,
                                                       @RequestBody RaportPropertiesIncome incomeProps) throws ParseException {
        return raportTotalService.getRaportTotalByProperties(dateFrom, dateTo, incomeProps);
    }

    /**
     * pobranie raportu według parametrów.
     */

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping("/api/v2/raport")
    public List<RaportTotalDto> getRaportTotalByIncomeProps(@RequestBody RaportPropertiesIncome incomeProps) throws ParseException {
        return raportTotalService.getRaportTotalByProperties(incomeProps.getDateFrom(), incomeProps.getDateTo(), incomeProps);
    }

    /**
     * aktualizacja raportu w bazie danych
     */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/raport/update")
    public void updateBaseRaport(@RequestParam("filename") String filename) {
        raportTotalService.updateBaseRaport(filename);
    }

    /**
     * zapis kopii bazy danych do pliku XLS bez określenia parametrów
     */

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @GetMapping("/api/v1/raport/backup")
    public void backupBaseRaport(@RequestParam("filename") String filename) {
        raportTotalService.saveCurrentRaportToXLSFileIntoDirectory(filename);
    }

    /**
     * zapis kopii bazy danych do pliku XLS z określeniem parametrów i zakresu dat
     */

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping("/api/v1/raport-filtered/backup")
    public void backupFilteredBaseRaport(@RequestParam("filename") String filename, @RequestBody RaportPropertiesIncome incomeProps) {
        System.out.println(filename);
        raportTotalService.saveFilteredRaportToXLSFileIntoDirectory(filename, incomeProps);
    }

    /**
     * zapis filtrowanego raportu zagregowanego do  bazy danych do pliku XLS na podstawie danych przekazanych przez front aplikacji
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' )")
    @PostMapping(value = "/api/v1/raport-filtered-agreg/backup")
    public void backupFilteredAgregRaport(@RequestParam("filename") String filename, @RequestBody List<RaportAgregowany> raport) {
        raportTotalService.saveFilteredAgregRaportToXLSFileIntoDirectory(filename, raport);

    }
}
