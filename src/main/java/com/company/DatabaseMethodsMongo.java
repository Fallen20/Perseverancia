package main.java.com.company;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseMethodsMongo extends DatabasePadre {

    private final String uri="mongodb://localhost";
    static MongoDatabase database;

    void connect() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");
        }
    }

    @Override
    void insert(String title) {
        connect();
        Document doc = new Document();
        doc.append("title", title);
        database.getCollection("movies").insertOne(doc);
    }

    @Override
    Stream<Pelicula> generalConsult() {
        connect();

        final List<Pelicula> peliculaList = new ArrayList<>();

        database.getCollection("movies").find().forEach(d -> {
            Pelicula pelicula = new Pelicula(d.getInteger("_id"), d.getString("title"), d.getString("synopsis"));
            peliculaList.add(pelicula);

        });

        return peliculaList.stream();
    }

    @Override
    void specificSearch(String title) {
        connect();
       database.getCollection("movies").find(eq("title", title)).forEach(d -> {
            System.out.println(d.toJson());
        });
    }

    @Override
    void specificDelete(String title) {
        connect();
        database.getCollection("movies").deleteMany(Filters.eq("title", title));
    }

    @Override
    void deleteTableData() {
        connect();
        database.getCollection("movies").deleteMany(new Document());
    }

}
