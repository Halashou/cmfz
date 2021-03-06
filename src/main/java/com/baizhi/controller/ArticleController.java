package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UtilHttp;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows){
        Map map = articleService.findAll(page, rows);
        return map;
    }
@RequestMapping("add")
    public String add(MultipartFile imgFile, Article article, HttpServletRequest request) throws UnknownHostException {
    System.out.println(article);
        String dir="/upload/article/";
        // 上传并 获得封面地址
    String http = UtilHttp.getHttp(imgFile, request, dir);
    article.setImg(http);
        articleService.add(article);
        return null;
    }
    /*
    2. 完成上传文件操作
        注意 : 上传文件默认为 imgFile 可以通过 KindEditor中的filePostName 进行修改
    3. 返回值类型指定
    //成功时
    {
    "error" : 0,
    "url" : "http://www.example.com/path/to/file.ext"
    }
    //失败时
{
    "error" : 1,
    "message" : "错误信息"
}
 */
    @RequestMapping("uploadKindeditorImg")
    public Map uploadKindeditorImg(MultipartFile imgFile,HttpServletRequest request){
        System.out.println(imgFile.getOriginalFilename());
        Map map=new HashMap();
        String dir="/upload/kindeditorimg/";
        // 上传并 获得封面地址
        try {
            String http = UtilHttp.getHttp(imgFile, request, dir);
            map.put("error",0);
            map.put("url",http);
        } catch (UnknownHostException e) {
            map.put("error",1);
            map.put("message","上传错误");
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("showAllImgs")
    public Map showAllImgs(HttpSession session, HttpServletRequest request){
        // 1. 获取文件夹绝对路径
        String realPath = session.getServletContext().getRealPath("/upload/kindeditorimg/");
        // 2. 准备返回的Json数据
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        // 3. 获取目标文件夹
        File file = new File(realPath);
        File[] files = file.listFiles();
        // 4. 遍历文件夹中的文件
        for (File file1 : files) {
            // 5. 文件属性封装
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            // 获取文件后缀 | 文件类型
            String extension = FilenameUtils.getExtension(file1.getName());
            fileMap.put("filetype",extension);
            fileMap.put("filename",file1.getName());
            // 获取文件上传时间 1. 截取时间戳 2. 创建格式转化对象 3. 格式类型转换
            String s = file1.getName().split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(s)));
            fileMap.put("datetime",format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        hashMap.put("total_count",arrayList.size());
        // 返回路径为 项目名 + 文件夹路径
        hashMap.put("current_url",request.getContextPath()+"/upload/kindeditorimg/");
        return hashMap;
    }
}
