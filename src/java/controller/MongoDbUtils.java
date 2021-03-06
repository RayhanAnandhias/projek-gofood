/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import java.util.ArrayList;
import java.io.IOException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import java.util.regex.Pattern;
import model.*;
import astar.*;
import com.mongodb.MongoCredential;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Driver;
import model.Motor;
import model.Pesanan;
import org.bson.types.ObjectId;
/**
 *
 * @author rayhan
 * @author Ananda Bayu
 */
public class MongoDbUtils {
    private MongoDatabase database;
    private MongoCollection<User> users;
    private MongoCollection<User> usersVersion;
    private MongoCollection<Restaurant> restaurants;
    private MongoCollection<Food> foods;
    private MongoCollection<Location> locations;
    private MongoCollection<Driver> drivers;
    private MongoCollection<Pesanan> pesanan;
    private LocationUtils locUtils;
    //collection
    
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
        database = database.withCodecRegistry(pojoCodecRegistry);
        users = database.getCollection("users", User.class);
        usersVersion = database.getCollection("usersVersion", User.class);
        restaurants = database.getCollection("restaurants", Restaurant.class);
        foods = database.getCollection("foods", Food.class);
        locations = database.getCollection("locations", Location.class);
        drivers = database.getCollection("drivers", Driver.class);
        pesanan = database.getCollection("pesanan", Pesanan.class);
        locUtils = new LocationUtils(this, locations);
    }
    
    public boolean insertDataUser(String name, String pwd, String email, String noTelp, int saldo) {
        String id = new ObjectId().toString();
        User user;
        Location loc;
        Random rn = new Random();
        try {	
                int max = 100;
                int min = 1;
                loc = locations.find(eq("kode", Integer.toString(rn.nextInt((max-min) + 1) + min))).first();
                user = users.find(eq("email", email)).first();
                if(user == null) {
                    user = new User(name, email, pwd, noTelp, loc, saldo);	
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

    public User validateUser(String email, String pwd) {
        User user;
        try {
            user = users.find(and(eq("email", email), eq("password", pwd))).first();
            if(user != null) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateDataUser(String row, String name, String pwd, String email, String noTelp) {
        User user;
        try {
            //before update save last version
            user = users.find(eq("kode", row)).first();
            usersVersion.insertOne(user);

            //update data
            UpdateResult updateResult = 
                    users.updateOne(eq("kode", row), combine(
                            set("full_name", name), 
                            set("email", email), 
                            set("password", pwd), 
                            set("telp_no", noTelp)));
            System.out.println("update = " + updateResult.getModifiedCount());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<Restaurant> searchRestaurant(String name) {
        ArrayList<Restaurant> resto = new ArrayList<Restaurant>();
        try {
            FindIterable<Restaurant> restoIterable = restaurants.find(regex("name", ".*" + Pattern.quote(name) + ".*"));
            for(Restaurant restaurant : restoIterable) {
                resto.add(restaurant);
            }
            return resto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Food> searchFood(String name) {
        ArrayList<Food> food = new ArrayList<Food>();
        try {
            FindIterable<Food> foodIterable = foods.find(regex("name", ".*" + Pattern.quote(name) + ".*"));
            for(Food fd : foodIterable) {
                food.add(fd);
            }
            return food;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Set<Location> getAllLocData() {
        Set<Location> loc = new LinkedHashSet<>();
        try {
            FindIterable<Location> locIterable = locations.find();
            for (Location location : locIterable) {
                loc.add(location);
            }
            return loc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean createPesanan(String userId, String restoId, String[] foodKode, String[] foodPrice, String[] foodQuantity, String[] foodNote, boolean method) {
        Pesanan orders;
        String idPesan = new ObjectId().toString();
        Driver driver;
        User user;
        Restaurant resto;
        int ongkir;
        double jarak;
        int hargaFood = 0;
        List<ItemPesanan> lsPesanan = new ArrayList<>();
        HaversineScorer scorer = new HaversineScorer();
        try {
            System.out.println("MASUK CREATE PESANAN");
            user = users.find(eq("kode", userId)).first();
            System.out.println(user.getFull_name());
            resto = restaurants.find(eq("kode", restoId)).first();
            System.out.println(resto.getName());
            for (int i = 0; i < foodKode.length; i++) {
                lsPesanan.add(new ItemPesanan(foodKode[i], Integer.parseInt(foodQuantity[i]), foodNote[i]));
                hargaFood += Integer.parseInt(foodPrice[i])*Integer.parseInt(foodQuantity[i]);
            }
            System.out.println(lsPesanan + " " + hargaFood);
            driver = chooseDriver(restoId);
            System.out.println(driver);
            jarak = scorer.computeCost(user.getLocation(), resto.getLocation());
            System.out.println(jarak);
            ongkir = (int)jarak*2000;
            System.out.println(ongkir);
            int totalCost = ongkir + hargaFood;
            if((method) && (user.getSaldo_gopay() >= totalCost)) {
                int curSaldo = user.getSaldo_gopay() - totalCost;
                UpdateResult updateResult =
                        users.updateOne(eq("kode", user.getKode()), combine(
                                set("saldo_gopay", curSaldo)));
            } else {
                //saldo kurang, beralih ke pembayaran cash
                method = false;
            }
            orders = new Pesanan(userId, restoId, driver.getKode(), lsPesanan, method, ongkir, ongkir + hargaFood);
            orders.setKode(idPesan);
            pesanan.insertOne(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Driver chooseDriver(String restoId) {
        Driver idDriver = null;
        Restaurant restoLocation;
        List<Driver> lsDriver;
        HaversineScorer cek = new HaversineScorer();
        try {
            System.out.println("MASUK CHOOSE DRIVER");
            restoLocation = restaurants.find(eq("kode", restoId)).first();
            System.out.println(restoLocation);
            lsDriver = getAllDriver();
            Collections.sort(lsDriver, new Comparator<Driver>() {
                @Override
                public int compare(Driver t, Driver t1) {
                    Double tScore = cek.computeCost(t.getLocation(), restoLocation.getLocation());
                    Double t1Score = cek.computeCost(t1.getLocation(), restoLocation.getLocation());
                    if(tScore > t1Score) {
                        return 1;
                    } else if(tScore < t1Score) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            for (Driver driver : lsDriver) {
                idDriver = driver;
                break;
            }
            return idDriver;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Driver> getAllDriver() {
        List<Driver> lsDriver = new ArrayList<Driver>();
        try {
            FindIterable<Driver> drvIterable = drivers.find();
            for (Driver driver : drvIterable) {
                lsDriver.add(driver);
            }
            return lsDriver;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertLocData() {
        return locUtils.insertLocData();
    }

    //Operator Feature
    //method untuk insert driver ke collections
    public boolean insertDriver(String fullName, String email, String telpNum, String platNum, String merk) {
        try {
            Motor motor = new Motor(platNum, merk);
            Random random = new Random();
            int max = 200;
            int min = 101;
            Location location = locations.find(eq("kode", Integer.toString(random.nextInt((max-min) + 1) + min))).first();

            Driver driver = new Driver(fullName, email, telpNum, motor, location);

            String id = new ObjectId().toString();
            driver.setKode(id);

            drivers.insertOne(driver);
            System.out.println("data inserted");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Driver> getDriver() throws IOException {
        ArrayList<Driver> resultList = new ArrayList<>();
        FindIterable<Driver> driverIterable = drivers.find();

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
                driverIterable = drivers.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "full_name":{
                driverIterable = drivers.find(regex("full_name", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "email":{
                driverIterable = drivers.find(regex("email", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "telp_no":{
                driverIterable = drivers.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "no_plat":{
                driverIterable = drivers.find(regex("motor.no_Plat", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "merk":{
                driverIterable = drivers.find(regex("motor.merk", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "street":{
                driverIterable = drivers.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
        }

        for(Driver temp : driverIterable) {
            resultList.add(temp);
            System.out.println(temp);
        }

        return resultList;
    }

    public boolean updateDriver(String row, String fullName, String email, String telpNum, String platNum, String merk) {
        try {
            drivers.updateOne(Filters.eq("kode", row), Updates.set("full_name", fullName));
            drivers.updateOne(Filters.eq("kode", row), Updates.set("email", email));
            drivers.updateOne(Filters.eq("kode", row), Updates.set("telp_no", telpNum));
            Motor motor = new Motor(platNum, merk);
            drivers.updateOne(Filters.eq("kode", row), Updates.set("motor", motor));
            System.out.println("data updated");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDriver(String row) {
        DeleteResult del = drivers.deleteOne(eq("kode", row));
        System.out.println("del on Driver = " + del.getDeletedCount());
        return true;
    }

    //    public boolean insertRestaurant(String fullName, String telpNum, String detail, String listFoodName[], String listFoodPrice[], String listFoodQuant[],
//    		String listFoodDetail[]) {
    public boolean insertRestaurant(String fullName, String telpNum, String detail, String listFoodName[], String listFoodPrice[],
                                    String listFoodDetail[]) {
        try {
            List<Food> foodList = new ArrayList<>();

            //set var location and set the value
            Random random = new Random();
            int max = 300;
            int min = 201;
            Location location = locations.find(eq("kode", Integer.toString(random.nextInt((max-min) + 1) + min))).first();
            String idFood = null;
            String idResto = null;

            Restaurant restaurant = new Restaurant(fullName, location, telpNum, detail, null);
            idResto = new ObjectId().toString();
            restaurant.setKode(idResto);
            restaurants.insertOne(restaurant);

            //iteator for inserting food to the collection
            for(int i = 0; i < listFoodName.length; i++) {
//				Food food = new Food(listFoodName[i], Integer.parseInt(listFoodPrice[i]), Integer.parseInt(listFoodQuant[i]),
//						listFoodDetail[i]);
                Food food = new Food(listFoodName[i], Integer.parseInt(listFoodPrice[i]),
                        listFoodDetail[i], idResto);
                Food temp = new Food(listFoodName[i], Integer.parseInt(listFoodPrice[i]),
                        listFoodDetail[i], null);

                
                idFood = new ObjectId().toString();
                food.setKode(idFood);
                foodList.add(temp);
                foods.insertOne(food);
            }

            UpdateResult updateResult =
                    restaurants.updateOne(eq("kode", idResto), set("foods", foodList));

            System.out.println("data inserted");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Restaurant> getRestaurant() throws IOException {
        ArrayList<Restaurant> resultList = new ArrayList<>();
        FindIterable<Restaurant> restaurantIterable = restaurants.find();

        for (Restaurant restaurant : restaurantIterable) {
            System.out.println(restaurant);
            resultList.add(restaurant);
        }
        return resultList;
    }

    public ArrayList<Food> getFood() throws IOException {
        ArrayList<Food> resultList = new ArrayList<>();
        FindIterable<Food> foodIterable = foods.find();

        for (Food food : foodIterable) {
            System.out.println(food);
            resultList.add(food);
        }
        return resultList;
    }

    public ArrayList<Restaurant> getRestaurantByCategory(String category, String value) throws IOException {
        ArrayList<Restaurant> resultList = new ArrayList<>();
        FindIterable<Restaurant> restaurantIterable = null;

        switch(category) {
            case "kode":{
                restaurantIterable = restaurants.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "name":{
                restaurantIterable = restaurants.find(regex("name", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "telp_no":{
                restaurantIterable = restaurants.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "detail":{
                restaurantIterable = restaurants.find(regex("detail", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "street":{
                restaurantIterable = restaurants.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "food_name":{
                restaurantIterable = restaurants.find(regex("foods.name", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
        }

        for(Restaurant temp : restaurantIterable) {
            resultList.add(temp);
            System.out.println(temp);
        }

        return resultList;
    }

    public ArrayList<Food> getFoodByCategory(String category, String value) throws IOException {
        ArrayList<Food> resultList = new ArrayList<>();
        FindIterable<Food> foodIterable = null;

        switch(category) {
            case "name":{
                foodIterable = foods.find(regex("name", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "detail":{
                foodIterable = foods.find(regex("detail", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
        }

        for(Food temp : foodIterable) {
            resultList.add(temp);
            System.out.println(temp);
        }

        return resultList;
    }

    public boolean updateRestaurant(String row, String name, String telpNum, String detail) {
        try {
            restaurants.updateOne(Filters.eq("kode", row), Updates.set("name", name));
            restaurants.updateOne(Filters.eq("kode", row), Updates.set("telp_no", telpNum));
            restaurants.updateOne(Filters.eq("kode", row), Updates.set("detail", detail));

            System.out.println("data updated");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteRestaurant(String row) {
        FindIterable<Restaurant> restaurantIterable = restaurants.find();
        Restaurant restaurant = null;
        for (Restaurant temp : restaurantIterable) {
            if(temp.getKode().equals(row))
                restaurant = temp;
        }
        int counter = 0;
        FindIterable<Food> foodIterable = foods.find();
        List<Food> food = restaurant.getFoods();
        for(Food id : food) {
            for (Food temp : foodIterable) {
                if(temp.getKode().equals(id.getKode())) {
                    counter++;
                    foods.deleteOne(eq("kode", id.getKode()));
                }

            }
        }

        DeleteResult del = restaurants.deleteOne(eq("kode", row));
        System.out.println("del on Restaurant = " + del.getDeletedCount());
        System.out.println("del on Food = " + counter);
        return true;
    }

    public List<Food> getFoodOnRestaurant(String row) throws IOException {

        List<Food> food = new ArrayList<>();
        //FindIterable<Restaurant> restaurantIterable = restaurants.find();

//		for (Restaurant restaurant : restaurantIterable) {
//			if(restaurant.getKode().equals(row)) {
//				food = restaurant.getFoods();
//				break;
//			}
//		}
//                System.out.println(food);
//		FindIterable<Food> foodIterable = foods.find();
//		for(Food id : food) {
//			for(Food temp : foodIterable) {
//				if(temp.getKode().equals(id.getKode())) {
//					resultList.add(temp);
//				}
//			}
//		}
        FindIterable<Food> foodIterable = foods.find(eq("idResto", row));
        for (Food f : foodIterable) {
            food.add(f);
        }
        return food;
    }

    public ArrayList<Restaurant> getRestaurantOnFood(String rowFood) throws IOException {
        ArrayList<Restaurant> resultList = new ArrayList<Restaurant>();
        FindIterable<Restaurant> restoIterable = restaurants.find(in("listFoodId", rowFood));
        for (Restaurant restaurant : restoIterable) {
            resultList.add(restaurant);
        }
        return resultList;
    }

    public boolean updateFoodOnRestaurant(String row, String name, int price, String detail, String idRest) {
        try {
            foods.updateOne(Filters.eq("kode", row), Updates.set("name", name));
            foods.updateOne(Filters.eq("kode", row), Updates.set("price", price));
            foods.updateOne(Filters.eq("kode", row), Updates.set("detail", detail));
            System.out.println("data updated");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFoodOnRestaurant(String restRow, String foodRow) {
        FindIterable<Restaurant> restaurantIterable = restaurants.find();
        Restaurant restaurant = null;
        for (Restaurant temp : restaurantIterable) {
            if(temp.getKode().equals(restRow))
                restaurant = temp;
        }
        List<Food> idFood = restaurant.getFoods();
        if(idFood.size() > 1) {
        	FindIterable<Food> foodIterable = foods.find();
            Food food = null;
            for (Food temp : foodIterable) {
                if(temp.getKode().equals(foodRow))
                	food = temp;
            }
            System.out.println(food.getName());
        	for (Food temp : idFood) {
        		System.out.println(temp.getName());
                if(temp.getName() == food.getName()) {
                	System.out.println("in");
                    idFood.remove(temp);
                    System.out.println("after");
                    break;
                }
            }
            DeleteResult del = foods.deleteOne(eq("kode", foodRow));
            System.out.println(idFood.size());
            restaurants.updateOne(Filters.eq("kode", restRow), Updates.set("foods", idFood));
            System.out.println("del on Food = " + del.getDeletedCount());
            return true;
        }
        return false;
    }

    public ArrayList<User> getUser() throws IOException {
        ArrayList<User> resultList = new ArrayList<>();
        FindIterable<User> userIterable = users.find();

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
                userIterable = users.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "full_name":{
                userIterable = users.find(regex("full_name", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "email":{
                userIterable = users.find(regex("email", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "telp_no":{
                userIterable = users.find(regex("telp_no", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "street":{
                userIterable = users.find(regex("location.street", ".*" + Pattern.quote(value) + ".*"));
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
            FindIterable<User> userIterable = users.find();
            int saldoNow = saldo;
            for (User user : userIterable) {
                if(user.getKode().equals(row)) {
                    saldoNow+=user.getSaldo_gopay();
                }
            }
            users.updateOne(Filters.eq("kode", row), Updates.set("saldo_gopay", saldoNow));
            System.out.println("data updated");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(String row) {
        DeleteResult del = users.deleteOne(eq("kode", row));
        System.out.println("del on User = " + del.getDeletedCount());
        return true;
    }

    public ArrayList<Pesanan> getPesanan() throws IOException {
        ArrayList<Pesanan> resultList = new ArrayList<>();
        FindIterable<Pesanan> pesananIterable = pesanan.find();

        for (Pesanan pesanan : pesananIterable) {
            System.out.println(pesanan);
            resultList.add(pesanan);
        }
        return resultList;
    }

    public ArrayList<Pesanan> getPesananByCategory(String category, String value) throws IOException {
        ArrayList<Pesanan> resultList = new ArrayList<>();
        FindIterable<Pesanan> pesananIterable = null;

        switch(category) {
            case "kode":{
                pesananIterable = pesanan.find(regex("kode", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "user_id":{
                pesananIterable = pesanan.find(regex("user_id", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "restaurant_id":{
                pesananIterable = pesanan.find(regex("restaurant_id", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "driver_id":{
                pesananIterable = pesanan.find(regex("driver_id", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "pesanan":{
                pesananIterable = pesanan.find(regex("pesanan", ".*" + Pattern.quote(value) + ".*"));
                break;
            }
            case "payment_method":{
                if(value == "gopay") {
                    pesananIterable = pesanan.find(regex("payment_method", ".*" + Pattern.quote(Boolean.toString(true)) + ".*"));
                }
                else if(value == "cash") {
                    pesananIterable = pesanan.find(regex("payment_method", ".*" + Pattern.quote(Boolean.toString(false)) + ".*"));
                }
                break;
            }

        }

        for(Pesanan temp : pesananIterable) {
            resultList.add(temp);
            System.out.println(temp);
        }

        return resultList;
    }

    public Restaurant getRestaurantByID(String id) {
        Restaurant resto;
        resto = restaurants.find(eq("kode", id)).first();
        return resto;
    }

    public ArrayList<Pesanan> getPesananByUserID(String userID) {
        ArrayList<Pesanan> resultList = new ArrayList<>();
        FindIterable<Pesanan> pesananIterable = pesanan.find(eq("userId", userID));
        for (Pesanan pesan : pesananIterable) {
            System.out.println(pesan.getKode());
            resultList.add(pesan);
        }
        return resultList;
    }

    public Driver getDriverByID(String id) {
        Driver driver;
        driver = drivers.find(eq("kode", id)).first();
        return driver;
    }

    public Location getLocationByID(String id) {
        Location loc;
        loc = locations.find(eq("kode", id)).first();
        return loc;
    }

    public List<String> printRoute(Location src, Location dest) {
        List<Location> route = locUtils.findShortestRoute(src, dest);
        return route.stream().map(Location::getStreet).collect(Collectors.toList());
    }

    public String translate_method(boolean method) {
        return (method) ? "gopay" : "cash";
    }

    public Pesanan getPesananByID(String id) {
        Pesanan ordered;
        ordered = pesanan.find(eq("kode", id)).first();
        return ordered;
    }

    public Food getFoodByID(String id) {
        Food food;
        food = foods.find(eq("kode", id)).first();
        return food;
    }

    public double computeDistance(Location resto, Location user) {
        HaversineScorer calculateDist = new HaversineScorer();
        return calculateDist.computeCost(resto, user);
    }

    public void sortByDistance(List<Restaurant> ls, User usr) {
        HaversineScorer calculateDist = new HaversineScorer();
        Collections.sort(ls, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant t, Restaurant t1) {
                double tScore = calculateDist.computeCost(t.getLocation(), usr.getLocation());
                double t1Score = calculateDist.computeCost(t1.getLocation(), usr.getLocation());
                if(tScore > t1Score) {
                    return 1;
                } else if(tScore < t1Score) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public User getUserByID(String id) {
        User user;
        user = users.find(eq("kode", id)).first();
        return user;
    }
}
