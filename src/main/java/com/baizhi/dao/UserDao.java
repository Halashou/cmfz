package com.baizhi.dao;

import com.baizhi.entity.MapVo;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Date;
import java.util.List;

public interface UserDao extends Mapper<User>{
    List<User> findAllByPage(Integer page, Integer rows);
    Integer findsexAndregestTime(@Param("sex") String sex, @Param("number") Integer number);
    List<MapVo> findLocationcount();
}
