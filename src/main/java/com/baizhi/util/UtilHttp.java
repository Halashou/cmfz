package com.baizhi.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class UtilHttp{
    public static String getHttp(MultipartFile file, HttpServletRequest request,String dir) throws UnknownHostException {
        //如dir  为 /upload/image/
        String path = request.getSession().getServletContext().getRealPath(dir);
        File file1=new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }
        //防止重名
        String filename = new Date().getTime() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String http = request.getScheme();
        String localhost = InetAddress.getLocalHost().toString();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        String uri = http+"://"+localhost.split("/")[1]+":"+port+contextPath+dir+filename;
        return uri;
    }
}
