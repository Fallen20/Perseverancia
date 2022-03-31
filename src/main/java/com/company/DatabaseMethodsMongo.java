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
    void insertPelicula(String title, String sinopsis) {
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
    Stream<Pelicula> generalConsultPelicula() {
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
    Stream<Pelicula> specificSearchPelicula(String title) {
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
    void specificDeletePelicula(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("movies").deleteMany(Filters.eq("title", title));

        }
    }

    @Override
    void deleteTableDataPelicula() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("movies").deleteMany(new Document());
        }

    }

    //-------------------------------------------
    //ACTOR

    @Override
    void insertActor(String name, int age) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            Document doc = new Document();
            doc.append("name_actor", name);
            doc.append("age", age);

            database.getCollection("actors").insertOne(doc);

        }
    }

    @Override
    Stream<Actor> generalConsultActor() {

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            final List<Actor> actorList = new ArrayList<>();

            database.getCollection("actors").find().forEach(d -> {
                Actor actor = new Actor(d.getObjectId("actor_id"),d.getString("name_actor"), d.getInteger("age"));
                //el id ha de ser objectID pero en sql lo usas como string
                actorList.add(actor);

            });

            return actorList.stream();
        }
    }

    @Override
    Stream<Actor> specificSearchActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            final List<Actor> actorList = new ArrayList<>();

            database.getCollection("actors").find(eq("name_actor", name)).forEach(d -> {
                Actor actor = new Actor(d.getObjectId("actor_id"),d.getString("name_actor"), d.getInteger("age"));
                actorList.add(actor);

            });

            return actorList.stream();

        }
    }

    @Override
    void specificDeleteActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("actors").deleteMany(Filters.eq("name_actor", name));

        }

    }

    @Override
    void deleteTableDataActor() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("sampledb");

            database.getCollection("actors").deleteMany(new Document());
        }

    }

}
