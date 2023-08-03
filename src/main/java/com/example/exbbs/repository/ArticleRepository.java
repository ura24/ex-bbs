package com.example.exbbs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;

@Repository
public class ArticleRepository {
        
    @Autowired
    private NamedParameterJdbcTemplate template;

private final static ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
    List<Article> articleList = new ArrayList<>();

    Article article = null;
    while (rs.next()) {
        if (article == null || article.getId() != rs.getInt("id")) {
            article = new Article();
            article.setId(rs.getInt("id"));
            article.setName(rs.getString("name"));
            article.setContent(rs.getString("content"));
            if (rs.getInt("com_id") != 0) article.setCommentList(new ArrayList<>());
            articleList.add(article);
        }
        if (!rs.wasNull()) {
            Comment comment = new Comment();
            comment.setId(rs.getInt("com_id"));
            comment.setName(rs.getString("com_name"));
            comment.setContent(rs.getString("com_content"));
            comment.setArticleId(rs.getInt("id"));
            article.getCommentList().add(comment);
        }
    }

    return articleList;
};

    /**
     * 記事を全件取得する
     * @return 記事全件
     */
    public List<Article> findAll() {
        String sql ="""
            SELECT
                a.id as id, a.name as name, a.content as content,c.id as com_id,
                c.name as com_name,c.content as com_content, c.article_id as article_id
            FROM 
                articles a
            LEFT JOIN 
                comments c
            ON 
                a.id = c.article_id
            ORDER BY 
                a.id DESC, c.id DESC;
            """; 
        List<Article> articleList = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
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

    public void deleteById(Integer id) {
        String sql = """
            BEGIN; -- トランザクションの開始
            DELETE FROM comments WHERE article_id = :articleId);
            DELETE FROM articles WHERE id = id;
            COMMIT; -- トランザクションのコミット
            """;
        SqlParameterSource param = new MapSqlParameterSource()
                                    .addValue("articleId", id)
                                    .addValue("id", id);
        template.update(sql, param);
    }
}
