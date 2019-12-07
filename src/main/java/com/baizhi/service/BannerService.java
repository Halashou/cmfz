package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //查找
    Map findAll(Integer page, Integer rows);
    Map add(Banner banner);
    Map update(Banner banner);
    void updateByPkey(Banner banner);
    Map delete(String[] id);
    List<Banner> findAll();
}
