package dfsp.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateParser {
    private static final Logger logger = Logger.getLogger(DateParser.class.getName());

    public static java.sql.Date toSqlDate(String date) throws ParseException {
    //    System.out.println("before"  + date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date parsedDate = formatter.parse(date);
     //   System.out.println(new java.sql.Date(parsedDate.getTime()));
        return new java.sql.Date(parsedDate.getTime());

    }

    public static java.sql.Date fromSqlDate(String date) throws ParseException {
        //    System.out.println("before"  + date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = formatter.parse(date);
        //   System.out.println(new java.sql.Date(parsedDate.getTime()));
        return new java.sql.Date(parsedDate.getTime());

    }

    public static Date toUtilDate(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }

    public static String millsToStringDate(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date resultdate = new Date(mills);
        return sdf.format(resultdate);
    }
}
