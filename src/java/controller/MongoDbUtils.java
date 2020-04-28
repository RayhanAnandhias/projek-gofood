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
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import model.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.regex.Pattern;

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
import com.mongodb.client.ListIndexesIterable;
/**
 *
 * @author rayhan
 */
public class MongoDbUtils {
    private MongoDatabase database;
    //collection
    MongoCollection<Driver> driverCollection;
    MongoCollection<Restaurant> restaurantCollection;
    MongoCollection<Food> foodCollection;
    MongoCollection<User> userCollection;
    MongoCollection<Pesanan> pesananCollection;
    
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
		restaurantCollection = database.getCollection("restaurantCollection", Restaurant.class);
		foodCollection = database.getCollection("foodCollection", Food.class);
		userCollection = database.getCollection("userCollection", User.class);
		pesananCollection = database.getCollection("pesananCollection", Pesanan.class);
    }
    
  //method untuk insert driver ke collections
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
    
    public ArrayList<Driver> getDriverByCategory(String category, String value) throws IOException {		
		ArrayList<Driver> resultList = new ArrayList<>();
		FindIterable<Driver> driverIterable = null;
		
		switch(category) {
			case "kode":{
				driverIterable = driverCollection.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "full_name":{
				driverIterable = driverCollection.find(regex("full_name", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "email":{
				driverIterable = driverCollection.find(regex("email", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "telp_no":{
				driverIterable = driverCollection.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "no_plat":{
				driverIterable = driverCollection.find(regex("motor.no_Plat", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "merk":{
				driverIterable = driverCollection.find(regex("motor.merk", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "street":{
				driverIterable = driverCollection.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "city":{
				driverIterable = driverCollection.find(regex("location.city", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
		}
		
		for(Driver temp : driverIterable) {
			resultList.add(temp);
			System.out.println(temp);
		}
		
		return resultList;
	}
    
    public boolean updateDriver(String row, String fullName, String email, String telpNum, String platNum, String merk, String locationKode,
    		String street, String city) {		
		try {	
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("full_name", fullName));
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("email", email));
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("telp_no", telpNum));
			Motor motor = new Motor(platNum, merk);
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("motor", motor));
			Location location = new Location(street, city);
			location.setKode(locationKode);
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("location", location));
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
    public boolean deleteDriver(String row) {
		DeleteResult del = driverCollection.deleteOne(eq("kode", row));
		System.out.println("del on Driver = " + del.getDeletedCount());
		return true;
	}
    
    public boolean insertRestaurant(String fullName, String street, String city, String telpNum, String detail, String listFoodName[], 
    		String listFoodPrice[], String listFoodQuant[], String listFoodDetail[]) {
		try {
			List<String> foodId = new ArrayList<>();
			
			//set var location and set the value
			Location location = new Location(street, city);
			String id = new ObjectId().toString();
			location.setKode(id);
			
			//iteator for inserting food to the collection
			for(int i = 0; i < listFoodName.length; i++) {
				Food food = new Food(listFoodName[i], Integer.parseInt(listFoodPrice[i]), Integer.parseInt(listFoodQuant[i]),
						listFoodDetail[i]);
				
				id = new ObjectId().toString();
				foodId.add(id);
				food.setKode(id);
				foodCollection.insertOne(food);
			}
			
			Restaurant restaurant = new Restaurant(fullName, location, telpNum, detail, foodId);
			id = new ObjectId().toString();
			restaurant.setKode(id);
			System.out.println(restaurant.getListFoodId().size());
			restaurantCollection.insertOne(restaurant);			
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
    
    public ArrayList<Restaurant> getRestaurant() throws IOException {		
		ArrayList<Restaurant> resultList = new ArrayList<>();
		FindIterable<Restaurant> restaurantIterable = restaurantCollection.find();
		
		for (Restaurant restaurant : restaurantIterable) {
			System.out.println(restaurant);
			resultList.add(restaurant);
		}		
		return resultList;
	}
    
    public ArrayList<Restaurant> getRestaurantByCategory(String category, String value) throws IOException {		
		ArrayList<Restaurant> resultList = new ArrayList<>();
		FindIterable<Restaurant> restaurantIterable = null;
		
		switch(category) {
			case "kode":{
				restaurantIterable = restaurantCollection.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "name":{
				restaurantIterable = restaurantCollection.find(regex("name", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "telp_no":{
				restaurantIterable = restaurantCollection.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "detail":{
				restaurantIterable = restaurantCollection.find(regex("detail", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "street":{
				restaurantIterable = restaurantCollection.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "city":{
				restaurantIterable = restaurantCollection.find(regex("location.city", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
		}
		
		for(Restaurant temp : restaurantIterable) {
			resultList.add(temp);
			System.out.println(temp);
		}
		
		return resultList;
	}
    
    public boolean updateRestaurant(String row, String name, String locationKode, String street, String city, String telpNum, String detail) {		
		try {	
			restaurantCollection.updateOne(Filters.eq("kode", row), Updates.set("name", name));
			restaurantCollection.updateOne(Filters.eq("kode", row), Updates.set("telp_no", telpNum));
			restaurantCollection.updateOne(Filters.eq("kode", row), Updates.set("detail", detail));
			
			Location location = new Location(street, city);
			location.setKode(locationKode);
			restaurantCollection.updateOne(Filters.eq("kode", row), Updates.set("location", location));
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
    public boolean deleteRestaurant(String row) {
    	FindIterable<Restaurant> restaurantIterable = restaurantCollection.find();
    	Restaurant restaurant = null;
		for (Restaurant temp : restaurantIterable) {
			if(temp.getKode().equals(row))
				restaurant = temp;
		}
		int counter = 0;
		FindIterable<Food> foodIterable = foodCollection.find();
		List<String> idFood = restaurant.getListFoodId();
		for(String id : idFood) {
			for (Food temp : foodIterable) {
				if(temp.getKode().equals(id)) {
					counter++;
					foodCollection.deleteOne(eq("kode", id));
				}
					
			}
		}
    	
		DeleteResult del = restaurantCollection.deleteOne(eq("kode", row));
		System.out.println("del on Restaurant = " + del.getDeletedCount());
		System.out.println("del on Food = " + counter);
		return true;
	}
    
    public ArrayList<Food> getFoodOnRestaurant(String row) throws IOException {		
    	ArrayList<Food> resultList = new ArrayList<>();
    	List<String> foodId = new ArrayList<>();
		FindIterable<Restaurant> restaurantIterable = restaurantCollection.find();
		
		for (Restaurant restaurant : restaurantIterable) {
			if(restaurant.getKode().equals(row)) {
				foodId = restaurant.getListFoodId();
				break;
			}
		}
		FindIterable<Food> foodIterable = foodCollection.find();
		for(String id : foodId) {
			for(Food food : foodIterable) {
				if(food.getKode().equals(id)) {
					resultList.add(food);
				}
			}
		}
		return resultList;
	}
    
    public boolean updateFoodOnRestaurant(String row, String name, int price, int quantity, String detail) {		
		try {	
			foodCollection.updateOne(Filters.eq("kode", row), Updates.set("name", name));
			foodCollection.updateOne(Filters.eq("kode", row), Updates.set("price", price));
			foodCollection.updateOne(Filters.eq("kode", row), Updates.set("quantity", quantity));
			foodCollection.updateOne(Filters.eq("kode", row), Updates.set("detail", detail));
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
    public boolean deleteFoodOnRestaurant(String restRow, String foodRow) {
    	FindIterable<Restaurant> restaurantIterable = restaurantCollection.find();
    	Restaurant restaurant = null;
		for (Restaurant temp : restaurantIterable) {
			if(temp.getKode().equals(restRow))
				restaurant = temp;
		}
		List<String> idFood = restaurant.getListFoodId();
		if(idFood.size() > 1) {
			DeleteResult del = foodCollection.deleteOne(eq("kode", foodRow));
			System.out.println(idFood.size());
			idFood.remove(foodRow);
	    	System.out.println(idFood.size());
	    	restaurantCollection.updateOne(Filters.eq("kode", restRow), Updates.set("listFoodId", idFood));
			System.out.println("del on Food = " + del.getDeletedCount());
			return true;
		}
		return false;
	}
    
    public ArrayList<User> getUser() throws IOException {		
		ArrayList<User> resultList = new ArrayList<>();
		FindIterable<User> userIterable = userCollection.find();
		
		for (User user : userIterable) {
			System.out.println(user);
			resultList.add(user);
		}		
		return resultList;
	}
    
    public ArrayList<User> getUserByCategory(String category, String value) throws IOException {		
		ArrayList<User> resultList = new ArrayList<>();
		FindIterable<User> userIterable = null;
		
		switch(category) {
			case "kode":{
				userIterable = userCollection.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "full_name":{
				userIterable = userCollection.find(regex("full_name", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "email":{
				userIterable = userCollection.find(regex("email", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "telp_no":{
				userIterable = userCollection.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "street":{
				userIterable = userCollection.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "city":{
				userIterable = userCollection.find(regex("location.city", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			
		}
		
		for(User temp : userIterable) {
			resultList.add(temp);
			System.out.println(temp);
		}
		
		return resultList;
	}
    
    public boolean topUpGopay(String row, int saldo) {		
		try {
			FindIterable<User> userIterable = userCollection.find();
			int saldoNow = saldo;
			for (User user : userIterable) {
				if(user.getKode().equals(row)) {
					saldoNow+=user.getSaldo_gopay();
				}
			}
			driverCollection.updateOne(Filters.eq("kode", row), Updates.set("saldo_gopay", saldoNow));
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
    public boolean deleteUser(String row) {
		DeleteResult del = userCollection.deleteOne(eq("kode", row));
		System.out.println("del on User = " + del.getDeletedCount());
		return true;
	}
    
    public ArrayList<Pesanan> getPesanan() throws IOException {		
		ArrayList<Pesanan> resultList = new ArrayList<>();
		FindIterable<Pesanan> pesananIterable = pesananCollection.find();
		
		for (Pesanan pesanan : pesananIterable) {
			System.out.println(pesanan);
			resultList.add(pesanan);
		}		
		return resultList;
	}
    /*
    public ArrayList<Pesanan> getPesananByCategory(String category, String value) throws IOException {		
		ArrayList<Pesanan> resultList = new ArrayList<>();
		FindIterable<Pesanan> pesananIterable = null;
		
		switch(category) {
			case "user_id":{
				pesananIterable = userCollection.find(regex("full_name", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "restaurant_id":{
				pesananIterable = userCollection.find(regex("email", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "driver_id":{
				pesananIterable = userCollection.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "street":{
				pesananIterable = userCollection.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			case "city":{
				pesananIterable = userCollection.find(regex("location.city", ".*" + Pattern.quote(value) + ".*"));
				break;
			}
			
		}
		
		for(User temp : userIterable) {
			resultList.add(temp);
			System.out.println(temp);
		}
		
		return resultList;
	}*/
}
