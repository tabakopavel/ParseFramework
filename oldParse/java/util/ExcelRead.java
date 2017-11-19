package util;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;

public class ExcelRead {
    private String inputFile;
    WritableWorkbook myFirstWbook = null;
    WritableSheet excelSheet = null;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void set() {
        try {
            myFirstWbook = Workbook.createWorkbook(new File(inputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (myFirstWbook.getSheet("boardparse") == null) {
            excelSheet = myFirstWbook.createSheet("boardparse", 0);
        } else {
            excelSheet = myFirstWbook.getSheet("boardparse");
        }
    }

    public void write(int number, String name, String desc, String adress, String addPage, String theme, String region, String indexY, String indexG, String tiz, String pr, String date) {
        try {
            Label label = new Label(0, number, name);
            excelSheet.addCell(label);
            label = new Label(1, number, desc);
            excelSheet.addCell(label);
            label = new Label(2, number, adress);
            excelSheet.addCell(label);
            label = new Label(3, number, addPage);
            excelSheet.addCell(label);
            label = new Label(4, number, theme);
            excelSheet.addCell(label);
            label = new Label(5, number, region);
            excelSheet.addCell(label);
            label = new Label(6, number, indexY);
            excelSheet.addCell(label);
            label = new Label(7, number, indexG);
            excelSheet.addCell(label);
            label = new Label(8, number, tiz);
            excelSheet.addCell(label);
            label = new Label(9, number, pr);
            excelSheet.addCell(label);
            label = new Label(10, number, date);
            excelSheet.addCell(label);

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void writeModel(int number, String name, String ageAndInfo, String vk, String facebook, String insta, String webSite, String pointA, String pointB, String currentUrl) {

        try {
            Label label = new Label(0, number, name);
            excelSheet.addCell(label);
            label = new Label(1, number, ageAndInfo);
            excelSheet.addCell(label);
            label = new Label(2, number, vk);
            excelSheet.addCell(label);
            label = new Label(3, number, facebook);
            excelSheet.addCell(label);
            label = new Label(4, number, insta);
            excelSheet.addCell(label);
            label = new Label(5, number, webSite);
            excelSheet.addCell(label);
            label = new Label(6, number, pointA);
            excelSheet.addCell(label);
            label = new Label(7, number, pointB);
            excelSheet.addCell(label);
            label = new Label(8, number, currentUrl);
            excelSheet.addCell(label);

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        try {
            myFirstWbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (myFirstWbook != null) {
            try {
                myFirstWbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
