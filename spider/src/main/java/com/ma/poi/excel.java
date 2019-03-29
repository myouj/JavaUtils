package com.ma.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class excel {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 读取Excel文件信息
     * @param filePath
     * @throws Exception
     */
    static void readExcel(String filePath) throws Exception{

        File file = new File(filePath);
        //判断文件是否存在
        checkFile(file);
        //创建输入流
        FileInputStream fis = new FileInputStream(file);

        Workbook workBook = getWorkBook(fis, file);
        //从第一行开始读，包括表头
        Sheet sheet = workBook.getSheetAt(0);

        StringBuffer sb = new StringBuffer("");
        //获取行数
        int lastRowNum = sheet.getLastRowNum();

        for(int i = 0; i <= lastRowNum; i++){
            //获取第i行的信息
            Row row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String code = row.getCell(1).getStringCellValue();
            sb.append(name + "\t");
            sb.append(code + "\n");
        }

        System.out.println(sb.toString());

    }

    /**
     * 判断文件类型，是xls还是xlsx
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    static Workbook getWorkBook(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否存在
     * @param file
     * @throws Exception
     */
    static void checkFile(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在！");
        }
        if(!(file.exists() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith((EXCEL_XLSX))))){
            throw new Exception("不是Excel文件！");
        }
    }

    public static void main(String[] args) throws Exception {
        readExcel("D:\\workspace\\utils\\spider\\src\\main\\resources\\别名定义-1.xlsx");
    }

}
