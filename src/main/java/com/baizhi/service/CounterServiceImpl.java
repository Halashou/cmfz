package com.baizhi.service;

import com.baizhi.dao.CounterDao;
import com.baizhi.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    private String message="";
    private String status="";

    @Override
    public Map findAllByUid(Counter counter){//byuidandcourse_id
        Map map=new HashMap();
        try {
            List<Counter> counters = counterDao.select(counter);
            message="counters查找成功";
            status="200";
            map.put("counters",counters);

        } catch (Exception e){
            message="counters查找失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }

    @Override
    public Map findOneById(String id) {
        Map map=new HashMap();
        try {
            Counter counter = counterDao.selectByPrimaryKey(id);
            message="counters查找成功";
            status="200";
            map.put("counter",counter);

        } catch (Exception e){
            message="counters查找失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }

    @Override
    public Map addCounter(Counter counter) {
        Map map=new HashMap();
        counter.setId(UUID.randomUUID().toString());
        counter.setCount(0);
        long time = new Date().getTime();
        java.sql.Date date = new java.sql.Date(time);
        counter.setCreate_date(date);
        try {
            counterDao.insertSelective(counter);
            message="counter添加成功";
            status="200";
        } catch (Exception e){
            message="counter添加失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }

    @Override
    public Map deleteCounter(String id) {
        Map map=new HashMap();
        try {
            counterDao.deleteByPrimaryKey(id);
            message="counter删除成功";
            status="200";
        } catch (Exception e){
            message="counter删除失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }

    @Override
    public Map updateCounter(Counter counter) {
        Map map=new HashMap();
        try {
            counterDao.updateByPrimaryKeySelective(counter);
            message="counter修改成功";
            status="200";
        } catch (Exception e){
            message="counter修改失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }
}
