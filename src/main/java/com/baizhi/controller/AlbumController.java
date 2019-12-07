package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.UtilHttp;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows){
        Map map = albumService.findAll(page, rows);
        return map;
    }
    @RequestMapping("findAllChapters")
    public Map findAllChapters(Integer page,Integer rows,String id){
        Map map = chapterService.findAll(page, rows,id);
        return map;
    }
    @RequestMapping("chapterOper")
    public Map oper(String oper, Chapter chapter,String[] id,String albumId){
        Map map=null;
        if("add".equals(oper)){
            chapter.setUrl(null);//地址没用
            chapter.setAlbum_id(albumId);
            map = chapterService.add(chapter);
        }
        if("del".equals(oper)){}
        if ("edit".equals(oper)){

        }
        return map;
    }
    @RequestMapping("chapterUpload")
    public void upload(MultipartFile url, HttpSession session, String chapterId, HttpServletRequest request) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        //上传文件
        String http = UtilHttp.getHttp(url,request,"/upload/chapter/");
        //上传之后修改数据库的内容
        String[] split = http.split("/");
        //获得文件名 157...xxx.mp3
        String filename = split[split.length - 1];
        //获得文件对象
        File file=new File(session.getServletContext().getRealPath("/upload/chapter/"),filename);
        //求文件大小 单位B  !!!需要处理音频的jar
        long length = file.length();
        double a=(double)length/1024/1024;
        //把a的精度调成两位
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String size = nf.format(a)+"MB";
        //获得文件的操作对象
        MP3File audioFile = (MP3File) AudioFileIO.read(file);
        //获得请求头文件 .获得时长 s
        int trackLength = audioFile.getMP3AudioHeader().getTrackLength();
        String time=trackLength/60+"分";
        String time2=trackLength%60+"秒";
        //把数据封装成数据 导入到数据库
        Chapter c =new Chapter();
        c.setUrl(http).setSize(size).setTime(time+time2).setId(chapterId);
        chapterService.updateById(c);
    }
@RequestMapping("download")
    public void download(String url, HttpServletRequest request, HttpServletResponse response) throws IOException{
        //获得文件的路径
    String[] split1 = url.split("/");
    String path = request.getSession().getServletContext().getRealPath("/upload/chapter/");
    InputStream fis = new FileInputStream(new File(path,split1[split1.length-1]));

    String[] split = url.split("_");
    response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(split[1],"UTF-8"));
    ServletOutputStream outputStream = response.getOutputStream();
    IOUtils.copy(fis,outputStream);
    IOUtils.closeQuietly(fis);
    IOUtils.closeQuietly(outputStream);
}
}



