package molodost.bz.db;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ExcelRead {
    private int headerLine = 0;
    private int currentLine = 1;
    private int currentRow;

    public static void main(String[] args) {
        ExcelRead excelRead = new ExcelRead();
        Map<String, String> tickets = excelRead.read("C:\\IntellijIdea\\UltimateEdition\\MolodostParse\\accounts.xls", "List");
        System.out.println(tickets.size());

    }

    public Map<String, String> read(String inputFile, String nameOfSheet) {
        Sheet sheet = null;
        Map<String, String> headerMap = new HashMap<>();
        int x = 1;
        int y = 2;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(inputFile));
            sheet = workbook.getSheet(nameOfSheet);
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        while (true) {
//            System.out.println(sheet.getCell(x, y).getContents());
//            System.out.println(sheet.getCell(y, x).getContents());
            headerMap.put(sheet.getCell(x, y).getContents(), sheet.getCell(x+2, y ).getContents());
            y++;
            if(y==98){
                break;
            }
        }
        return headerMap;
    }

}