package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    /**
     * 記事を全件取得する
     * @return 記事全件
     */
    public List<Comment> findAll() {
        String sql = "SELECT id, name, content, article_id FROM comments ORDER BY id DESC";
        List<Comment> commentList = template.query(sql, COMMENT_ROW_MAPPER);
        return commentList;
    }
}
