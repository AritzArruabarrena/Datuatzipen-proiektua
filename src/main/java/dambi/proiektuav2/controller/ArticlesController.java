package dambi.proiektuav2.controller;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoWriteException;

import dambi.proiektuav2.model.Articles;
import dambi.proiektuav2.repositorio.ArticlesRepositorio;

@RestController
@RequestMapping("api/articles")
public class ArticlesController {

    @Autowired
    private ArticlesRepositorio articlesRepositorio;

    @GetMapping
    public List<Articles> getArticles() {
        return articlesRepositorio.getArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticlesById(@PathVariable Integer id) {
            Articles articles = articlesRepositorio.getArticlesById(id);

        if (articles != null) {
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Articles>> getArticlesByType(@PathVariable String type) {
        List<Articles> articlesList = articlesRepositorio.getArticlesByType(type);

        if (!articlesList.isEmpty()) {
            return ResponseEntity.ok(articlesList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Articles> addArticle(@RequestBody Articles addArticles) {
        Articles addedArticle = articlesRepositorio.addArticle(addArticles);

        if (addedArticle != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedArticle);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
public ResponseEntity<?> updateArticle(@PathVariable Integer id, @RequestBody Articles updatedArticle) {
    try {
        Articles existingArticle = articlesRepositorio.getArticlesById(id);

        if (existingArticle != null) {
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setFeed(updatedArticle.getFeed());
            existingArticle.setType(updatedArticle.getType());

            Articles savedArticle = articlesRepositorio.save(existingArticle);
            return ResponseEntity.ok(savedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (MongoWriteException e) {
        // Manejar la excepción específica de escritura de MongoDB
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el artículo: " + e.getMessage());
    }

   
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        try {
            articlesRepositorio.deleArticles(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            // Si no se encuentra el artículo, devuelve un 404 con un objeto Articles nulo
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
    }
    





    }
}




    

