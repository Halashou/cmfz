package com.baizhi.controller;

import com.baizhi.entity.*;
import com.baizhi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("firstPage")
public class FirstPageController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("showFirstPage")
    public Map showFirstPage(String uid,String type,String sub_type){
        //type all wen si  必填  sub_type ssyj xmfy
        Map map = new HashMap();
        String message="";
        String status="";
        if("all".equals(type)){
            //查找所有
            try {
                // 轮播图信息 head
                List<Banner> banners = bannerService.findAll();
                //albums 专辑信息 wen  吉祥秒音
                List<Album> albums = albumService.findAllAlbums();
                //articles 文章信息 si 甘露妙宝
                List<Article> articles = articleService.findAllArticles();
                message="all查找成功";
                status="200";
                map.put("head",banners);
                map.put("albums",albums);
                map.put("articles",articles);
            } catch (Exception e){
                message="all查找失败";
                status="-200";
                e.printStackTrace();
            }

        }
        if("wen".equals(type)){
            //查找 albums
            try {
                List<Album> albums = albumService.findAllAlbums();
                message="albums查找成功";
                status="200";
                map.put("albums",albums);
            } catch (Exception e) {
                message="albums查找失败";
                status="-200";
                e.printStackTrace();
            }

        }
        if("si".equals(type)){
            //查找articles 文章分为关注的上师的文章
            /*
            * 1.上师要求存在redis 如果sub_type为ssyj 就要找到关注的上师  然后查到关注的
            * 上师的文章 返回来
            * 2.如果是xmfy 就推荐文章  或者返回全部
            * */
            if("ssyj".equals(sub_type)){

            }
            if ("xmfy".equals(sub_type)){
                try {
                    List<Article> articles = articleService.findAllArticles();
                    message="articles查找成功";
                    status="200";
                    map.put("articles",articles);
                } catch (Exception e) {
                    message="articles查找失败";
                    status="-200";
                    e.printStackTrace();
                }

            }
        }
        map.put("message",message);
        map.put("status",status);

        return map;
    }
    @RequestMapping("articleDesc")
    public Map articleDesc(String id,String uid){
        Map map=new HashMap();
        String message="";
        String status="";
        Article article=new Article();
        article.setId(id);
        try {
            Article article1 = articleService.findArticleById(article);
            message="article查找成功";
            status="200";
            map.put("article",article1);
        } catch (Exception e) {
            message="article查找失败";
            status="-200";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }
    @RequestMapping("chapterOfAlbum")
    public Map chapterOfAlbum(String id,String uid){//albumId userId
        Map map = new HashMap();
        String message="";
        String status="";
        //查找这个专辑下的所有章节 返回这个专辑信息  和这个章节信息
        Album album = new Album();
        Album album1 = album.setId(id);
        //查找这个专辑信息
        try {
            Album one = albumService.findOne(album1);
            //查找 这个专辑下的chapter信息
            Chapter chapter = new Chapter();
            //查找这个album_id下的所有章节
            chapter.setAlbum_id(id);
            List<Chapter> chapters = chapterService.findChaptersByalbumId(chapter);
            status="200";
            message="章节或专辑查找成功";
            map.put("album",one);
            map.put("list",chapters);

        } catch (Exception e) {
            status="-200";
            message="章节或专辑查找失败";
            e.printStackTrace();
        }
        map.put("message",message);
        map.put("status",status);
        return map;
    }
@RequestMapping("showCourses")
    public Map showCources(String uid){
        //把message 和status  封装在了service
    Course course = new Course();
    Map map = courseService.findAll(course);
    return map;
}
    @RequestMapping("addCourse")
    public Map addCourse(String uid,String title){
        //把message 和status  封装在了service
        Course course = new Course();
        course.setUser_id(uid);
        course.setTitle(title);
        Map map = null;
        try {
            map = courseService.addCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        Course course1 = new Course();
        course1.setUser_id(uid);
        Map map1 = courseService.findAll(course1);

        return map1;
    }
    @RequestMapping("deleteCourse")
    public Map deleteCourse(String uid,String id){
        //把message 和status  封装在了service
        Map map = null;
        try {
            map = courseService.deleteCourse(id);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        Course course1 = new Course();
        course1.setUser_id(uid);
        Map map1 = courseService.findAll(course1);
        return map1;
    }
    @RequestMapping("showCounters")
    public Map showCounters(String uid,String id){//id为课程id
        //把message 和status  封装在了service
        Counter counter = new Counter();
        counter.setUser_id(uid);
        counter.setCourse_id(id);
        Map map = counterService.findAllByUid(counter);
        return map;
    }
    @RequestMapping("showCounter")
    public Map showCounter(String uid,String id){
        Map map = counterService.findOneById(id);
        return map;
    }
    @RequestMapping("addCounter")
    public Map addCounter(String uid,String course_id,String title){
        Counter counter = new Counter();
        counter.setUser_id(uid);
        counter.setCourse_id(course_id);
        counter.setTitle(title);
        Map map = null;
        try {
            map = counterService.addCounter(counter);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        //查询所有
        Counter counter1 = new Counter();
        counter1.setUser_id(uid);
        counter1.setCourse_id(course_id);
        Map map1 = counterService.findAllByUid(counter1);
        return map1;
    }
    @RequestMapping("deleteCounter")
    public Map deleteCounter(String uid,String id,String course_id){
        Map map = null;
        try {
            map = counterService.deleteCounter(id);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        //查询所有
        Counter counter1 = new Counter();
        counter1.setUser_id(uid);
        counter1.setCourse_id(course_id);
        Map map1 = counterService.findAllByUid(counter1);
        return map1;
    }
    @RequestMapping("updateCounter")
    public Map updateCounter(String uid,String id,Integer count,String course_id){
        Counter counter = new Counter();
        counter.setId(id);
        counter.setCount(count);
        Map map = null;
        try {
            map = counterService.updateCounter(counter);
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }

        //查询所有
        Counter counter1 = new Counter();
        counter1.setUser_id(uid);
        counter1.setCourse_id(course_id);
        Map map1 = counterService.findAllByUid(counter1);
        return map1;
    }


}
