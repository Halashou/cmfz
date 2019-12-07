package com.baizhi.controller;

import com.baizhi.entity.MapVo;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows){
        Map map = userService.findAllByPage(page, rows);
        return map;
    }
    @RequestMapping("findsexAndregist")
    public Map<String, List<Integer>> findsexAndregist(){
        Map<String, List<Integer>> map = userService.findsexAndregestTime();
        return map;
    }
    @RequestMapping("findLocation")
    public List<MapVo> findLocation(){
        List<MapVo> list = userService.findLocationcount();
        return list;
    }

}
