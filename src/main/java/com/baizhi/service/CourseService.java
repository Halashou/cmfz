package com.baizhi.service;

import com.baizhi.entity.Course;

import java.util.Map;

public interface CourseService {
    Map findAll(Course course);
    Map addCourse(Course course);
    Map deleteCourse(String id);
}
