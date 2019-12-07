package com.baizhi.service;

import com.baizhi.dao.CourseDao;
import com.baizhi.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    private String message="";
    private String status="";
    @Override
    public Map findAll(Course course) {
        Map map=new HashMap();
        try {
            List<Course> courses = courseDao.select(course);
            message="courses查找成功";
            status="200";
            map.put("option",courses);
        } catch (Exception e) {
            message="courses查找失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);

        return map;
    }

    @Override
    public Map addCourse(Course course){
        Map map=new HashMap();
        //补充缺失数据
        String id = UUID.randomUUID().toString();
        long time = new Date().getTime();
        java.sql.Date date = new java.sql.Date(time);
        String type="选修";
        course.setId(id);
        course.setCreate_date(date);
        course.setType(type);
        try {
            courseDao.insertSelective(course);
            message="course添加成功";
            status="200";
        } catch (Exception e){
            message="course添加失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);

        return map;
    }

    @Override
    public Map deleteCourse(String id){
        Map map=new HashMap();
        try {
            courseDao.deleteByPrimaryKey(id);
            message="course删除成功";
            status="200";
        } catch (Exception e){
            message="course删除失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);

        return map;

    }
}
