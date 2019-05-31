package cn.hbkcn.bean2excel;

import cn.hbkcn.querystu.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // 输出文件
        File file = new File("D:\\out.xls");

        // 需要保存的数据
        ArrayList<Student> students = readXLS();

        new Worker(file, Student.class, students).run();
    }

    /**
     * 读取测试表数据
     * @return Students
     */
    public static ArrayList<Student> readXLS() {
        ArrayList<Student> list = new ArrayList<>(5000);
        try {
            String filePath = "D:\\student.xls";
            InputStream is = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            Student stu;
            for (int i = 2; i < rowNum; i++) {
                HSSFRow row = sheet.getRow(i);

                HSSFCell cell = row.getCell(0);
                String year = cell.getStringCellValue();

                cell = row.getCell(1);
                String schoolId = cell.getStringCellValue();

                cell = row.getCell(2);
                String name = cell.getStringCellValue();

                cell = row.getCell(3);
                String sex = cell.getStringCellValue();

                cell = row.getCell(4);
                String clazz = cell.getStringCellValue();

                cell = row.getCell(7);
                String id = cell.getStringCellValue();

                stu = new Student(year, name, schoolId, sex, clazz, id);
                list.add(stu);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
