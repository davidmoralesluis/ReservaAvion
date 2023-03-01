package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.*;

public class ActualizarReserva {
    public static void main(String[] args) throws SQLException {

        Integer idIda=0;
        Integer idVuelta=0;
        String deEneI="";

        //postgres
        ResultSet rs = null;
        String ddnnii=null;
        String nnoommee=null;
        String reservass=null;
        Integer cash=0;

        //---------------------------------------------------------------------------------
        // Creating a Mongo client
        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");

        // Accessing the database
        MongoDatabase test = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = test.getCollection("reserva");
        System.out.println("Collection sampleCollection selected successfully");

        //---------------------------------------------------------------------------------
        // Establecer Connection con postgreSQL
        Connection conn;
        String driver = "jdbc:postgresql:";
        String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
        String porto = "5432";
        String sid = "postgres";
        String usuario = "dam2a";
        String password = "castelao";
        String url = driver + host+ porto + "/" + sid;
        try {
            conn = DriverManager.getConnection(url,usuario,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //---------------------------------------------------------------------------------





        //---------------------------------------------------------------------------------------------------------------------------------------------------
        FindIterable<Document> documents =  collection.find(Filters.eq("dni","361a"));


        for (Document document : documents) {
            deEneI = document.getString("dni");

            idIda = document.getInteger("idvooida");
            idVuelta = document.getInteger("idvoovolta");
            System.out.println(idIda+" <-> "+idVuelta);


            PreparedStatement pre = conn.prepareStatement("update pasaxeiros set nreservas= nreservas+1 where dni= ?;");
            pre.setString(1,deEneI);
            pre.executeUpdate();


            rs = conn.createStatement().executeQuery("select * from pasaxeiros where dni = '"+deEneI+"';");
            while(rs.next()){

                ddnnii=rs.getString(1);
                nnoommee=rs.getString(2);
                reservass=rs.getString(5);
            }
            System.out.print("\ndni: "+ddnnii+"\nnome: "+nnoommee+"\nreserva: "+reservass+"\n");

            Bson update = Updates.set("confimado",1);     //BasicDBObject modificacion = new BasicDBObject().append("$inc",new BasicDBObject("confirmado",1));

            collection.updateOne(document,update);
            //Inserting document into the collection
            System.out.println("Document inserted successfully");
        }



        mongo.close();
    }
}
