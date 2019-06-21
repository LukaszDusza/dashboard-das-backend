package dfsp.models.raport;

import lombok.*;

import java.math.BigDecimal;

/** WAŻNE-  Polskie zmienne, ponieważ baza danych zawiera polskie nazwy kolumn.
 Klasa służy do filtrowania wyniku wyszukiwania danych w bazie. Jest mapowana za pomocą klasy RaportPropertiesIncome */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter


public class RaportPropertiesToQuery {

    String agent;
    String dyrektorSegmentu;
    String dyrektorSektora;
    String kanalDystrybucji;
    String mzaKierownikZespolu;
    String miasto;
    String nazwaProduktu;
    String nazwaSektoraSprzedazy;
    String platnosc;
    String segmentSprzedazy;
    BigDecimal fromAmount;
    BigDecimal toAmount;
    String statusUmowy;
    String uzytkownik;
}
