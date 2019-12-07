package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Guru implements Serializable {
    @Id
    private String id;
    private String name;
    private String photo;
    private String status;
    @Column(name = "nick_name")
    private String nick_name;
}
