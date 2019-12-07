package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("code")
    public String code(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //获得验证码  并把验证码放入session
        String code = SecurityCode.getSecurityCode();
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        //获得验证码图片
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"png",outputStream);
        return null;
    }
    @RequestMapping("login")
    public String login(Admin admin,String code,HttpServletRequest request){
        String code1 = (String) request.getSession().getAttribute("code");
        Admin admin1 = adminService.findOneByUsername(admin.getUsername());
        if(code!=null){
            if (code.equals(code1)){
                if(admin1!=null){
                    if(admin1.getPassword().equals(admin.getPassword())){
                        return "back/home";
                    }else {
                        return "back/login";
                    }
                }
            }else{//验证码错误
                return "back/login";
            }
        }
        return "back/login";
    }
}
