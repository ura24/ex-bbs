package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;

@Repository
public class ArticleRepository {
        
    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    /**
     * 記事を全件取得する
     * @return 記事全件
     */
    public List<Article> findAll() {
        String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
        return articleList;
    }

    /**
     * 記事を一件投稿する
     * @param name 投稿者名
     * @param content 投稿内容
     */
    public void insert(Article article) {
        String sql = "INSERT INTO articles(name, content) VALUES(:name, :content)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
        template.update(sql, param);
    }
}
