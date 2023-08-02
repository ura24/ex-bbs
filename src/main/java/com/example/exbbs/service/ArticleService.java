package com.example.exbbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exbbs.domain.Article;
import com.example.exbbs.repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 記事を全件取得する
     * @return 記事全件
     */
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    /**
     * 記事を一件投稿する
     * @param name 投稿者名
     * @param content 投稿内容
     */
    public void postArticle(Article article) {
        articleRepository.insert(article);
    }
}
