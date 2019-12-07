package com.baizhi.test;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class goeasyTest {
@Test
    public void goeasy(){
    GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-5a085c1e051f404892644baedd1ef85d");
            goEasy.publish("cmfz", "Hello, GoEasy!");
}
}
