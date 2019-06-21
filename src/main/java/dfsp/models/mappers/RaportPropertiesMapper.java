package dfsp.models.mappers;

import dfsp.commons.Mapper;
import dfsp.models.raport.RaportPropertiesIncome;
import dfsp.models.raport.RaportPropertiesToQuery;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/** Klasa mapująca parametry zapytania - obecnie nie ma różnicy między mapowaniem. Pomocna, gdy wystąpią różnice w typach zmiennych*/

@Component
public class RaportPropertiesMapper implements Mapper<RaportPropertiesIncome, RaportPropertiesToQuery> {

    @Override
    public RaportPropertiesToQuery map(RaportPropertiesIncome p) throws ParseException {
        return RaportPropertiesToQuery
                .builder()
                .agent(p.getAgent())
                .dyrektorSegmentu(p.getDyrektorSegmentu())
                .dyrektorSektora(p.getDyrektorSektora())
                .kanalDystrybucji(p.getKanalDystrybucji())
                .mzaKierownikZespolu(p.getMzaKierownikZespolu())
                .miasto(p.getMiasto())
                .nazwaProduktu(p.getNazwaProduktu())
                .nazwaSektoraSprzedazy(p.getNazwaSektoraSprzedazy())
                .platnosc(p.getPlatnosc())
                .segmentSprzedazy(p.getSegmentSprzedazy())
                .fromAmount(p.getFromAmount())
                .toAmount(p.getToAmount())
                .statusUmowy(p.getStatusUmowy())
                .uzytkownik(p.getUzytkownik())
                .build();
    }
}
