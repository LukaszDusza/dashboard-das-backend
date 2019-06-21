package dfsp.models.raport;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

/** Klasa, na którą jest mapowana encja RaportTotal.*/

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RaportTotalDto {

    private String numerKalkulacji;
    private Date dataKalkulacji;
    private String numerUmowy;
    private Date dataZawarcia;
    private Date wazneOd;
    private Date wazneDo;
    private String nazwaProduktu;
    private String statusUmowy;
    private BigDecimal skladka;
    private String platnosc;
    private String agent;
    private String nrWewnAgenta;
    private String nrKnfAgenta;
    private String uzytkownik;
    private String nrKnfUzytkownika;
    private String emailUzytkownika;
    private String aktywny;
    private String zablokowany;
    private String poziom1;
    private String kanalDystrybucji;
    private String poziom1KNF;
    private String poziom2;
    private String nazwaSektoraSprzedazy;
    private String poziom2KNF;
    private String poziom3;
    private String dyrektorSektora;
    private String poziom3KNF;
    private String poziom4;
    private String segmentSprzedazy;
    private String poziom4knf;
    private String poziom5;
    private String dyrektorSegmentu;
    private String poziom5knf;
    private String poziom6;
    private String miasto;
    private String poziom6knf;
    private String poziom7;
    private String mzaKierownikZespolu;
    private String poziom7knf;
    private String telefonUzytkownika;
}
