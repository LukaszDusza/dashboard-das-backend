package dfsp.models.raport;

import lombok.*;

import java.math.BigDecimal;

/*
*
* Klasa modelowa
* */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Properties {

    String dateFrom;
    String dateTo;
    String agent;
    String dyrektorSegmentu;
    String dyrektorSektora;
    String kanalDystrybucji;
    String kierownikZespolu;
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
