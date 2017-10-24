/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicselector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rustem Azimov
 */
public class ReadWriter {
    public void uploadToFile(String path, String[][] data) throws FileNotFoundException/*, NotExcelFileException*/, IOException, InvalidFormatException, NotExcelFileException {
        if(!path.endsWith(".xlsx"))
        {
            throw new NotExcelFileException();
        }
       //Create new workbook and tab
      Workbook wb = new XSSFWorkbook();//WorkbookFactory.create(new File(path));
      FileOutputStream fileOut = new FileOutputStream(path);
      Sheet sheet = wb.createSheet("Sheet");

      //Create 2D Cell Array
      Row[] row = new Row[data.length];
      Cell[][] cell = new Cell[row.length][];

      //Define and Assign Cell Data from Given
      for(int i = 0; i < row.length; i ++)
      {
          row[i] = sheet.createRow(i);
          cell[i] = new Cell[data[i].length];

          for(int j = 0; j < cell[i].length; j ++)
          {
              cell[i][j] = row[i].createCell(j);
              cell[i][j].setCellValue(data[i][j]);
              sheet.autoSizeColumn(cell[i][j].getColumnIndex());
          }
      }
      wb.write(fileOut);
      fileOut.flush();
      fileOut.close();
    }
    public String[][] readMatrixFromFile(String path) throws FileNotFoundException, IOException{
        XSSFRow row;
       XSSFCell cell;
       String[][] value = null;
       double[][] nums = null;
           FileInputStream inputStream = new FileInputStream(path);
           XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

           // get sheet number
           int sheetCn = workbook.getNumberOfSheets();

           for (int cn = 0; cn < sheetCn; cn++) {

               // get 0th sheet data
               XSSFSheet sheet = workbook.getSheetAt(cn);

               // get number of rows from sheet
               int rows = sheet.getPhysicalNumberOfRows();

               // get number of cell from row
               int cells = sheet.getRow(cn).getPhysicalNumberOfCells();

               value = new String[rows][cells];

               for (int r = 0; r < rows; r++) {
                   row = sheet.getRow(r); // bring row
                   if (row != null) {
                       for (int c = 0; c < cells; c++) {
                           cell = row.getCell(c);
                           nums = new double[rows][cells];

                           if (cell != null) {

                               switch (cell.getCellType()) {

                               case XSSFCell.CELL_TYPE_FORMULA:
                                   value[r][c] = cell.getCellFormula();
                                   break;

                               case XSSFCell.CELL_TYPE_NUMERIC:
                                   value[r][c] = ""
                                        + cell.getNumericCellValue();
                                   break;

                               case XSSFCell.CELL_TYPE_STRING:
                                   value[r][c] = ""
                                        + cell.getStringCellValue();
                                   break;

                               case XSSFCell.CELL_TYPE_BLANK:
                                  value[r][c] = "[BLANK]";
                                  break;

                               case XSSFCell.CELL_TYPE_ERROR:
                                  value[r][c] = ""+cell.getErrorCellValue();
                                break;
                            default:
                            }
                            //System.out.print(value[r][c]);

                        } else {
                            //System.out.print("[null]\t");
                        }
                    } // for(c)
                    //System.out.print("\n");
                }
            } // for(r)
        }
           return value;
    }
}
