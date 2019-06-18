/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xslhtmlwriter;

/**
 *
 * @author Andi
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Andi
 */
public class main {

    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        main http = new main();
        List<String> filledData = new LinkedList<String>();

        File myFile = new File("/Users/Andi/excelsnake/090419/Preisliste_020419_172739.xlsx");

        // Finds the workbook instance for XLSX file 
        FileInputStream fis = new FileInputStream(myFile);

        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook 
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        
        
        // Get iterator to all the rows in current sheet 
        Iterator<Row> rowIterator = mySheet.iterator(); // Traversing over each row of XLSX file 

        int filledInt = 0;
        
        while (rowIterator.hasNext()) {
            

            Row row = rowIterator.next();

            Cell url = row.getCell(14);
            
            try {
                if(url.getStringCellValue().equals("Textpfad")){
                    filledInt++;
                    continue;
                }
                
                String response = http.sendGet(url.getStringCellValue());
                
                filledData.add(response);
                
            } catch (NullPointerException e) {
                Cell url2 = row.getCell(13);
                try{
                    String maybeHtml = url2.getStringCellValue();
                    if(!maybeHtml.contains("JPG")){
                        String response = http.sendGet(maybeHtml);
                        filledData.add(response);
                    }else{
                        //System.out.println("habeinen " + filledInt);
                        filledData.add("\t");
                    }
                }catch (NullPointerException e2){
                //System.out.println("habeinen " + filledInt);
                filledData.add("\t");
            }
             
            }
            if (filledInt % 1000 == 0) {
                System.out.print(".");
            }

            filledInt++;

        }

        http.write(filledData);
    }

    private String sendGet(String get) throws Exception {

        
        URL url;
        StringBuffer response = new StringBuffer();
        try {
            url = new URL(get);
            // Reader reader = new InputStreamReader(url.openStream(),"UTF-8");
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
            connection.setRequestProperty("Accept-Charset", "ISO-8859-15");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ISO-8859-15"));
            
            String inputLine;
            
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
                in.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return response.toString();

    }

    public void write(List<String> filledData) throws FileNotFoundException, IOException {
        File outputFile = new File("/Users/Andi/excelsnake/PreislistenurHTML.xlsx");
        FileInputStream fisOut = new FileInputStream(outputFile);
        XSSFWorkbook myWorkBookOutput = new XSSFWorkbook(fisOut);
        XSSFSheet myOutputSheet = myWorkBookOutput.getSheetAt(0);


        int rownum = 0;
        int length = filledData.size();
        for (int i = 0; i < length; i++) {
// Creating a new Row in existing XLSX sheet 
            Row row = myOutputSheet.createRow(rownum++);
            String text = filledData.get(i);
            int cellnum = 0;
            Cell cell = row.createCell(cellnum);
            cell.setCellValue(text);
            
        }
// open an OutputStream to save written data into XLSX file 
        FileOutputStream os = new FileOutputStream(outputFile);
        myWorkBookOutput.write(os);
        System.out.println("Writing on XLSX file Finished ...");
    }
}
