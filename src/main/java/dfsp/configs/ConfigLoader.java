package dfsp.configs;

import dfsp.repositories.ConfigLoaderRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static dfsp.configs.Naming.CONFIG_APP_DB;

/*
 * Klasa służy do ładowania konfiguracji z bazy danych na zmienne w klasie dfsp.configs.Naming
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


    public void loadCurrentConfiguration() {
        Field[] fields = Naming.class.getDeclaredFields();
        configLoaderRepository.findAll().forEach(
                c -> {
                    for (Field f : fields) {
                        if (f.getName().equals(c.key)) {
                            Reflect.on(Naming.class).set(f.getName(), c.getValue());
                            try {
                                LOGGER.log(Level.INFO, "load config variable: " + f.getName() + " : " + f.get("java.util.String"));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
        );
    }

    @Bean
    public void loadPropertyConfiguration() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(properties.getProperty("config.file.path")));
        configLoaderRepository.findAll().forEach(
                c -> {
                    if(properties.containsKey(c.getKey())) {
                        properties.setProperty(c.getKey(), c.getValue());
                        LOGGER.log(Level.INFO, "Set property: " + c.getKey() + " : " + c.getValue());
                    }
                }
        );
    }
}
