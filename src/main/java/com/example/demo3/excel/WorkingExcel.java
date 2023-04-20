package com.example.demo3.excel;

import com.example.demo3.entity.Dish;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WorkingExcel {

    public List<Dish> Reading(InputStream file_path){

        try{
//        FileInputStream excelFile = new FileInputStream(new File(file_path));
        Workbook workbook = WorkbookFactory.create(file_path);
        Sheet firstSheet = workbook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();

            Iterator<Row> iterator = firstSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);

            System.out.println(firstCell.getStringCellValue());
            List<Dish> listofDish = new ArrayList<Dish>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Dish dish = new Dish();
                dish.setId((long) Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
                dish.setName(fmt.formatCellValue(currentRow.getCell(1)));
                dish.setCount(Integer.valueOf(fmt.formatCellValue(currentRow.getCell(2))));
                dish.setUnit(fmt.formatCellValue(currentRow.getCell(3)));
                dish.setPrice(Double.valueOf(fmt.formatCellValue(currentRow.getCell(4))));
                dish.setStatus(Integer.valueOf(fmt.formatCellValue(currentRow.getCell(5))));
                dish.setImage(fmt.formatCellValue(currentRow.getCell(6)));
                dish.setCategory_id(Long.valueOf(Integer.valueOf(fmt.formatCellValue(currentRow.getCell(7)))));

                listofDish.add(dish);
            }
            for (Dish dish : listofDish) {
                System.out.println(dish);
            }

            workbook.close();
            return listofDish;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        };
        return null;
    }

    public void Writing(List<Dish> data, String file_path) throws IOException {
//        Khởi tạo  wordbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        Row headerRow =     sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Danh sách");
//        danh sách

        Row header = sheet.createRow(1);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Count");
        header.createCell(3).setCellValue("Unit");
        header.createCell(4).setCellValue("Price");
        header.createCell(5).setCellValue("Status");
        header.createCell(6).setCellValue("Image");
        header.createCell(7).setCellValue("Category ID");
//
        int rowIndex = 2;
        for(Dish dish : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(dish.getId());
            row.createCell(1).setCellValue(dish.getName());
            row.createCell(2).setCellValue(dish.getCount());
            row.createCell(3).setCellValue(dish.getUnit());
            row.createCell(4).setCellValue(dish.getPrice());
            row.createCell(5).setCellValue(dish.getStatus());
            row.createCell(6).setCellValue(dish.getImage());
            row.createCell(7).setCellValue(dish.getCategory_id());
        }
        FileOutputStream outputStream = new FileOutputStream(file_path);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
