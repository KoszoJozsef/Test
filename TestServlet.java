/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test2.User;

/**
 *
 * @author K
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("username", "");
        request.setAttribute("email", "");
        
        RequestDispatcher test3 = request.getRequestDispatcher("WEB-INF/test3/jsp.jsp");
           test3.forward(request, response);
    }

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
        
        request.setAttribute("errors", false);
        
        User u = new User();
        
        String username = request.getParameter("username");
        if(username.length() == 0){
            System.out.println("Username is required!");
            request.setAttribute("errors", true);
            request.setAttribute("username_error", true);
            request.setAttribute("username", "");
        } else {
            u.setUsername(username);
            request.setAttribute("username", username);
        }
        
        String password = request.getParameter("password");
        
        if(password.length() == 0){
            request.setAttribute("errors", true);
            request.setAttribute("password_error", true);
        } else {
            u.setPassword(password);
        }
        
        String passwordConfirm = request.getParameter("passwordConfirm");
        if(passwordConfirm.length() == 0){
            request.setAttribute("errors", true);
            request.setAttribute("password_error", true);
        } else {
            u.setPassword(passwordConfirm);
        }
        
        if(password.equals(passwordConfirm)){
            u.setPassword(password);
        } else {
            request.setAttribute("errors", true);
            request.setAttribute("password_match_error", true);
        }
        
        String email = request.getParameter("email");
            
        String pattern = "^[A-Za-z]*\\@[a-z]*\\.[a-z]{2,4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        
        if(m.find()){
            u.setEmail(email);
            request.setAttribute("email", email);
        } else if (email.length() == 0){
            request.setAttribute("errors", true);
            request.setAttribute("email_error", true);
            request.setAttribute("email", "");
        } else {
            request.setAttribute("errors", true);
            request.setAttribute("invalid_email_error", true);
            request.setAttribute("email", "");
        }
        
        if((Boolean)request.getAttribute("errors")){
            
             RequestDispatcher test3 = request.getRequestDispatcher("WEB-INF/test3/jsp.jsp");
             test3.forward(request, response);
             
        } else {
            
            ServletContext sc = this.getServletContext();
            
            synchronized(this){
                ArrayList<User> userList = (ArrayList<User>) sc.getAttribute("users");
                userList.add(u);
                sc.setAttribute("users", userList);
            }
            response.sendRedirect("");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
