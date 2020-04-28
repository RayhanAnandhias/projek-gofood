/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Food;
import model.Restaurant;
import model.User;

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
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        System.out.println("ACTION = "+action);
        MongoDbUtils mongodbUtils = new MongoDbUtils();
        
        if(action.equals("Sign Up")) {
            request.getRequestDispatcher("/signup.jsp")
                .forward(request, response);
            
        }else if(action.equals("Sign In")) {
            request.getRequestDispatcher("/signin.jsp")
                .forward(request, response);
            
        }else if(action.equals("insertUser")) {
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
        } else if(action.equals("submitLogin")) {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            User result = mongodbUtils.validateUser(email,pwd);
            if(result != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", result);
                request.getRequestDispatcher("/menu.jsp")
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
        } else if(action.equals("Update Profile")) {
            request.getRequestDispatcher("/updateUser.jsp")
                    .forward(request, response);
        } else if(action.equals("Log Out")) {
            HttpSession session=request.getSession();  
            session.invalidate();
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        }else if(action.equals("updateUser")) {
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
                request.getRequestDispatcher("/menu.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
        } else if(action.equals("Check Gopay")) {
            request.getRequestDispatcher("/checkGopay.jsp")
                .forward(request, response);
        } else if(action.equals("Search Restaurant")) {
            request.getRequestDispatcher("/searchResto.jsp")
                .forward(request, response);
        } else if(action.equals("searchFood")) {
            
        } else if(action.equals("createOrder")) {
            
        } else if(action.equals("seeOrder")) {
            
        }else if("Back to Main Menu".equals(action)){
            request.getRequestDispatcher("/menu.jsp")
                .forward(request, response);
        }else if("Search Restaurant by".equals(action)) {
        	try{
        		String category = request.getParameter("attribute rest");
    	    	String boxValue = request.getParameter("search restaurant box");
	    		List<Restaurant> listRestaurant = mongodbUtils.getRestaurantByCategory(category, boxValue);
				request.setAttribute("dataList", listRestaurant);
				request.setAttribute("sdbvalue", boxValue);
				request.setAttribute("attributerest", category);
				request.getRequestDispatcher("/searchResto.jsp").forward(request, response);
            } 
            catch(NullPointerException e){ 
            	request.getRequestDispatcher("/searchResto.jsp").forward(request, response);
            } 
        }else if("view food".equals(action)) {
        	vrow = request.getParameter("kode");
        	restaurantName = request.getParameter("name");
        	System.out.println(vrow + " " + restaurantName);
        	showFoodOnRestaurantData(request, response, mongodbUtils, vrow);
        }
        else if("Retrieve All Restaurant Data".equals(action)) {
        	showRestaurantData(request, response, mongodbUtils);
        }
    }
    
    public void showRestaurantData(HttpServletRequest request, HttpServletResponse response,
			MongoDbUtils mongodbUtils) {
    	try {
            List<Restaurant> listRestaurant = mongodbUtils.getRestaurant();
            request.setAttribute("dataList", listRestaurant);
            request.getRequestDispatcher("/searchResto.jsp").forward(request, response);
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
            request.getRequestDispatcher("/ReadFoodUser.jsp").forward(request, response);
        }catch (Exception e) {
                e.printStackTrace();
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

}
