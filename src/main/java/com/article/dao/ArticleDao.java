package com.article.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.article.entity.Article;
import com.core.dao.CoreDao;

@Repository
public interface ArticleDao extends CoreDao<Article, Integer> {
	
    int insert(Article article);
    int update(Article article);
    void delete(Article article);
    Article selectById(Integer articleId);
    List<Article> selectAll();

}
