/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import test2.User;

/**
 * Web application lifecycle listener.
 *
 * @author K
 */
public class UserListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext sc = sce.getServletContext();
        
        ArrayList<User> userList = (ArrayList<User>) sc.getAttribute("users");
        
        if(userList == null){
            userList = new ArrayList<User>();
            sc.setAttribute("users", userList);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
