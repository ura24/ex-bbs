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

    public List<Article> showList() {
        return articleRepository.findAll();
    }
}
