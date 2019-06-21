package dfsp.services;

import dfsp.commons.DateParser;
import dfsp.models.mappers.RaportPropertiesMapper;
import dfsp.models.mappers.RaportTotalMapper;
import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportTotalDto;
import dfsp.repositories.RaportTotalRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/** Klasa odpowiedzialna za warstę serwisową aplikacji. Działa pomiędzy kontrolerem a repozytorium */

@Service
public class RaportTotalService {

    private RaportTotalRepository raportTotalRepository;
    private RaportTotalMapper raportTotalMapper;
    private RaportPropertiesMapper raportPropertiesMapper;


    public RaportTotalService(RaportTotalRepository raportTotalRepository, RaportTotalMapper raportTotalMapper, RaportPropertiesMapper raportPropertiesMapper) {
        this.raportTotalRepository = raportTotalRepository;
        this.raportTotalMapper = raportTotalMapper;
        this.raportPropertiesMapper = raportPropertiesMapper;
    }

    public List<RaportTotalDto> getRaportTotalByProperties(@PathVariable String dateFrom, @PathVariable String dateTo, RaportPropertiesIncome incomeProps) throws ParseException {

        if(StringUtils.isEmpty(dateFrom)) { dateFrom = "1999-01-01";}
        if(StringUtils.isEmpty(dateTo)) { dateTo = "2100-12-31";}

        return raportTotalRepository
                .findRaportTotalByProperties(DateParser.toSqlDate(dateFrom), DateParser.toSqlDate(dateTo), raportPropertiesMapper.map(incomeProps))
                .stream()
                .map(raportTotalMapper::map)
                .collect(Collectors.toList());
    }

    public List<RaportTotalDto> getRaportTotalBetweenDates(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        if(StringUtils.isEmpty(dateFrom)) { dateFrom = "1999-01-01";}
        if(StringUtils.isEmpty(dateTo)) { dateTo = "2100-12-31";}
        return raportTotalRepository
                .findRaportTotalBetweenDates(DateParser.toSqlDate(dateFrom), DateParser.toSqlDate(dateTo))
                .stream()
                .map(raportTotalMapper::map)
                .collect(Collectors.toList());
    }

}
