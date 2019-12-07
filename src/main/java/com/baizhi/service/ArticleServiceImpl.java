package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAll(Integer page, Integer rows) {
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds((page - 1) * rows, rows));
        Map map=new HashMap();
        Integer records=articleDao.selectCount(new Article());
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",articles);
        return map;
    }

    @Override
    public void add(Article article){
        String id = UUID.randomUUID().toString();
        long time = new Date().getTime();
        java.sql.Date date = new java.sql.Date(time);
        article.setId(id).setCreate_date(date).setPublish_date(date);
        articleDao.insertSelective(article);
    }

    @Override
    public List<Article> findAllArticles() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }

    @Override
    public Article findArticleById(Article article) {
        Article one = articleDao.selectOne(article);
        return one;
    }
}
