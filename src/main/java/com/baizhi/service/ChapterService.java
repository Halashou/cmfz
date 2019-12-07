package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map findAll(Integer page, Integer rows,String id);
    Map add(Chapter chapter);
    void updateById(Chapter chapter);
    //后台
    List<Chapter> findChaptersByalbumId(Chapter chapter);
}
