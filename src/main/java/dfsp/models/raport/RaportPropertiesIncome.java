package dfsp.models.raport;

import lombok.*;

import java.math.BigDecimal;

/** Klasa służy do filtrowania wyniku wyszukiwania danych w bazie. Używana od strony Klienta, czyli zapytania przychodzące do serwera */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RaportPropertiesIncome {

    String dateFrom;
    String dateTo;
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
