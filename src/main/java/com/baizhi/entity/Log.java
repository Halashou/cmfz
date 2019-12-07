package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Log {
    @Id
    private String id;
    private String username;
    @JSONField(format = "yyyy-MM-dd:HH-mm-ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    @Column(name = "`time`")
    private Date time;
    private String methodname;
    private String status;
}
