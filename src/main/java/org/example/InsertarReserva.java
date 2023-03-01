package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class InsertarReserva {
    public static void main(String[] args) {
        // Creating a Mongo client
        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");

        // Accessing the database
        MongoDatabase test = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = test.getCollection("reserva");
        System.out.println("Collection sampleCollection selected successfully");

        //preparar documento para insertar
        Document document = new Document("_id", 3)
                .append("dni", "361a")
                .append("idvooida", 5)
                .append("idvoovolta", 6)
                .append("prezoreserva", 0)
                .append("confirmado", 0);

        //Inserting document into the collection
        collection.insertOne(document);
        System.out.println("Document inserted successfully");
    }
}
