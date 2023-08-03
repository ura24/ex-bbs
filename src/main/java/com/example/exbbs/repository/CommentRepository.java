package com.example.exbbs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * コメントを一件投稿する
     * @param name コメント者名
     * @param content コメント内容
     */
    public void insert(Comment comment) {
        String sql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId)";
        SqlParameterSource param = new MapSqlParameterSource()
                                    .addValue("name", comment.getName())
                                    .addValue("content", comment.getContent())
                                    .addValue("articleId", comment.getArticleId());
        template.update(sql, param);
    }
}
