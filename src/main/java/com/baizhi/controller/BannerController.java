package com.baizhi.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.baizhi.util.UtilHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows) {
        Map map = bannerService.findAll(page, rows);
        return map;
    }
    @RequestMapping("oper")
    public Map oper(String oper, Banner banner,String[] id){
        Map map=null;
        if("add".equals(oper)){
          map = bannerService.add(banner);
       }
       if("del".equals(oper)){
           map = bannerService.delete(id);
       }
       if("edit".equals(oper)){
           map=bannerService.update(banner);
       }
        return map;
    }
    @RequestMapping("upload")
    public void upload(MultipartFile url, String bannerId,HttpServletRequest request) throws UnknownHostException {
        // String uri="../upload/image/"+filename;为了防止换端口项目  设置动态网络资源路径
        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置

        //通过工具类 上传文件并获取网络路径   dir  为自己制定的路径
        String http1 = UtilHttp.getHttp(url, request, "/upload/image/");

        Banner banner=new Banner();
        banner.setId(bannerId);
        banner.setUrl(http1);
        bannerService.updateByPkey(banner);
    }
    @RequestMapping("downeasyExcel")
    public void downeasyExcel(HttpServletRequest request,HttpServletResponse response){
        String fileName="banner.xls";
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        ServletOutputStream outputStream = null;
        try {
             outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Banner> banners = bannerService.findAll();
        EasyExcel.write(outputStream).head(Banner.class).sheet("banner").doWrite(banners);
    }
}
