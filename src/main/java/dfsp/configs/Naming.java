package dfsp.configs;

public class Naming {

    /*
    * Wartosci pol zaczytywane są z bazy danych w zmiennej CONFIG_APP_DB
    * pod warunkiem identycznej nazwy zmiennej i wartości kolumny 'key' w tabeli.
    */

    /*databases*/
    public static final String TEST_TABLE_NAME = "raport_test_2";
    public static final String CONFIG_APP_DB = "config";

    /*files*/
    public static String LOCAL_PATH;
    public static String FILES;
    public static String DOWNLOAD_PATH;
    public static String DELETE_PATH;
    public static String UPLOAD_FILE;

    /*swagger*/
    public static String BASE_PACKAGE_CONTROLLERS;

}
