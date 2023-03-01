package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;


public class Main {
    public static void main(String[] args) {
        // Creating a Mongo client
        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");

        // Accessing the database
        MongoDatabase test = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = test.getCollection("reserva");
        System.out.println("Collection sampleCollection selected successfully");
        BasicDBObject con = new BasicDBObject("dni", "361a");
        collection.find(Filters.eq("dni","361a"));
        FindIterable<Document> documents =  collection.find(Filters.eq("dni","361a"));
        for (Document document : documents) {
            Integer idvooida = document.getInteger("idvooida");
            System.out.println(idvooida);
        }



/*
        try (MongoCursor<Document> cursor = collection.find().cursor()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());  //System.out.println(cursor.next().toJson());
            }
        }

 */

mongo.close();
    }
}