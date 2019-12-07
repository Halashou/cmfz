package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Counter;
import com.baizhi.entity.MapVo;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAllByPage(Integer page, Integer rows){
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds((page - 1) * rows, rows));
        Integer records=userDao.selectCount(new User());
        Integer total=records%rows==0?records/rows:records/rows+1;
        Map map=new HashMap();
        map.put("page",page);
        map.put("records",records);
        map.put("rows",users);
        map.put("total",total);
        return map;
    }

    @Override
    public Map findsexAndregestTime(){
        HashMap<String,List<Integer>> map = new HashMap<>();
        List men = new ArrayList<>();
        List women = new ArrayList<>();
        men.add(userDao.findsexAndregestTime("男", 1));
        men.add(userDao.findsexAndregestTime("男", 7));
        men.add(userDao.findsexAndregestTime("男", 30));
        men.add(userDao.findsexAndregestTime("男", 365));
        //nv
        women.add(userDao.findsexAndregestTime("女", 1));
        women.add(userDao.findsexAndregestTime("女", 7));
        women.add(userDao.findsexAndregestTime("女", 30));
        women.add(userDao.findsexAndregestTime("女", 365));
        map.put("men",men);
        map.put("women",women);
        return map;
    }

    @Override
    public List<MapVo> findLocationcount(){
        List<MapVo> list = userDao.findLocationcount();
        return list;
    }
//后台
    @Override
    public User findOne(String phone) {
        User user=new User();
        user.setPhone(phone);
        User one = userDao.selectOne(user);
        return one;
    }

    @Override
    public void addPhoneAndId(User user) {
        userDao.insert(user);
    }

    @Override
    public void addUserOtherMess(User user) {
        userDao.updateByPrimaryKey(user);
    }

    @Override
    public Map updateUserMes(User user) {
        String message="";
        String status="";
        Map map=new HashMap();
        try {
            userDao.updateByPrimaryKeySelective(user);
            message="user修改成功";
            status="200";

        } catch (Exception e){
            message="user修改失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }

    @Override
    public Map findOneByid(String id) {
        String message="";
        String status="";
        Map map=new HashMap();
        try {
            User user = userDao.selectByPrimaryKey(id);
            message="user查找成功";
            status="200";
            map.put("user",user);
        } catch (Exception e){
            message="user查找失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }
}
