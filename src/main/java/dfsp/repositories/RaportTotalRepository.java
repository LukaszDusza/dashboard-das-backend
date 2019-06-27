package dfsp.repositories;

import dfsp.models.raport.RaportPropertiesToQuery;
import dfsp.models.raport.RaportTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/** Warstwa repozytorium aplikacji, poboeranie danych z bazy za pomocą zapytań JPQL**/

@Repository
public interface RaportTotalRepository extends JpaRepository<RaportTotal, Long> {

    @Query(value = "select r from RaportTotal r " +
            "where r.dataZawarcia between :dateFrom and :dateTo " +
            "and   (r.agent = :#{#p.agent} or :#{#p.agent} is null or :#{#p.agent} = '') " +
            "and   (r.dyrektorSegmentu = :#{# p.dyrektorSegmentu} or :#{# p.dyrektorSegmentu} is null or :#{# p.dyrektorSegmentu} = '') " +
            "and   (r.dyrektorSektora = :#{#p.dyrektorSektora} or :#{#p.dyrektorSektora} is null or :#{#p.dyrektorSektora} = '') " +
            "and   (r.kanalDystrybucji = :#{#p.kanalDystrybucji} or :#{#p.kanalDystrybucji} is null or :#{#p.kanalDystrybucji} = '') " +
            "and   (r.mzaKierownikZespolu = :#{#p.mzaKierownikZespolu} or :#{#p.mzaKierownikZespolu} is null or :#{#p.mzaKierownikZespolu} = '') " +
            "and   (r.miasto = :#{#p.miasto} or :#{#p.miasto} is null or :#{#p.miasto} = '') " +
            "and   (r.nazwaProduktu = :#{#p.nazwaProduktu} or :#{#p.nazwaProduktu} is null or :#{#p.nazwaProduktu} = '') " +
            "and   (r.nazwaSektoraSprzedazy = :#{#p.nazwaSektoraSprzedazy} or :#{#p.nazwaSektoraSprzedazy} is null or :#{#p.nazwaSektoraSprzedazy} = '') " +
            "and   (r.platnosc = :#{#p.platnosc} or :#{#p.platnosc} is null or :#{#p.platnosc} = '') " +
            "and   (r.segmentSprzedazy = :#{#p.segmentSprzedazy} or :#{#p.segmentSprzedazy} is null or :#{#p.segmentSprzedazy} = '') " +
            "and   (r.skladka >= :#{#p.fromAmount} or :#{#p.fromAmount} is null) " +
            "and   (r.skladka <= :#{#p.toAmount} or :#{#p.toAmount} is null) " +
            "and   (r.statusUmowy = :#{#p.statusUmowy} or :#{#p.statusUmowy} is null or :#{#p.statusUmowy} = '') " +
            "and   (r.uzytkownik = :#{#p.uzytkownik} or :#{#p.uzytkownik} is null or :#{#p.uzytkownik} = '') ")
    List<RaportTotal> findRaportTotalByProperties(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo, @Param("p") RaportPropertiesToQuery p);


    @Query(value = "select r from RaportTotal r where r.dataZawarcia between ?1 and ?2")
    List<RaportTotal> findRaportTotalBetweenDates(Date dateFrom, Date dateTo);

}


