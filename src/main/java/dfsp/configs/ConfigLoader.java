package dfsp.configs;

import dfsp.repositories.ConfigLoaderRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import static dfsp.configs.Naming.CONFIG_APP_DB;

/*
 * Klasa służy do mapowania wartości z bazy danych na zmienne w klasie dfsp.configs.Naming
 *
 */

@NoArgsConstructor
@ToString(exclude = {"configLoaderRepository"})
@Getter
@Setter
@Configuration
@Entity
@Table(name = CONFIG_APP_DB)
public class ConfigLoader {

    @Transient
    private static final Logger LOGGER = Logger.getLogger(ConfigLoader.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String key;
    private String value;
    private String comment;


    @Transient
    private ConfigLoaderRepository configLoaderRepository;

    @Autowired
    public ConfigLoader(ConfigLoaderRepository configLoaderRepository) {
        this.configLoaderRepository = configLoaderRepository;
    }

    @Bean
    public void loadCurrentConfiguration() {
        Field[] fields = Naming.class.getDeclaredFields();
        configLoaderRepository.findAll().forEach(
                c -> {
                    for (Field f : fields) {
                        if (f.getName().equals(c.key)) {
                            try {
                                f.set(c, c.value);
                                LOGGER.log(Level.INFO, "load config variable: " + f.getName());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
}
