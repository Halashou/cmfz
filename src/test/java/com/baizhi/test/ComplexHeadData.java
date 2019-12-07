package com.baizhi.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplexHeadData implements Serializable {
    @ExcelProperty({"user", "username"})
    private String username;
    @ExcelProperty({"user", "password"})
    private String password;
    @ExcelProperty({"user", "date"})
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    private Date date;

}
