package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course implements Serializable {
    @Id
    private String id;
    private String title;
    @Column(name = "user_id")
    private String user_id;
    private String type;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date create_date;
}
