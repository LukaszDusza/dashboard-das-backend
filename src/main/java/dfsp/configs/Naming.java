package dfsp.configs;

public class Naming {

    /*
     * Klasa przechowująca zmienne konfiguracyjne.
     *
     * */

    /*
     * Wartosci pol zaczytywane są z bazy danych w zmiennej CONFIG_APP_DB
     * pod warunkiem identycznej nazwy zmiennej i wartości kolumny 'key' w tabeli.
     * Zmienne finalne są modyfikowe przez biblioteke jOOR dla wygodnej implementacji refleksji
     */

    /*databases*/
    public static final String CONFIG_APP_DB = "config";
    public static final String TEST_TABLE_NAME = "";

    /*files*/
    public static final String LOCAL_PATH = "";
    public static final String FILES = "";
    public static final String DOWNLOAD_PATH = "";
    public static final String DELETE_PATH = "";
    public static final String UPLOAD_FILE = "";

    /*swagger*/
    public static String BASE_PACKAGE_CONTROLLERS;

}
