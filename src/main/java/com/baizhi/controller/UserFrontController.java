package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.UtilPhoneCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("frontUser")
public class UserFrontController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("findOne")
    public Map findOne(String phone,String password){
        Map map=new HashMap();
        String message="";
        String status="";
        User one = userService.findOne(phone);
        if (one!=null){
            if (one.getPassword().equals(password)){
                status="200";
                map.put("user",one);
            }else {
                status="-200";
                message="密码不正确";
                map.put("message",message);
            }
        }else {
            status="-200";
            message="用户不存在";
            map.put("message",message);
        }
        map.put("status",status);
        return map;
    }
    //验证码验证
    @RequestMapping("phoneCode")
    public Map phoneCode(String phone, HttpServletRequest request){
      //获得验证码
        Map map=new HashMap();
        String message="";
        String status="";
        //生成验证码
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        String code = String.valueOf(i);//六位

        if(code!=null){
            UtilPhoneCode.sendPhoneCode(phone,code);
            stringRedisTemplate.opsForValue().set("code",code,60, TimeUnit.SECONDS);

            message="验证码发送成功";
            status="200";
        }else {
            message="验证码发送失败";
            status="-200";
        }
        map.put("message",message);
        map.put("status",status);
        //把验证码和手机号放入session

        return map;
    }
    //注册 校检验证码
    @RequestMapping("regist")
    public Map regist(String code,String phone,HttpServletRequest request){
        Map map=new HashMap();
        HttpSession session = request.getSession();
        session.setAttribute("phone",phone);
        String code1=stringRedisTemplate.opsForValue().get("code");
        String status="";
        String message="";
        String id=null;
        if(code1.equals(code)){
            //如果验证码成功  插入
            try {
                User user=new User();
                id=UUID.randomUUID().toString();
                user.setId(id);
                user.setPhone(phone);
                userService.addPhoneAndId(user);
                message="注册成功";
                status="200";
            } catch (Exception e) {
                message="注册失败";
                status="-200";
                e.printStackTrace();
            }
        }else{
            message="验证码错误";
            status="-200";
        }
        map.put("message",message);
        map.put("status",status);
        map.put("id",id);
        return map;
    }
    @RequestMapping("addUserOhterMess")
    public Map addUserOhterMess(User user,HttpSession session){
        String phone = (String) session.getAttribute("phone");
        System.out.println(phone);
        Map map=new HashMap();
        String status="";
        String message="";
        try {
            User one = userService.findOne(phone);
            String id = one.getId();
            user.setId(id);
            userService.addUserOtherMess(user);
             status="200";
             message="完善信息成功";
            User user1 = userService.findOne(phone);
            map.put("user",user1);
        } catch (Exception e) {
            status="-200";
            message="完善信息失败";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);

        return map;
    }
    @RequestMapping("updateUserMes")
    public Map updateUserMes(User user,String uid){
        user.setId(uid);
        Map map = null;
        try {
            map = userService.updateUserMes(user);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        Map map1 = userService.findOneByid(uid);
        return map1;
    }
}

