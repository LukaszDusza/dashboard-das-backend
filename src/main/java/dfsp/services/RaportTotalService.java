package dfsp.services;

import dfsp.commons.DateParser;
import dfsp.commons.ManagerXLS;
import dfsp.models.mappers.RaportPropertiesMapper;
import dfsp.models.mappers.RaportTotalMapper;
import dfsp.models.raport.RaportAgregowany;
import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportTotal;
import dfsp.models.raport.RaportTotalDto;
import dfsp.repositories.RaportTotalRepository;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static dfsp.commons.Naming.LOCAL_PATH;

/**
 * Klasa odpowiedzialna za warstę serwisową aplikacji. Działa pomiędzy kontrolerem a repozytorium
 */

@Service
public class RaportTotalService {

    private RaportTotalRepository raportTotalRepository;
    private RaportTotalMapper raportTotalMapper;
    private RaportPropertiesMapper raportPropertiesMapper;
    private ManagerXLS<RaportTotalDto> managerXLS = new ManagerXLS<>(RaportTotalDto.class);


    public RaportTotalService(RaportTotalRepository raportTotalRepository, RaportTotalMapper raportTotalMapper, RaportPropertiesMapper raportPropertiesMapper) {
        this.raportTotalRepository = raportTotalRepository;
        this.raportTotalMapper = raportTotalMapper;
        this.raportPropertiesMapper = raportPropertiesMapper;
    }

    /**
     * Pobranie raportu z bazy danych na podstawie parametrów i zakresu dat
     */

    public List<RaportTotalDto> getRaportTotalByProperties(String dateFrom, String dateTo, RaportPropertiesIncome incomeProps) throws ParseException {

        if (StringUtils.isEmpty(dateFrom)) {
            dateFrom = "1999-01-01";
        }
        if (StringUtils.isEmpty(dateTo)) {
            dateTo = "2100-12-31";
        }

        return raportTotalRepository
                .findRaportTotalByProperties(DateParser.fromSqlDate(dateFrom), DateParser.fromSqlDate(dateTo), raportPropertiesMapper.map(incomeProps))
                .stream()
                .map(raportTotalMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Pobranie raportu z bazy danych na podstawie tylko zakresu dat
     */

    public List<RaportTotalDto> getRaportTotalBetweenDates(String dateFrom, String dateTo) throws ParseException {
        if (StringUtils.isEmpty(dateFrom)) {
            dateFrom = "1999-01-01";
        }
        if (StringUtils.isEmpty(dateTo)) {
            dateTo = "2100-12-31";
        }
        return raportTotalRepository
                .findRaportTotalBetweenDates(DateParser.toSqlDate(dateFrom), DateParser.toSqlDate(dateTo))
                .stream()
                .map(raportTotalMapper::map)
                .collect(Collectors.toList());
    }


    /**
     * Aktualizacja raportu w  bazie danych na podstawie pliku znajdującego się w katalogu z plikami xls.
     */

    @Async
    public void updateBaseRaport(String filename) {

        try {
            File file = new File(Paths.get(LOCAL_PATH + filename).toString());
            MultipartFile result = new MockMultipartFile(filename, file.getName(), "application/excel", Files.readAllBytes(file.toPath()));
            List<RaportTotal> series = new LinkedList<>();

            managerXLS.loadXLSFileToList(result).forEach( r -> series.add(raportTotalMapper.reverse(r)));

        //    series.forEach(System.out::println);

            raportTotalRepository.deleteAll();
            raportTotalRepository.flush();
            raportTotalRepository.saveAll(series);

        } catch (IOException | NoSuchMethodException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * zrzut aktualnych danuch znajdujących się w bazie do pliku XLS i umieszcze tego pliku w katalogu z plikami. Zmienna Naming.LOCAL_PATH
     */

    public void saveCurrentRaportToXLSFileIntoDirectory(String filename) {
        List<RaportTotalDto> series = raportTotalRepository.findAll().stream().map(raportTotalMapper::map).collect(Collectors.toList());

        try {
            managerXLS.saveListToXLSFileInToDirectory(filename, series);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }

    /** zapisanie przefiltrowanego raportu do pliku XLS i umieszcze tego pliku w katalogu z plikami. Zmienna Naming.LOCAL_PATH*/

    public void saveFilteredRaportToXLSFileIntoDirectory(String filename, RaportPropertiesIncome incomeProps) {
        try {
            managerXLS.saveListToXLSFileInToDirectory(filename, getRaportTotalByProperties(incomeProps.getDateFrom(), incomeProps.getDateTo(), incomeProps));
        } catch (ParseException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    /** zapisanie przefiltrowanego agregowanego raportu do pliku XLS i umieszcze tego pliku w katalogu z plikami. Zmienna Naming.LOCAL_PATH*/

    public void saveFilteredAgregRaportToXLSFileIntoDirectory(String filename, List<RaportAgregowany> series) {
         ManagerXLS<RaportAgregowany> managerXLS = new ManagerXLS<>(RaportAgregowany.class);
        try {
            managerXLS.saveListToXLSFileInToDirectory(filename, series);
        } catch ( NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
