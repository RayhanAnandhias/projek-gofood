/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            boolean result = mongodbUtils.validateUser(email,pwd);
            if(result) {
                request.getRequestDispatcher("/menu.jsp")
                    .forward(request, response);
            }else {
                request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            }
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
