package dambi.proiektuav2.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import dambi.proiektuav2.model.Articles;

@Repository
public interface ArticlesRepositorio {
    List<Articles> getArticles();

    Articles getArticlesById(Integer id);

    List<Articles> getArticlesByType(String type);

    Articles addArticle(Articles articles);

    Articles updateArticle(Integer id, Articles article);

    Articles save(Articles articles);

    Articles deleArticles(Integer id);
}
