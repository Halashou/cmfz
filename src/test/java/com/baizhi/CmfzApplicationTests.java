package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CmfzApplicationTests {
@Autowired
private AdminDao adminDao;
    @Test
    void contextLoads(){
        List<Admin> admins = adminDao.selectAll();
            admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void poi(){
//        User user1 = new User("1", "小黑", 12, new Date());
//        User user2 = new User("2", "小红", 26, new Date());
//        User user3 = new User("3", "小绿", 23, new Date());
//        User user4 = new User("4", "小紫", 17, new Date());
//        User user5 = new User("5", "小蓝", 31, new Date());
//        User user6 = new User("6", "小黄", 18, new Date());
        //List<User> users = Arrays.asList(user1,user2,user3,user4,user5,user6);
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表 并返回sheet对象
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        //创建行 并返回行对象
        HSSFRow row = sheet.createRow(0);
        //定义表头
        String[] title ={"id","name","age","date"};
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }

        //创建样式对象
        CellStyle cellStyle2 = workbook.createCellStyle();
        //创建日期对象
        DataFormat dataFormat = workbook.createDataFormat();
        //设置日期格式
        cellStyle2.setDataFormat(dataFormat.getFormat("yyy年MM月dd日"));
        //写入数据
//        for (int i = 0; i < users.size(); i++) {
//            HSSFRow row2 = sheet.createRow(i+1);
//            row2.createCell(0).setCellValue(users.get(i).getId());
//            row2.createCell(1).setCellValue(users.get(i).getName());
//            row2.createCell(2).setCellValue(users.get(i).getAge());
//            //前面设置了样式的格式，
//            HSSFCell cell = row2.createCell(3);
//            cell.setCellStyle(cellStyle2);
//            cell.setCellValue(users.get(i).getDate());
//        }
//


        try {
            workbook.write(new FileOutputStream(new File("F:/poi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关流
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
