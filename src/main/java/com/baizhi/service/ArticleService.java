package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map findAll(Integer page,Integer rows);
    void add(Article article);
    List<Article> findAllArticles();
    Article findArticleById(Article article);
}
