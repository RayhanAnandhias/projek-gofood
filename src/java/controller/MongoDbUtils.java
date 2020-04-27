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
import model.Food;
import model.Restaurant;
import model.User;
import model.Location;
import astar.*;
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
import model.Pesanan;
import org.bson.types.ObjectId;
/**
 *
 * @author rayhan
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
        usersVersion = database.getCollection("usersVersion", User.class);
        restaurants = database.getCollection("restaurants", Restaurant.class);
        foods = database.getCollection("foods", Food.class);
        locations = database.getCollection("locations", Location.class);
        drivers = database.getCollection("drivers", Driver.class);
        pesanan = database.getCollection("pesanan", Pesanan.class);
    }
    
    public boolean insertDataUser(String name, String pwd, String email, String noTelp, int saldo) {
        String id = new ObjectId().toString();
        User user;
        Location loc;
        Random rn = new Random();
        try {	
                loc = locations.find(eq("kode", Integer.toString(rn.nextInt(5) + 1))).first();
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

    public boolean validateUser(String email, String pwd) {
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
        Set<Location> loc = new HashSet<>();
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
    
    public List<Location> findShortestRoute(Location source, Location dest) {
        Graph maps;
        RouteFinder routeFinder;
        Map<String, Set<String>> connections = new HashMap<>();
        try {
            Set<Location> location  = getAllLocData();
            connections.put("1", Stream.of("2","4","5").collect(Collectors.toSet()));
            connections.put("2", Stream.of("1","4","6").collect(Collectors.toSet()));
            connections.put("3", Stream.of("4","8","5").collect(Collectors.toSet()));
            connections.put("4", Stream.of("2","1","3","5","10").collect(Collectors.toSet()));
            connections.put("5", Stream.of("1","3","4","9").collect(Collectors.toSet()));
            connections.put("6", Stream.of("2","8","7").collect(Collectors.toSet()));
            connections.put("7", Stream.of("6","8","10").collect(Collectors.toSet()));
            connections.put("8", Stream.of("3","6","7").collect(Collectors.toSet()));
            connections.put("9", Stream.of("5","10").collect(Collectors.toSet()));
            connections.put("10", Stream.of("4","9","7").collect(Collectors.toSet()));
            maps = new Graph(location,connections);
            routeFinder = new RouteFinder(maps, new HaversineScorer(), new HaversineScorer());
            List<Location> route = routeFinder.findRoute(source, dest);
            //System.out.println(route.stream().map(Location::getName).collect(Collectors.toList()));
            return route;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public boolean insertLocData() {
        try {
            Set<Location> loc = new LinkedHashSet<>();
            loc.add(new Location("1", "Acton Town", 51.5028, -0.2801));
            loc.add(new Location("2", "Aldgate", 51.5143, -0.0755));
            loc.add(new Location("3", "Aldgate East", 51.5154, -0.0726));
            loc.add(new Location("4", "All Saints", 51.5107, -0.013));
            loc.add(new Location("5", "Alperton", 51.5407, -0.2997));
            loc.add(new Location("6", "Amersham", 51.6736, -0.607));
            loc.add(new Location("7", "Angel", 51.5322, -0.1058));
            loc.add(new Location("8", "Archway", 51.5653, -0.1353));
            loc.add(new Location("9", "Arnos Grove", 51.6164, -0.1331));
            loc.add(new Location("10", "Arsenal", 51.5586, -0.1059));
            for (Location location : loc) {
                System.out.println(location.getKode() + "," + location.getStreet());
                locations.insertOne(location);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean createPesanan(String userId, String restoId, String locId, List<String> listOrder, boolean method, int hargaMakanan) {
        Pesanan orders;
        Driver driver;
        User user;
        Restaurant resto;
        int ongkir;
        double jarak;
        try {
            user = users.find(eq("kode", userId)).first();
            resto = restaurants.find(eq("kode", restoId)).first();
            driver = chooseDriver(restoId);
            jarak = HaversineScorer.computeCost(user.getLocation(), resto.getLocation());
            ongkir = (int)jarak*2000;
            orders = new Pesanan(userId, restoId, driver.getKode(), listOrder, method, ongkir, ongkir + hargaMakanan);
            pesanan.insertOne(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Driver chooseDriver(String restoLocId) {
        Driver idDriver = null;
        Location restoLocation;
        List<Driver> lsDriver;
        try {
            restoLocation = locations.find(eq("kode", restoLocId)).first();
            lsDriver = getAllDriver();
            Collections.sort(lsDriver, new Comparator<Driver>() {
                @Override
                public int compare(Driver t, Driver t1) {
                    Double tScore = HaversineScorer.computeCost(t.getLocation(), restoLocation);
                    Double t1Score = HaversineScorer.computeCost(t1.getLocation(), restoLocation);
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
}
