package dfsp.controllers;


import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportTotalDto;
import dfsp.services.RaportTotalService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/** Warstwa webowa aplikacji odpowiedzialna za REST API */

@CrossOrigin
@RestController
public class RaportController {

    private RaportTotalService raportTotalService;

    public RaportController(RaportTotalService raportTotalService) {
        this.raportTotalService = raportTotalService;
    }

    @GetMapping("/api/v1/raport/{dateFrom}/{dateTo}")
    public List<RaportTotalDto> getRaportTotalByParams(@PathVariable String dateFrom, @PathVariable String dateTo, RaportPropertiesIncome incomeProps) throws ParseException {
        return raportTotalService.getRaportTotalByProperties(dateFrom, dateTo, incomeProps);
    }

    /** endpoint wyłaczony **/
  //  @GetMapping("/api/v1/raport/{dateFrom}/{dateTo}")
    public List<RaportTotalDto> getRaportTotalBetweenDates(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        return raportTotalService.getRaportTotalBetweenDates(dateFrom, dateTo);
    }
}
