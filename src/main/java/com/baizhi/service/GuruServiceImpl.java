package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GuruServiceImpl implements GuruService{
    @Autowired
    private GuruDao guruDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Guru> findAll() {
        List<Guru> gurus = guruDao.selectAll();
        return gurus;
    }

    @Override
    public Map findAllByPage(Integer page, Integer rows){
        Map map=new HashMap();
        List<Guru> gurus = guruDao.selectByRowBounds(new Guru(), new RowBounds((page - 1) * rows, rows));
        Integer records=guruDao.selectCount(new Guru());
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",gurus);
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}
