package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin findOneByUsername(String username){
        Admin admin=new Admin();
        admin.setUsername(username);
        //根据username的查询条件，查找返回一个对象  不同与select
        Admin admin1 = adminDao.selectOne(admin);

        return admin1;
    }
}
