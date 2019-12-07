package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("guru")
public class GuruController{
    @Autowired
    private GuruService guruService;
    @RequestMapping("findAll")
    public List<Guru> findAll(){
        List<Guru> gurus = guruService.findAll();
        return gurus;
    }
    @RequestMapping("findAllByPage")
    public Map findAllByPage(Integer page, Integer rows, HttpServletRequest request){
        Map map = guruService.findAllByPage(page, rows);
        return map;
    }
}
