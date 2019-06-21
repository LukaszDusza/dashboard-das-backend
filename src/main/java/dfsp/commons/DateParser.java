package dfsp.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateParser {
    private static final Logger logger = Logger.getLogger(DateParser.class.getName());

    public static java.sql.Date toSqlDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = formatter.parse(date);
        return new java.sql.Date(parsedDate.getTime());

    }

    public static Date toUtilDate(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);

    }
}
