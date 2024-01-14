package dambi.proiektuav2.model;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import dambi.proiektuav2.repositorio.ArticlesRepositorio;
import jakarta.annotation.PostConstruct;

@Repository
public class MongoDBArticlesRepository implements ArticlesRepositorio {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<Articles> articlesCollection;

    @PostConstruct
    void init() {
        articlesCollection = client.getDatabase("prueba").getCollection("prueba2", Articles.class);
    }

    // Hemengo metodo honek colekzio guztia hartzen du eta 60.000 registro direnez
    // ordenatu egiten du eta limite bat ipini 100 bakarrik ikusteko
    @Override
    public List<Articles> getArticles() {
        return articlesCollection.find().sort(Sorts.descending("pub")).limit(100).into(new ArrayList<>());
    }

    // Hemengoko honetan id modura billatzen dugu
    @Override
    public Articles getArticlesById(Integer id) {
        return articlesCollection.find(eq("_id", id)).first();
    }

    @Override
    public List<Articles> getArticlesByType(String type) {
        List<Articles> articlesList = new ArrayList<>();

        articlesCollection.find(eq("type", type)).into(articlesList);

        return articlesList;
    }

    @Override
    public Articles addArticle(Articles article) {
        articlesCollection.insertOne(article);

        return article;
    }


     @Override
    public Articles updateArticle(Integer id, Articles article) {
        articlesCollection.replaceOne(eq("id", article.getId()), article);
        return article;
    }

    @Override
    public Articles save(Articles articles){
        articlesCollection.insertOne(articles);
        return articles;
    }

@Override
public Articles deleArticles(Integer id) {
    Articles deletedArticle = articlesCollection.findOneAndDelete(eq("id", id));
    
    if (deletedArticle != null) {
        return deletedArticle;
    } else {
        // Aquí puedes manejar el caso en el que no se encuentra el artículo
        throw new EmptyResultDataAccessException("Article not found with id: " + id, 1);
    }
}

}
