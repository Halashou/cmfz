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
public class Chapter implements Serializable {
    @Id
    private String id;
    private String title;
    private String url;
    private String size;
    private String time;
    @Column(name="create_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date create_time;
    @Column(name="album_id")
    private String album_id;
}
