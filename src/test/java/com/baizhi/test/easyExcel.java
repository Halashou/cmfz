package com.baizhi.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baizhi.entity.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class easyExcel {
    @Test
    public void Test1(){
        //写法一
        String fileName="F:/DemoData.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }
    @Test
    public void Test2(){
        //写法二  虽然有点麻烦  但是 可以指定特定的模板去写
        String fileName="F:/DemoData.xlsx";
        ExcelWriter build = EasyExcel.write(fileName, DemoData.class).build();
        //写一个sheet模板对象
        WriteSheet sheet = EasyExcel.writerSheet("模板").build();
        build.write(data(),sheet);
        //记得关流
        build.finish();
    }
    @Test
    public void writeByexcludeName(){
        String fileName="F:/DemoDataByexcludeName.xlsx";
        HashSet<String> set = new HashSet<>();//定义不包括的字段集合 写的时候就不包括这个字段了
        set.add("date");
        EasyExcel.write(fileName,DemoData.class).excludeColumnFiledNames(set).sheet("excludByName").doWrite(data());
    }
    @Test
    public void read(){
        /*
        * 读操作 不同于poi  poi要指定行列进行读  而easyExel直接读
        * */
        String fileName="F:/DemoData.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ReadLisener()).sheet("模板").doRead();
    }
    @Test
    public void read2(){
        /*
         * 读操作 不同于poi  poi要指定行列进行读  而easyExel直接读
         * */
        String fileName="F:/DemoData.xlsx";
        ExcelReader build = EasyExcel.read(fileName, DemoData.class, new ReadLisener()).build();
        ReadSheet sheet = EasyExcel.readSheet("模板").build();
        build.read(sheet);
        build.finish();
    }
    /*
    * 写复杂头
    *
    * */
    @Test
    public void TestWriteComplexHeadData(){

        String fileName="F:/ComplexHeadData.xlsx";
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
    }
    @Test
    public void TestConverwriter(){
        String fileName="F:/TestConver.xlsx";
        ConverterData data1 = new ConverterData("an", new Date(), 0.23);
        ConverterData data2 = new ConverterData("an", new Date(), 0.23);
        ConverterData data3 = new ConverterData("an", new Date(), 0.23);
        EasyExcel.write(fileName, ConverterData.class).sheet("converter").doWrite(Arrays.asList(data1,data2,data3));

    }
    @Test
    public void TestConverread(){
        String fileName="F:/TestConver.xlsx";
       EasyExcel.read(fileName,ConverterData.class,new ConverterReadLisener()).sheet("converter").doRead();

    }
    @Test
    public void TestImageData() throws IOException {
        String fileName="F:/TestImageData.xlsx";
        ImageData data = new ImageData();
        File file = new File("F:/imageData.jpg");
        data.setFile(file);
        data.setString("F:/imageData.jpg");
        data.setInputStream(new FileInputStream(file));
        data.setByteArray(FileUtils.readFileToByteArray(file));
        data.setUrl(new URL("http://www.sx198.cn/p/a/20161120/201611201042536317261918900244.jpg"));
        EasyExcel.write(fileName,ImageData.class).sheet("image").doWrite(Arrays.asList(data));

    }
    @Test
    public List<DemoData> data(){
        DemoData date1=new DemoData("小明","123456",new Date(),null);
        DemoData date2=new DemoData("小明","123456",new Date(),null);
        DemoData date3=new DemoData("小明","123456",new Date(),null);
        DemoData date4=new DemoData("小明","123456",new Date(),null);
        List<DemoData> list = Arrays.asList(date1, date2, date3, date4);
        return list;
    }
}
