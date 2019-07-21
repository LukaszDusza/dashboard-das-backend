package dfsp.models.mappers;

import dfsp.commons.Mapper;
import dfsp.models.raport.RaportTotal;
import dfsp.models.raport.RaportTotalDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/** Klasa mapująca RaportTotal na RaportTotalDto */


@Component
public class RaportTotalMapper implements Mapper<RaportTotal, RaportTotalDto> {

    @Override
    public RaportTotalDto map(RaportTotal f) {
        return RaportTotalDto
                .builder()
                .numerKalkulacji(f.getNumerKalkulacji() )
                .dataKalkulacji(f.getDataKalkulacji() )
                .numerUmowy(f.getNumerUmowy() )
                .dataZawarcia(f.getDataZawarcia() )
                .wazneOd(f.getWazneOd() )
                .wazneDo(f.getWazneDo() )
                .nazwaProduktu(f.getNazwaProduktu() )
                .statusUmowy(f.getStatusUmowy() )
                .skladka(f.getSkladka() )
                .platnosc(f.getPlatnosc() )
                .agent(f.getAgent() )
                .nrWewnAgenta(f.getNrWewnAgenta() )
                .nrKnfAgenta(f.getNrKnfAgenta() )
                .uzytkownik(f.getUzytkownik() )
                .nrKnfUzytkownika(f.getNrKnfUzytkownika() )
                .emailUzytkownika(f.getEmailUzytkownika() )
                .aktywny(f.getAktywny() )
                .zablokowany(f.getZablokowany() )
                .poziom1(f.getPoziom1() )
                .kanalDystrybucji(f.getKanalDystrybucji() )
             //   .poziom1KNF(f.getPoziom1KNF() )
                .poziom2(f.getPoziom2() )
                .nazwaSektoraSprzedazy(f.getNazwaSektoraSprzedazy() )
             //   .poziom2KNF(f.getPoziom2KNF() )
                .poziom3(f.getPoziom3() )
                .dyrektorSektora(f.getDyrektorSektora() )
             //   .poziom3KNF(f.getPoziom3KNF() )
                .poziom4(f.getPoziom4() )
                .segmentSprzedazy(f.getSegmentSprzedazy() )
             //   .poziom4knf(f.getPoziom4knf() )
                .poziom5(f.getPoziom5() )
                .dyrektorSegmentu(f.getDyrektorSegmentu() )
             //   .poziom5knf(f.getPoziom5knf() )
                .poziom6(f.getPoziom6() )
                .miasto(f.getMiasto() )
             //   .poziom6knf(f.getPoziom6knf() )
                .poziom7(f.getPoziom7() )
                .mzaKierownikZespolu(f.getMzaKierownikZespolu() )
             //   .poziom7knf(f.getPoziom7knf() )
                .telefonUzytkownika(f.getTelefonUzytkownika() )
                .build();
    }

    @Override
    public RaportTotal reverse(RaportTotalDto f) {
        return RaportTotal
                .builder()
                .numerKalkulacji(f.getNumerKalkulacji() )
                .dataKalkulacji(f.getDataKalkulacji() )
                .numerUmowy(f.getNumerUmowy() )
                .dataZawarcia(f.getDataZawarcia() )
                .wazneOd(f.getWazneOd() )
                .wazneDo(f.getWazneDo() )
                .nazwaProduktu(f.getNazwaProduktu() )
                .statusUmowy(f.getStatusUmowy() )
                .skladka(f.getSkladka() )
                .platnosc(f.getPlatnosc() )
                .agent(f.getAgent() )
                .nrWewnAgenta(f.getNrWewnAgenta() )
                .nrKnfAgenta(f.getNrKnfAgenta() )
                .uzytkownik(f.getUzytkownik() )
                .nrKnfUzytkownika(f.getNrKnfUzytkownika() )
                .emailUzytkownika(f.getEmailUzytkownika() )
                .aktywny(f.getAktywny() )
                .zablokowany(f.getZablokowany() )
                .poziom1(f.getPoziom1() )
                .kanalDystrybucji(f.getKanalDystrybucji() )
             //   .poziom1KNF(f.getPoziom1KNF() )
                .poziom2(f.getPoziom2() )
            //    .nazwaSektoraSprzedazy(f.getNazwaSektoraSprzedazy() )
             //   .poziom2KNF(f.getPoziom2KNF() )
                .poziom3(f.getPoziom3() )
                .dyrektorSektora(f.getDyrektorSektora() )
             //   .poziom3KNF(f.getPoziom3KNF() )
                .poziom4(f.getPoziom4() )
                .segmentSprzedazy(f.getSegmentSprzedazy() )
             //   .poziom4knf(f.getPoziom4knf() )
                .poziom5(f.getPoziom5() )
                .dyrektorSegmentu(f.getDyrektorSegmentu() )
            //    .poziom5knf(f.getPoziom5knf() )
                .poziom6(f.getPoziom6() )
                .miasto(f.getMiasto() )
             //   .poziom6knf(f.getPoziom6knf() )
                .poziom7(f.getPoziom7() )
                .mzaKierownikZespolu(f.getMzaKierownikZespolu() )
             //   .poziom7knf(f.getPoziom7knf() )
                .telefonUzytkownika(f.getTelefonUzytkownika() )
                .build();
    }
}
