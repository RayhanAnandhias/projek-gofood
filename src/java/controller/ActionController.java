/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author rayhan
 * @author Ananda Bayu
 */
public class ActionController extends HttpServlet {
    private String vrow;
    private String restaurantName;
    private String idResto;
    private String userid;
    List<Food> fd = new ArrayList<Food>();
    boolean locationHasBeenInserted = false;

    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        System.out.println("ACTION = "+action);

        MongoDbUtils mongodbUtils = new MongoDbUtils();

        if(!locationHasBeenInserted) {
        	mongodbUtils.insertLocData();
        	locationHasBeenInserted = true;
        }

        if(action.equals("Sign Up")) {
            request.getRequestDispatcher("/user-page/signup.jsp")
                .forward(request, response);
            
        }
        else if(action.equals("Sign In")) {
            request.getRequestDispatcher("/user-page/signin.jsp")
                .forward(request, response);
            
        }
        else if(action.equals("operator")) {
            request.getRequestDispatcher("/op-page/OperatorMenu.jsp")
                .forward(request, response);
            
        }
        else if(action.equals("insertUser")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String noTelp = request.getParameter("no_telp");
            String pwd = request.getParameter("pwd");
            int saldo = Integer.parseInt(request.getParameter("saldo"));
            boolean result = 
                    mongodbUtils.insertDataUser(name, pwd, email, noTelp, saldo);
            if(result) {
                request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        }
        else if(action.equals("submitLogin")) {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            User result = mongodbUtils.validateUser(email,pwd);
            if(result != null) {
                userid = result.getKode();
                HttpSession session = request.getSession();
                session.setAttribute("user", result);
                fd = new ArrayList<Food>();
                idResto = null;
                request.getRequestDispatcher("/user-page/menu.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        }
        else if(action.equals("InsertLoc")) {
            boolean result  = mongodbUtils.insertLocData();
//            boolean result = true;
            if(result) {
                request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        }
        else if(action.equals("Update Profile")) {
            request.getRequestDispatcher("/user-page/updateUser.jsp")
                    .forward(request, response);
        }
        else if(action.equals("Log Out")) {
            HttpSession session=request.getSession();  
            session.invalidate();
            fd = new ArrayList<Food>();
            idResto = null;
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        }
        else if(action.equals("updateUser")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String noTelp = request.getParameter("no_telp");
            String pwd = request.getParameter("pwd");
            String kode = request.getParameter("kode");
            boolean result = 
                    mongodbUtils.updateDataUser(kode, name, pwd, email, noTelp);
            if(result) {
                User user = mongodbUtils.validateUser(email,pwd);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("/user-page/menu.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        }
        else if(action.equals("Check Gopay")) {
            request.getRequestDispatcher("/user-page/checkGopay.jsp")
                .forward(request, response);
        }
        else if(action.equals("What do you want to eat?")) {
            request.getRequestDispatcher("/user-page/searchResto.jsp")
                .forward(request, response);
        }
        else if(action.equals("Create Order")) {
            Restaurant resto = mongodbUtils.getRestaurantByID(idResto);
            request.setAttribute("dataList", fd);
            request.setAttribute("resto", resto);
            request.getRequestDispatcher("/user-page/createOrder.jsp")
                .forward(request, response);
        }
        else if(action.equals("See Order")) {
            List<Pesanan> lsPesanan = mongodbUtils.getPesananByUserID(userid);
            request.setAttribute("dataList", lsPesanan);
            request.setAttribute("util", mongodbUtils);
            request.getRequestDispatcher("/user-page/viewOrder.jsp")
                    .forward(request, response);
        }
        else if("Back to Main menu".equals(action)){
        	RequestDispatcher rd = request.getRequestDispatcher("/user-page/menu.jsp");
			rd.forward(request, response);
        }
        else if("Retrieve All Restaurant data".equals(action)) {
        	showRestaurantDataUser(request, response, mongodbUtils);
        }
        else if("view Food".equals(action)) {
        	vrow = request.getParameter("kode");
        	restaurantName = request.getParameter("name");
        	System.out.println(vrow + " " + restaurantName);
        	showFoodOnRestaurantDataUser(request, response, mongodbUtils, vrow);
        }
        else if("view ordered food".equals(action)) {
            String idPesanan = request.getParameter("kodePesanan");
            Pesanan ordered = mongodbUtils.getPesananByID(idPesanan);
            List<ItemPesanan> orderedList = ordered.getPesanan();
            request.setAttribute("util", mongodbUtils);
            request.setAttribute("dataList", orderedList);
            request.getRequestDispatcher("/user-page/viewOrderedFood.jsp").forward(request, response);
        }
        else if("Search By".equals(action)) {
        	try{
        		String category = request.getParameter("attribute rest");
    	    	String boxValue = request.getParameter("search restaurant box");
	    		List<Restaurant> listRestaurant = mongodbUtils.getRestaurantByCategory(category, boxValue);
				request.setAttribute("dataList", listRestaurant);
				request.setAttribute("sdbvalue", boxValue);
				request.setAttribute("attributerest", category);
                request.setAttribute("util", mongodbUtils);
				request.getRequestDispatcher("/user-page/searchResto.jsp").forward(request, response);
            } 
            catch(NullPointerException e){ 
            	request.getRequestDispatcher("/user-page/searchResto.jsp").forward(request, response);
            } 
        }
        else if("Recommended Nearest Restaurant".equals(action)) {
            List<Restaurant> listRestaurant = mongodbUtils.getRestaurant();
            mongodbUtils.sortByDistance(listRestaurant, mongodbUtils.getUserByID(userid));
            request.setAttribute("dataList", listRestaurant);
            request.setAttribute("util", mongodbUtils);
            request.getRequestDispatcher("/user-page/searchResto.jsp").forward(request, response);
        }
        else if("add to cart".equals(action)) {
            String id_resto = request.getParameter("id_resto");
            idResto = id_resto;
            String kodeFood = request.getParameter("kode");
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String detail = request.getParameter("detail");
            Food onePesanan = new Food(name, price, detail, id_resto);
            onePesanan.setKode(kodeFood);
            fd.add(onePesanan);
            showFoodOnRestaurantDataUser(request, response, mongodbUtils, vrow);
            
        }
        else if("orderFood".equals(action)) {
            String id_user = request.getParameter("idUser");
            System.out.println(id_user);
            String id_resto = request.getParameter("kodeResto");
            System.out.println(id_resto);
            String foodKode[] = request.getParameterValues("kode");
            String foodPrice[] = request.getParameterValues("price");
            String foodQuantity[] = request.getParameterValues("quantity");
            String foodNote[] = request.getParameterValues("note");
            String payment = request.getParameter("method");
            boolean method;
            if(payment.equals("gopay"))
                method = true;
            else
                method = false;
            boolean result = mongodbUtils.createPesanan(id_user, id_resto, foodKode, foodPrice, foodQuantity, foodNote, method);
            if(result) {
                fd = new ArrayList<Food>();
                idResto = null;
                User user = mongodbUtils.getUserByID(userid);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("/user-page/menu.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        }

        //operator feature
        else if("Register Driver".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/RegDriver.jsp");
            rd.forward(request, response);
        }
        else if("Register Restaurant".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/RegRest.jsp");
            rd.forward(request, response);
        }
        else if("RegDriver".equals(action)) {
            String fullName = request.getParameter("fullname");
            String email = request.getParameter("email");
            String telpNum = request.getParameter("telpnum");
            String platNum = request.getParameter("platnum");
            String merk = request.getParameter("merk");

            boolean result = mongodbUtils.insertDriver(fullName, email, telpNum, platNum, merk);

            if(result) {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/SuccessInsert.jsp");
                rd.forward(request, response);
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedInsert.jsp");
                rd.forward(request, response);
            }
        }
        else if("RegRest".equals(action)) {
            try{
                String fullName = request.getParameter("name");
                String telpNum = request.getParameter("telpnum");
                String detail = request.getParameter("detail");

                String foodName[] = request.getParameterValues("foodname");
                String foodPrice[] = request.getParameterValues("foodprice");
//    			String foodQuantity[] = request.getParameterValues("foodquant");
                String foodDetail[] = request.getParameterValues("fooddetail");

                //boolean result = mongodbUtils.insertRestaurant(fullName, telpNum, detail, foodName, foodPrice, foodQuantity, foodDetail);
                boolean result = mongodbUtils.insertRestaurant(fullName, telpNum, detail, foodName, foodPrice, foodDetail);

                if(result) {
                    RequestDispatcher rd = request.getRequestDispatcher("/op-page/SuccessInsert.jsp");
                    rd.forward(request, response);
                }
                else {
                    RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedInsert.jsp");
                    rd.forward(request, response);
                }

            }
            catch(NumberFormatException e){
            }
            catch(NullPointerException e){
            }
        }
        else if("View Driver".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/ReadDriver.jsp");
            rd.forward(request, response);
        }
        else if("View Restaurant".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/ReadRestaurant.jsp");
            rd.forward(request, response);
        }
        else if("view food".equals(action)) {
            vrow = request.getParameter("kode");
            restaurantName = request.getParameter("name");
            System.out.println(vrow + " " + restaurantName);
            showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
        }
        else if("view food from update".equals(action)) {
            showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
        }
        else if("View User".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/ReadUser.jsp");
            rd.forward(request, response);
        }
        else if("View Pesanan".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/ReadPesanan.jsp");
            rd.forward(request, response);
        }
        else if("Retrieve All Driver Data".equals(action)) {
            showDriverData(request, response, mongodbUtils);
        }
        else if("Retrieve All Restaurant Data".equals(action)) {
            showRestaurantData(request, response, mongodbUtils);
        }
        else if("Retrieve All User Data".equals(action)) {
            showUserData(request, response, mongodbUtils);
        }
        else if("Retrieve All Pesanan Data".equals(action)) {
            showPesananData(request, response, mongodbUtils);
        }
        else if("Search Driver by".equals(action)) {
            try{
                String category = request.getParameter("attribute driver");
                String boxValue = request.getParameter("search driver box");
                List<Driver> listDriver = mongodbUtils.getDriverByCategory(category, boxValue);
                request.setAttribute("dataList", listDriver);
                request.setAttribute("sdbvalue", boxValue);
                request.setAttribute("attributedriver", category);
                request.getRequestDispatcher("/op-page/ReadDriver.jsp").forward(request, response);
            }
            catch(NullPointerException e){
                request.getRequestDispatcher("/op-page/ReadDriver.jsp").forward(request, response);
            }
        }
        else if("Search Restaurant by".equals(action)) {
            try{
                String category = request.getParameter("attribute rest");
                String boxValue = request.getParameter("search restaurant box");
                List<Restaurant> listRestaurant = mongodbUtils.getRestaurantByCategory(category, boxValue);
                request.setAttribute("dataList", listRestaurant);
                request.setAttribute("sdbvalue", boxValue);
                request.setAttribute("attributerest", category);
                request.getRequestDispatcher("/op-page/ReadRestaurant.jsp").forward(request, response);
            }
            catch(NullPointerException e){
                request.getRequestDispatcher("/op-page/ReadRestaurant.jsp").forward(request, response);
            }
        }
        else if("Search User by".equals(action)) {
            try{
                String category = request.getParameter("attribute user");
                String boxValue = request.getParameter("search user box");
                List<User> listUser = mongodbUtils.getUserByCategory(category, boxValue);
                request.setAttribute("dataList", listUser);
                request.setAttribute("sdbvalue", boxValue);
                request.setAttribute("attributeuser", category);
                request.getRequestDispatcher("/op-page/ReadUser.jsp").forward(request, response);
            }
            catch(NullPointerException e){
                request.getRequestDispatcher("/op-page/ReadUser.jsp").forward(request, response);
            }
        }
        else if("Search Pesanan by".equals(action)) {
            try{
                String category = request.getParameter("attribute pesanan");
                String boxValue = request.getParameter("search pesanan box");
                List<Pesanan> listUser = mongodbUtils.getPesananByCategory(category, boxValue);
                request.setAttribute("dataList", listUser);
                request.setAttribute("sdbvalue", boxValue);
                request.setAttribute("attributepesanan", category);
                request.getRequestDispatcher("/op-page/ReadPesanan.jsp").forward(request, response);
            }
            catch(NullPointerException e){
                request.getRequestDispatcher("/op-page/ReadPesanan.jsp").forward(request, response);
            }
        }
        else if("delete driver".equals(action)) {
            String row = request.getParameter("kode");
            System.out.println("ROW DELETED = "+row);

            boolean result = mongodbUtils.deleteDriver(row);
            if(result) {
                showDriverData(request, response, mongodbUtils);
            }else {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedDelete.jsp");
                rd.forward(request, response);
            }
        }
        else if("delete restaurant".equals(action)) {
            String row = request.getParameter("kode");
            System.out.println("ROW DELETED = "+row);

            boolean result = mongodbUtils.deleteRestaurant(row);
            if(result) {
                showRestaurantData(request, response, mongodbUtils);
            }else {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedDelete.jsp");
                rd.forward(request, response);
            }
        }
        else if("delete food".equals(action)) {
            String row = request.getParameter("kode");
            System.out.println("ROW DELETED = "+row);

            boolean result = mongodbUtils.deleteFoodOnRestaurant(vrow, row);
            if(result) {
                showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
            }else {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedDelete.jsp");
                rd.forward(request, response);
            }
        }
        else if("delete user".equals(action)) {
            String row = request.getParameter("kode");
            System.out.println("ROW DELETED = "+row);

            boolean result = mongodbUtils.deleteUser(row);
            if(result) {
                showUserData(request, response, mongodbUtils);
            }else {
                RequestDispatcher rd = request.getRequestDispatcher("/op-page/FailedDelete.jsp");
                rd.forward(request, response);
            }
        }
        else if("update driver".equals(action)) {
            vrow = request.getParameter("kode");
            String fullName = request.getParameter("fullname");
            String email = request.getParameter("email");
            String telpNum = request.getParameter("telpnum");
            String numPlat = request.getParameter("numplat");
            String merk = request.getParameter("merk");

            Driver driver = new Driver(fullName, email, telpNum, new Motor(numPlat, merk), null);
            request.setAttribute("driver", driver);
            request.getRequestDispatcher("/op-page/UpdateDriver.jsp").forward(request, response);
        }
        else if("update restaurant".equals(action)) {
            vrow = request.getParameter("kode");
            String name = request.getParameter("name");
            String telpNum = request.getParameter("telpnum");
            String detail = request.getParameter("detail");

            Restaurant restaurant = new Restaurant(name, null, telpNum, detail, null);
            request.setAttribute("restaurant", restaurant);
            request.getRequestDispatcher("/op-page/UpdateRestaurant.jsp").forward(request, response);
        }
        else if("update food".equals(action)) {
            String foodrow = request.getParameter("kode");
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String detail = request.getParameter("detail");
            String idrest = request.getParameter("idResto");
            //int quantity = Integer.parseInt(request.getParameter("quantity"));

            //Food food = new Food(name, price, quantity, detail);
            Food food = new Food(name, price, detail, idrest);
            food.setKode(foodrow);
            request.setAttribute("food", food);
            request.getRequestDispatcher("/op-page/UpdateFood.jsp").forward(request, response);
        }
        else if("top up gopay".equals(action)) {
            vrow = request.getParameter("kode");
            int saldo = Integer.parseInt(request.getParameter("saldo_gopay"));

            User user = new User(null, null,null,null,null, saldo);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/op-page/TopUpGopay.jsp").forward(request, response);
        }
        else if("AfterUpdateDriver".equals(action)) {
            String fullName = request.getParameter("updatedfullname");
            String email = request.getParameter("updatedemail");
            String telpNum = request.getParameter("updatedtelpnum");
            String numPlat = request.getParameter("updatedplatnum");
            String merk = request.getParameter("updatedmerk");

            System.out.println("ROW UPDATED = "+vrow);
            boolean resultUpdate = mongodbUtils.updateDriver(vrow, fullName, email, telpNum, numPlat, merk);
            if(resultUpdate) {
                showDriverData(request, response, mongodbUtils);
            }else {
                RequestDispatcher rdUpdate = request.getRequestDispatcher("/op-page/FailedUpdate.jsp");
                rdUpdate.forward(request, response);
            }
        }
        else if("AfterUpdateRestaurant".equals(action)) {
            String name = request.getParameter("updatedname");
            String telpNum = request.getParameter("updatedtelpnum");
            String detail = request.getParameter("updateddetail");

            System.out.println("ROW UPDATED = "+vrow);
            boolean resultUpdate = mongodbUtils.updateRestaurant(vrow, name, telpNum, detail);
            if(resultUpdate) {
                showRestaurantData(request, response, mongodbUtils);
            }else {
                RequestDispatcher rdUpdate = request.getRequestDispatcher("/op-page/FailedUpdate.jsp");
                rdUpdate.forward(request, response);
            }
        }
        else if("AfterUpdateFood".equals(action)) {
            String row = request.getParameter("kode");
            String name = request.getParameter("updatedname");
            int price = Integer.parseInt(request.getParameter("updatedprice"));
            String detail = request.getParameter("updateddetail");
            String idrest = request.getParameter("idRest");

            System.out.println("ROW UPDATED = "+row);
            boolean resultUpdate = mongodbUtils.updateFoodOnRestaurant(row, name, price, detail, idrest);
            if(resultUpdate) {
                restaurantName = name;
                showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
            }else {
                RequestDispatcher rdUpdate = request.getRequestDispatcher("/op-page/FailedUpdate.jsp");
                rdUpdate.forward(request, response);
            }
        }
        else if("AfterTopUp".equals(action)) {
            int saldo = Integer.parseInt(request.getParameter("topupval"));
            if(saldo >= 10000) {
                System.out.println("ROW UPDATED = "+vrow);
                boolean resultUpdate = mongodbUtils.topUpGopay(vrow, saldo);
                if(resultUpdate) {
                    showUserData(request, response, mongodbUtils);
                }
            }
            else {
                RequestDispatcher rdUpdate = request.getRequestDispatcher("/op-page/FailedTopUp.jsp");
                rdUpdate.forward(request, response);
            }
        }
        else if("Back to Main Menu".equals(action)){
            RequestDispatcher rd = request.getRequestDispatcher("/op-page/OperatorMenu.jsp");
            rd.forward(request, response);
        }
    }

    public void showRestaurantDataUser(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils) {
    	try {
			List<Restaurant> listRestaurant = mongodbUtils.getRestaurant();
			request.setAttribute("dataList", listRestaurant);
			request.setAttribute("util", mongodbUtils);
			request.getRequestDispatcher("/user-page/searchResto.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void showFoodOnRestaurantDataUser(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils, String row) {
    	try {
			List<Food> listFood = mongodbUtils.getFoodOnRestaurant(row);
			request.setAttribute("dataList", listFood);
                        request.setAttribute("idResto", vrow);
			request.setAttribute("restnameparam", restaurantName);
			request.getRequestDispatcher("/user-page/ReadFoodUser.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void showDriverData(HttpServletRequest request, HttpServletResponse response,
                               MongoDbUtils mongodbUtils) {
        try {
            List<Driver> listDriver = mongodbUtils.getDriver();
            request.setAttribute("dataList", listDriver);
            request.getRequestDispatcher("/op-page/ReadDriver.jsp").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRestaurantData(HttpServletRequest request, HttpServletResponse response,
                                   MongoDbUtils mongodbUtils) {
        try {
            List<Restaurant> listRestaurant = mongodbUtils.getRestaurant();
            request.setAttribute("dataList", listRestaurant);
            request.getRequestDispatcher("/op-page/ReadRestaurant.jsp").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFoodOnRestaurantData(HttpServletRequest request, HttpServletResponse response,
                                         MongoDbUtils mongodbUtils, String row) {
        try {
            List<Food> listFood = mongodbUtils.getFoodOnRestaurant(row);
            System.out.println(listFood);
            request.setAttribute("dataList", listFood);
            request.setAttribute("restnameparam", restaurantName);
            request.getRequestDispatcher("/op-page/ReadFood.jsp").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUserData(HttpServletRequest request, HttpServletResponse response,
                             MongoDbUtils mongodbUtils) {
        try {
            List<User> listUser = mongodbUtils.getUser();
            request.setAttribute("dataList", listUser);
            request.getRequestDispatcher("/op-page/ReadUser.jsp").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPesananData(HttpServletRequest request, HttpServletResponse response,
                                MongoDbUtils mongodbUtils) {
        try {
            List<Pesanan> listPesanan = mongodbUtils.getPesanan();
            request.setAttribute("dataList", listPesanan);
            request.getRequestDispatcher("/op-page/ReadPesanan.jsp").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
