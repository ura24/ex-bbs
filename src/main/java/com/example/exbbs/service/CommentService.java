package com.example.exbbs.service;

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

    public void postComment(Comment comment) {
        commentRepository.insert(comment);
    }
}
