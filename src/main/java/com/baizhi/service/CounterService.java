package com.baizhi.service;

import com.baizhi.entity.Counter;

import java.util.List;
import java.util.Map;

public interface CounterService {
    Map findAllByUid(Counter counter);
    Map findOneById(String id);
    Map addCounter(Counter counter);
    Map deleteCounter(String id);
    Map updateCounter(Counter counter);
}
