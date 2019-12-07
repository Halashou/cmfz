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
public class User implements Serializable{
    @Id
    private String id;
    private String phone;
    private String password;
    private String salt;
    private String status;
    private String photo;
    private String name;
    @Column(name = "nick_name")
    private String nick_name;
    private String sex;
    private String sign;
    private String location;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "rigest_date")
    private Date rigest_date;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "last_login")
    private Date last_login;
}
