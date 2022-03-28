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

    @Override
    void insert(String title, String sinopsis) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");



            Document doc = new Document();
            doc.append("title", title);

            if(!sinopsis.equalsIgnoreCase("")){//si no es nulo
                doc.append("synopsis", sinopsis);
            }
            database.getCollection("movies").insertOne(doc);

        }

    }

    @Override
    Stream<Pelicula> generalConsult() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            final List<Pelicula> peliculaList = new ArrayList<>();

            database.getCollection("movies").find().forEach(d -> {
                Pelicula pelicula = new Pelicula(d.getObjectId("_id"),d.getString("title"), d.getString("synopsis"));
                //el id ha de ser objectID pero en sql lo usas como string
                peliculaList.add(pelicula);

            });

            return peliculaList.stream();
        }


    }

    @Override
    Stream<Pelicula> specificSearch(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            final List<Pelicula> peliculaList = new ArrayList<>();

            database.getCollection("movies").find(eq("title", title)).forEach(d -> {
                Pelicula pelicula = new Pelicula(d.getObjectId("_id"),d.getString("title"), d.getString("synopsis"));
                peliculaList.add(pelicula);

            });

            return peliculaList.stream();

        }

    }

    @Override
    void specificDelete(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("movies").deleteMany(Filters.eq("title", title));

        }
    }

    @Override
    void deleteTableData() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("movies").deleteMany(new Document());
        }

    }

}
