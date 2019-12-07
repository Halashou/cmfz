package com.baizhi.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Banner implements Serializable {
    @Id
    @ExcelProperty("编号")
    private String id;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("图片")
    private String url;
    @ExcelIgnore
    private String href;
    @ExcelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="create_date")
    private Date create_date;
    @ExcelProperty("描述")
    @Column(name = "`desc`")
    private String desc;
    @ExcelProperty("状态")
    private String status;
}
