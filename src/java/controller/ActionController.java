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

import model.Driver;
import model.Food;
import model.Location;
import model.Motor;
import model.Restaurant;

/**
 *
 * @author rayhan
 */
public class ActionController extends HttpServlet {
	private String vrow;
	private String restaurantName;
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("ACTION = "+action);
        MongoDbUtils mongodbUtils = new MongoDbUtils();
        
        if("Register Driver".equals(action)) {
        	RequestDispatcher rd = request.getRequestDispatcher("/RegDriver.jsp");
			rd.forward(request, response);
        }
        else if("Register Restaurant".equals(action)) {
        	RequestDispatcher rd = request.getRequestDispatcher("/RegRest.jsp");
			rd.forward(request, response);
        }
        else if("RegDriver".equals(action)) {
        	String fullName = request.getParameter("fullname");
			String email = request.getParameter("email");
			String telpNum = request.getParameter("telpnum");
			String platNum = request.getParameter("platnum");
			String merk = request.getParameter("merk");	
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			
			boolean result = mongodbUtils.insertDriver(fullName, email, telpNum, platNum, merk, street, city);
			
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/SuccessInsert.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/FailedInsert.jsp");
				rd.forward(request, response);
			}
        }
        else if("RegRest".equals(action)) {
        	try{ 
        		String fullName = request.getParameter("name");
            	String street = request.getParameter("street");
    			String city = request.getParameter("city");
    			String telpNum = request.getParameter("telpnum");
    			String detail = request.getParameter("detail");
    			
    			String foodName[] = request.getParameterValues("foodname");
    			String foodPrice[] = request.getParameterValues("foodprice");
    			String foodQuantity[] = request.getParameterValues("foodquant");
    			String foodDetail[] = request.getParameterValues("fooddetail");
    			
    			boolean result = mongodbUtils.insertRestaurant(fullName, street, city, telpNum, detail, foodName, foodPrice,
    					foodQuantity, foodDetail);
    			
    			if(result) {
    				RequestDispatcher rd = request.getRequestDispatcher("/SuccessInsert.jsp");
    				rd.forward(request, response);
    			}
    			else {
    				RequestDispatcher rd = request.getRequestDispatcher("/FailedInsert.jsp");
    				rd.forward(request, response);
    			}
    			
            } 
            catch(NumberFormatException e){ 
            } 
        	catch(NullPointerException e){ 
            } 
        }
        else if("View Driver".equals(action)) {
        	RequestDispatcher rd = request.getRequestDispatcher("/ReadDriver.jsp");
			rd.forward(request, response);
        }
        else if("View Restaurant".equals(action)) {
        	RequestDispatcher rd = request.getRequestDispatcher("/ReadRestaurant.jsp");
			rd.forward(request, response);
        }
        else if("view food".equals(action)) {
        	vrow = request.getParameter("kode");
        	restaurantName = request.getParameter("name");
        	
        	request.setAttribute("restnameparam", restaurantName);
        	RequestDispatcher rd = request.getRequestDispatcher("/ReadFood.jsp");
			rd.forward(request, response);
        }
        else if("Retrieve All Driver Data".equals(action)) {
        	showDriverData(request, response, mongodbUtils);
        }
        else if("Retrieve All Restaurant Data".equals(action)) {
        	showRestaurantData(request, response, mongodbUtils);
        }
        else if("Retrieve All Food Data".equals(action)) {
        	showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
        }
        else if("Search Driver by".equals(action)) {
        	try{ 
        		String category = request.getParameter("attribute driver");
    	    	String boxValue = request.getParameter("search driver box");
	    		List<Driver> listDriver = mongodbUtils.getDriverByCategory(category, boxValue);
				request.setAttribute("dataList", listDriver);
				request.setAttribute("sdbvalue", boxValue);
				request.setAttribute("attributedriver", category);
				request.getRequestDispatcher("/ReadDriver.jsp").forward(request, response);
            } 
            catch(NullPointerException e){ 
            	request.getRequestDispatcher("/ReadDriver.jsp").forward(request, response);
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
				request.getRequestDispatcher("/ReadRestaurant.jsp").forward(request, response);
            } 
            catch(NullPointerException e){ 
            	request.getRequestDispatcher("/ReadRestaurant.jsp").forward(request, response);
            } 
        }
        else if("delete driver".equals(action)) {
        	String row = request.getParameter("kode");
			System.out.println("ROW DELETED = "+row);
			
			boolean result = mongodbUtils.deleteDriver(row);
			if(result) {
				showDriverData(request, response, mongodbUtils);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/FailedDelete.jsp");
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
				RequestDispatcher rd = request.getRequestDispatcher("/FailedDelete.jsp");
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
        	String locationKode = request.getParameter("locationkode");
        	String street = request.getParameter("street");
        	String city = request.getParameter("city");
        	
        	Location location = new Location(street, city);
        	location.setKode(locationKode);
        	
        	Driver driver = new Driver(fullName, email, telpNum, new Motor(numPlat, merk), location);
        	request.setAttribute("driver", driver);	
			request.getRequestDispatcher("/UpdateDriver.jsp").forward(request, response);
        }
        else if("update restaurant".equals(action)) {
        	
        }
        else if("AfterUpdateDriver".equals(action)) {
        	String fullName = request.getParameter("updatedfullname");
        	String email = request.getParameter("updatedemail");
        	String telpNum = request.getParameter("updatedtelpnum");
        	String numPlat = request.getParameter("updatedplatnum");
        	String merk = request.getParameter("updatedmerk");
        	String locationKode = request.getParameter("locationkode");
        	String street = request.getParameter("updatedstreet");
        	String city = request.getParameter("updatedcity");
        	
        	System.out.println("ROW UPDATED = "+vrow);				
			boolean resultUpdate = mongodbUtils.updateDriver(vrow, fullName, email, telpNum, numPlat, merk, locationKode, street, city);
			if(resultUpdate) {
				showDriverData(request, response, mongodbUtils);
			}else {
				RequestDispatcher rdUpdate = request.getRequestDispatcher("/FailedUpdate.jsp");
				rdUpdate.forward(request, response);
			}
        }
        else if("AfterUpdateRestaurant".equals(action)) {
        	
        }
        else if("Back to Main Menu".equals(action)){
        	RequestDispatcher rd = request.getRequestDispatcher("/OperatorMenu.jsp");
			rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ActionController Servlet";
    }// </editor-fold>
    
    public void showDriverData(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils) {
    	try {
			List<Driver> listDriver = mongodbUtils.getDriver();
			request.setAttribute("dataList", listDriver);
			request.getRequestDispatcher("/ReadDriver.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void showRestaurantData(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils) {
    	try {
			List<Restaurant> listRestaurant = mongodbUtils.getRestaurant();
			request.setAttribute("dataList", listRestaurant);
			request.getRequestDispatcher("/ReadRestaurant.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void showFoodOnRestaurantData(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils, String row) {
    	try {
			List<Food> listFood = mongodbUtils.getFoodOnRestaurant(row);
			request.setAttribute("dataList", listFood);
			request.setAttribute("restnameparam", restaurantName);
			request.getRequestDispatcher("/ReadFood.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
}
