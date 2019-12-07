package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Counter implements Serializable{
    @Id
    private String id;
    private String title;
    private Integer count;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date create_date;
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "course_id")
    private String course_id;
}
