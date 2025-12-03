package utils;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
    private static Workbook  workbook;
    private static Sheet sheet;


    public static void loadExcel(String filepath,String sheetname) throws IOException
    {
        FileInputStream file=new FileInputStream(filepath);

        workbook=new XSSFWorkbook(file);
        sheet=workbook.getSheet(sheetname);

        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetname + "' not found in Excel file");
        }

        file.close(); // Close input stream
    }


    //Get cell data as String (Null-safe, handles all types)
    public static String getCellData(int row, int col)
    {
        Row rowobj = sheet.getRow(row);
        if (rowobj== null) return "";


        Cell cell = rowobj.getCell(col);
        if (cell == null) return "";

        CellType cellType = cell.getCellType();

        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getStringCellValue();

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    //Get total rows (physical count)
    public static int getRowCount()
    {
        return sheet.getPhysicalNumberOfRows();

    }


    //close workbook
    public static void closeExcel() throws IOException
    {
        if (workbook != null) {
        workbook.close();
    }
    }
}
