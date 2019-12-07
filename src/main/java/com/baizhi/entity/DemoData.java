package com.baizhi.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData implements Serializable {
    @ExcelProperty( value = "用户名",index = 2)//index指定写的开始的列
    private String username;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    private Date date;
    @ExcelIgnore
    private String ignore;

}
