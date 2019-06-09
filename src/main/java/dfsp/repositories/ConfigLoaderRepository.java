package dfsp.repositories;

import dfsp.configs.ConfigLoader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Klasa odpowiedzialna za warstwę DAO aplikacji
* */

@Repository
public interface ConfigLoaderRepository extends JpaRepository<ConfigLoader, Integer> {

}
