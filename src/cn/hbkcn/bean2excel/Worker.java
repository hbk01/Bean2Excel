package cn.hbkcn.bean2excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 2019/3/26 14:09
 */
public class Worker {
    private File out;
    private Class clazz;
    private ArrayList<? extends Bean> list;

    public Worker(File out, Class clazz, ArrayList<? extends Bean> list) {
        this.out = out;
        this.clazz = clazz;
        this.list = list;
    }

    public void run() {
        // 传入的class不能为空
        if (clazz == null) {
            System.out.println("Class is null.");
            return;
        }

        ArrayList<String> titles = new ArrayList<>();

        // 查找需要作为标题的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            cn.hbkcn.bean2excel.Field annotation = field.getAnnotation(cn.hbkcn.bean2excel.Field.class);
            if (annotation == null) {
                continue;
            }
            String name = "".equals(annotation.value()) ? field.getName() : annotation.value();
            titles.add(name);
        }

        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet(clazz.getSimpleName());

        // 获得第一行
        HSSFRow row = sheet.createRow(0);

        // 将标题写入第一行
        for (int i = 0; i < titles.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(titles.get(i));
        }

        for (int i = 1; i < list.size(); i++) {
            row = sheet.createRow(i);
            Bean bean = list.get(i);

            Class clazz1 = bean.getClass();
            Field[] fields1 = clazz1.getDeclaredFields();
            for (int j = 0; j < fields1.length; j++) {
                Field field = fields1[j];
                // 获取含有注解的字段
                cn.hbkcn.bean2excel.Field annotation = field.getAnnotation(cn.hbkcn.bean2excel.Field.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    String value = field.get(bean).toString();
                    HSSFCell cell = row.createCell(j);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
