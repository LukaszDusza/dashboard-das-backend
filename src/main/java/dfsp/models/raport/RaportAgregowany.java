package dfsp.models.raport;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaportAgregowany {

    private String agent;
    private String kanalDystrybucji;
    private String nazwaSektoraSprzedazy;
    private String dyrektorSektora;
    private String segmentSprzedazy;
    private String dyrektorSegmentu;
    private String mzaKierownikZespolu;
    private String miasto;
    private String nrWewAgenta;
    private int quantity;
    private int amount;

}
