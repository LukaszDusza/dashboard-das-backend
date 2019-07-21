package dfsp.commons;

/** Klasa - słownik - przechowująca stałe kofigurujące działanie aplikacji.*/

public class Naming {

    /** ustawienia aplikacji*/

    public static final String TEST_TABLE_NAME = "raport_test";
    public static final String TEST_TABLE_NAME_2 = "raport_test_2";
//    public static final String LOCAL_PATH = "c:\\files\\raports\\";
    public static final String LOCAL_PATH = "/home/das/raports/";
//    public static final String LOGO_PATH = "c:\\files\\logo\\";
    public static final String LOGO_PATH = "/home/das/logo/";
    public static final String FILES = "/api/v1/files";
    public static final String DOWNLOAD_PATH = "/download/";
    public static final String DELETE_PATH = "/delete/";
    public static final String UPLOAD_PATH = "/upload";

    /** ustawienia secirity*/

    public static final int EXPIRATION_TIME = 3_600_000; //1 hour
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/signup**";
    public static final String LOGIN = "/login**";
    public static final String SIGNING_KEY = "secret";
    public static final String ROLE = "role";
    public static final String TOKEN_AUTH = "/token/generate**";
    public static final String LOGO_URL = "/api/v1/files/download/logo/**";


}
