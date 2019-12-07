package com.baizhi.service;

import com.baizhi.entity.MapVo;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    //后台管理
    Map findAllByPage(Integer page, Integer rows);
    Map<String,List<Integer>> findsexAndregestTime();
    List<MapVo> findLocationcount();
    //前台
    //登录
    User findOne(String phone);//先查找 校检
    void addPhoneAndId(User user);
    void addUserOtherMess(User user);
    Map updateUserMes(User user);
    Map findOneByid(String id);


}
