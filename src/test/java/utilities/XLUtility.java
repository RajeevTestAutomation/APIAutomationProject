package utilities;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
	
	

    public FileInputStream fi;

    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path){

        this.path=path;

            }

            public int getRowCount(String sheetName) throws IOException
            {

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
         return rowcount;

            }

            public int getCellCount(String sheetName, int rownum) throws IOException
            {

           fi= new FileInputStream(path);
           workbook=new XSSFWorkbook(fi);
           sheet=workbook.getSheet(sheetName);
           row=sheet.getRow(rownum);
           int cellCount=row.getLastCellNum();
           workbook.close();
           fi.close();
           return cellCount;


            }
            public String getCellData(String sheetName, int rownum,int colum) throws IOException
            {
              fi = new FileInputStream(path);
              workbook=new XSSFWorkbook(fi);
              sheet=workbook.getSheet(sheetName);
              row=sheet.getRow(rownum);
              cell=row.getCell(colum);
                DataFormatter formatter=new DataFormatter();
                String data;
                try
                {
                    data = formatter.formatCellValue(cell); // return the formatted value of cell as string regardless


                } catch (Exception e)
                {
                    data ="";
                                    }
                workbook.close();
                fi.close();
                return data;

            }

            public void setCellData(String SheetName,int rownum,int colnum,String data) throws IOException

            {
                File xlfile=new File(path);
                if(xlfile.exists());  // if file not exist then create file
                {
                    workbook=new XSSFWorkbook();
                    fo=new FileOutputStream(path);
                    workbook.write(fo);
                                    }

                   fi=new FileInputStream(path);
                workbook = new XSSFWorkbook(fi);
                if(workbook.getSheetIndex(SheetName)==-1) // if sheet not exist then create new sheet
                {
                    workbook.createSheet(SheetName);
                    sheet = workbook.getSheet(SheetName);
                }
                if(sheet.getRow(rownum)==null) // if row not exist create new row
                {
                    sheet.createRow(rownum);
                    row=sheet.getRow(rownum);

                    cell=row.createCell(colnum);
                    cell.setCellValue(data);

                   fo=new FileOutputStream(path);
                   workbook.write(fo);
                   workbook.close();
                   fi.close();
                   fo.close();


                }



            }


}
