package com.baizhi.service;

import com.baizhi.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    List<Guru> findAll();
    Map findAllByPage(Integer page, Integer rows);
}
