/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.Block;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import com.mongodb.ServerAddress;
import org.bson.Document;
import java.util.Arrays;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.io.IOException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import model.User;
import org.bson.types.ObjectId;
/**
 *
 * @author rayhan
 */
public class MongoDbUtils {
    private MongoDatabase database;
    private MongoCollection<User> users;
    //collection
    
    public MongoDbUtils() {
        CodecRegistry pojoCodecRegistry = 
                fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                    .automatic(true).build()));
        
        MongoClient mongo = new 
            MongoClient("localhost", MongoClientOptions.builder()
                .codecRegistry(pojoCodecRegistry).build());
        
        database = mongo.getDatabase("myDb"); 
        database = database.withCodecRegistry(pojoCodecRegistry);
        users = database.getCollection("users", User.class);
    }
    
    public boolean insertDataUser(String name, String pwd, String email, String noTelp, int saldo) throws IOException {
        String id = new ObjectId().toString();
        User user;
        try {	
                user = users.find(eq("email", email)).first();
                if(user == null) {
                    user = new User(name, email, pwd, noTelp, null, saldo);	
                    user.setKode(id);
                    users.insertOne(user);			
                    System.out.println("new user inserted");
                } else {
                    return false;
                }
        } catch (Exception e) {
                e.printStackTrace();
                return false;
        }		
        return true;
    }

    boolean validateUser(String email, String pwd) {
        User user;
        try {
            user = users.find(and(eq("email", email), eq("password", pwd))).first();
            if(user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
