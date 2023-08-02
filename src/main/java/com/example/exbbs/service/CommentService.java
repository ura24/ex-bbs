package com.example.exbbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exbbs.domain.Comment;
import com.example.exbbs.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 記事を全件取得する
     * @return 記事全件
     */
    public List<Comment> showList() {
        return commentRepository.findAll();
    }
}
