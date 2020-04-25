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
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import model.Driver;
import model.Location;
import model.Motor;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

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
/**
 *
 * @author rayhan
 */
public class MongoDbUtils {
    private MongoDatabase database;
    //collection
    MongoCollection<Driver> driverCollection;
    
    public MongoDbUtils() {
    	// Creating Credentials 
		MongoCredential credential; 
		credential = MongoCredential.createCredential("User", "ProjectGoFood", 
				"password".toCharArray()); 
		System.out.println("Connected to the database successfully"); 
        CodecRegistry pojoCodecRegistry = 
                fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                    .automatic(true).build()));
        
        MongoClient mongo = new 
            MongoClient("localhost", MongoClientOptions.builder()
                .codecRegistry(pojoCodecRegistry).build());
        
        database = mongo.getDatabase("ProjectGoFood"); 
        //database = mongo.getDatabase("myDb"); 
        database = database.withCodecRegistry(pojoCodecRegistry);
        System.out.println("Credentials ::"+ credential);
		driverCollection = database.getCollection("driverCollection", Driver.class);
    }
    
  //method untuk insert driver ke collection
    public boolean insertDriver(String fullName, String email, String telpNum, String platNum, String merk, 
    		String street, String city) {
		try {
			Motor motor = new Motor(platNum, merk);
			
			Location location = new Location(street, city);
			String id = new ObjectId().toString();
			location.setKode(id);
			
			Driver driver = new Driver(fullName, email, telpNum, motor, location);	
			
			id = new ObjectId().toString();
			driver.setKode(id);
			
			driverCollection.insertOne(driver);			
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
    
    public ArrayList<Driver> getDriver() throws IOException {		
		ArrayList<Driver> resultList = new ArrayList<>();
		FindIterable<Driver> driverIterable = driverCollection.find();
		
		for (Driver driver : driverIterable) {
			System.out.println(driver);
			resultList.add(driver);
		}		
		return resultList;
	}
}
