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

/**
 *
 * @author rayhan
 */
public class ActionController extends HttpServlet {
    
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
        	
        }
        else if("View Driver".equals(action)) {
        	showDriverData(request, response, mongodbUtils);
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
    
}
