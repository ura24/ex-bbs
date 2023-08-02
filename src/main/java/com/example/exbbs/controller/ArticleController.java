package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbbs.domain.Article;
import com.example.exbbs.service.ArticleService;

@Controller
@RequestMapping("/")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    public String index(Model model) {
        List<Article> articleList = articleService.showList();
        model.addAttribute("articleList", articleList);
        return "home";
    }
}
