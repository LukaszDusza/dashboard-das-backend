package dfsp.models.raport;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RaportAgreg {

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
