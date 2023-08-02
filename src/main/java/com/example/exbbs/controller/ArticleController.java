package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;
import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.service.ArticleService;
import com.example.exbbs.service.CommentService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/")
public class ArticleController {
    
    @Autowired
    private ServletContext application;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String index(Model model, ArticleForm articleForm) {
        List<Article> articleList = articleService.showList();
        application.setAttribute("articleList", articleList);
        List<Comment> commentList = commentService.showList();
        application.setAttribute("commentList", commentList);
        return "home";
    }

    @PostMapping("/post")
    public String post(ArticleForm articleForm) {
        articleService.postArticle(articleForm.getName(), articleForm.getContent());
        return "redirect:/";
    }
}
