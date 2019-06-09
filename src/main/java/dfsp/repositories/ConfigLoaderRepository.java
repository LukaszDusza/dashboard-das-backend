package dfsp.repositories;

import dfsp.configs.ConfigLoader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigLoaderRepository extends JpaRepository<ConfigLoader, Integer> {

}
