package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Album implements Serializable {
    @Id
    private String id;
    private String title;
    private String score;
    private String author;
    private String broadcast;
    private Integer count;
    @Column(name="`desc`")
    private String desc;
    private String status;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="create_date")
    private Date create_date;
    private String cover;
}
