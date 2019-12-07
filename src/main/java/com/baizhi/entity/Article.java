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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Article implements Serializable {
    @Id
    private String id;
    private String title;
    private String img;
    private String content;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="create_date")
    private Date create_date;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="publish_date")
    private Date publish_date;
    private String status;
    private String guru_id;

}
