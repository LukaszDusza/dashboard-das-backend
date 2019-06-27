package dfsp.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {


    @Query(value = "select u from UserApp u left join fetch u.roles r where u.username=?1")
    Optional<UserApp> findUserAppByName(String username);
}
