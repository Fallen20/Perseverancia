package main.java.com.company;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseMethods_Mongo extends DatabasePadre {

    private final String uri="mongodb://localhost";
    @Override
    void insert(String title) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongourl"))) {
            MongoDatabase database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");

            // INSERT
            Document doc = new Document();
            doc.append("title", title);
            collection.insertOne(doc);

        }
    }

    @Override
    List<Pelicula> generalConsult() {

        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongourl"))) {
            MongoDatabase database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");


//            FindIterable<Document> iterable = collection.find();
//            MongoCursor<Document> cursor = iterable.iterator();
//
//            while(cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }

            List<Document> peliculaList = new ArrayList<>();
            peliculaList=collection.find().into(new ArrayList<>());

            Pelicula peli;
            peliculaList.stream()
                    .forEach(v-> {
                            System.out.println(v.toJson());
                        }
                    );


        }
        return null;
    }

    @Override
    void specificSearch(String title) {

        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongourl"))) {
            MongoDatabase database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");

            //QUERY
            FindIterable<Document> iterable = collection.find(eq("title", title));
            MongoCursor<Document> cursor = iterable.iterator();

            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }

    }

    @Override
    void specificDelete(String title) {

        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongourl"))) {
            MongoDatabase database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");

            //borras todos los que encuentras con ese nombre
          collection.deleteMany(Filters.eq("title", title));
        }

    }

    @Override
    void deleteTableData() {

        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongourl"))) {
            MongoDatabase database = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = database.getCollection("movies");

            //borras todos
            collection.deleteMany(new Document());


        }

    }

}
