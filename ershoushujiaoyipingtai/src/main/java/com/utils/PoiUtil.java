package com.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件导入到处
 */
public class PoiUtil {
    /**
     * 导入
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static List<List<String>> poiImport(String url) throws Exception {
        List<List<String>> list = new ArrayList<>();
        // 创建Excel 读取文件内容
        HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(new File(url)));
        /**
         * 第一种方式读取Sheet页
         */
//      HSSFSheet sheet = workbook.getSheet("Sheet0");
        /**
         * 第二种方式读取Sheet页
         */
        HSSFSheet sheet = workbook.getSheetAt(0);//获取工作表
        for (int i = 0; i < sheet.getLastRowNum()+1; i++) {
            HSSFRow row = sheet.getRow(i);//获取行
            List<String> rowlist = new ArrayList<>();//行数据
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getStringCellValue();
                rowlist.add(value);//行中数据添加到行中
            }
            list.add(rowlist);//将行数据添加到list中
        }
        return list;
    }






    // 导出
    public static void poiExport(List<List<String>> list, String url) throws Exception {
        //创建Excel工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作表shheet
        HSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            List<String> dataList = list.get(i);
            for (int j = 0; j < dataList.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(dataList.get(j));
            }

        }
        FileOutputStream stream = FileUtils.openOutputStream(new File(url));
        workbook.write(stream);
        stream.close();
    }


    public static void main(String[] args) {
        try {
            //导入
            List<List<String>> lists = PoiUtil.poiImport("C:/Users/Administrator/Desktop/工作1.xls");
            System.out.println();


            //导出

            PoiUtil.poiExport(lists, "C:/Users/Administrator/Desktop/工作1.xls");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
