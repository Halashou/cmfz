package com.baizhi.test;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import com.baizhi.util.UtilPhoneCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.UUDecoder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void TestUser(){
        UtilPhoneCode.sendPhoneCode("15937805968","1235");
    }
    @Test
    public  void TestCode(){
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        String s = String.valueOf(i);
        System.out.println(s);
        System.out.println((Math.random()*9)+1);
    }
    @Test
    public void TestRedis(){
        Guru guru = new Guru();
        guru.setId("1234");
        guru.setName("大佬啊");
        guru.setNick_name("大师");
        guru.setPhoto("www");
        guru.setStatus("1");
        Guru guru2 = new Guru();
        guru2.setId("12345");
        guru2.setName("大佬啊5");
        guru2.setNick_name("大师5");
        guru2.setPhoto("www5");
        guru2.setStatus("15");
        redisTemplate.opsForSet().add("set1",guru);
        redisTemplate.opsForSet().add("set1",guru2);


    }
    @Test
    public void TestGetRedis(){
     //  Guru o = (Guru) redisTemplate.opsForHash().get("gurus", "guru");
        Set<Guru> set1 = redisTemplate.opsForSet().members("set1");
        for (Guru guru : set1) {
            System.out.println(guru);
        }
    }
    @Test
    public void TestGetRedis2(){
        //  Guru o = (Guru) redisTemplate.opsForHash().get("gurus", "guru");
        stringRedisTemplate.opsForValue().set("code2","123456",60, TimeUnit.SECONDS);
        System.out.println("==========");
        String code = stringRedisTemplate.opsForValue().get("code2");
        System.out.println(code);
        }

}
