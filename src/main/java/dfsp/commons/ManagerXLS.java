package dfsp.commons;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static dfsp.commons.Naming.LOCAL_PATH;

@AllArgsConstructor
@NoArgsConstructor
public class ManagerXLS<T> {

    Class<T> clazz;
    private static final Logger logger = Logger.getLogger(ManagerXLS.class.getName());

    public File saveListToXLSFileInToDirectory(String fileName, List<T> series) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // ---- create column titles and first row in document ----
        List<String> columnsTitles = new LinkedList<>();

        for (Field f : clazz.getDeclaredFields()) {
            columnsTitles.add(f.getName());
        }
      //  columnsTitles.remove(0); //without id

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnsTitles.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnsTitles.get(i));
            cell.setCellStyle(headerCellStyle);
        }

//        columnsTitles.forEach(
//                t -> {
//                    String name = "get" + t.substring(0, 1).toUpperCase() + t.substring(1);
//                    System.out.println(name);
//                }
//        );


        for (int i = 0; i < series.size(); i++) {

            HSSFRow row = sheet.createRow(i + 1);

            for (int j = 0; j < columnsTitles.size(); j++) {

                HSSFCell cell = row.createCell(j);
                Method method = series.get(i).getClass().getMethod("get" + columnsTitles.get(j).substring(0, 1).toUpperCase() + columnsTitles.get(j).substring(1));

             //   System.out.println(method.getName());
                Object result = method.invoke(series.get(i));
                cell.setCellValue(String.valueOf(result));

            }
        }

        for (int i = 0; i < columnsTitles.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        String file = LOCAL_PATH + fileName + " " + DateParser.millsToStringDate(System.currentTimeMillis()) + ".xls";
        workbook.write(new File(file));
        workbook.close();

        return new File(file);
    }

    public List<T> loadXLSFileToList(MultipartFile file) throws IOException, ParseException, NoSuchMethodException, NullPointerException {

        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<T> series = new LinkedList<>();

        Constructor<T> reflConstruct = clazz.getConstructor();

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {

            List<String> props = new LinkedList<>();

            for (int collIndex = 0; collIndex < sheet.getRow(0).getPhysicalNumberOfCells(); collIndex++) {

             //   try {
                    Cell cell = sheet.getRow(rowIndex).getCell(collIndex);
                    props.add(cell.toString());

            //    } catch (NullPointerException e) {
            //        Cell cell = sheet.getRow(rowIndex).createCell(collIndex);
            //        cell.setCellValue("");
            //        props.add(cell.toString());

            //    }

            }

            try {

                Object ob = reflConstruct.newInstance();

                int count = 0;
                for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
                    Field field = clazz.getDeclaredFields()[i];
                    field.setAccessible(true);

                    if (field.getType().getCanonicalName().equals("java.lang.String")) {
                        try {
                            field.set(ob, props.get(count));

                        } catch (NullPointerException e) {
                            logger.info("String null value in:" + count + " Setting data to null");
                            field.set(ob, null);
                        }
                    }

                    if (field.getType().getCanonicalName().equals("java.sql.Date")) {

                        try {
                            field.set(ob, DateParser.toSqlDate(props.get(count)));

                        } catch (ParseException e) {
                            logger.info("sql.Date null value in:" + count + " Setting data to null");
                            field.set(ob, null);
                        }
                    }

                    if (field.getType().getCanonicalName().equals("java.math.BigDecimal")) {
                        try {
                            field.set(ob, new BigDecimal(props.get(count)));
                        } catch (NumberFormatException e) {
                            logger.info("BigDecimal null value in:" + count + " Setting data to null");
                            field.set(ob, null);
                        }
                    }
                    count++;
                }
                series.add((T) ob);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        workbook.close();
        inputStream.close();

        return series;

    }
}
