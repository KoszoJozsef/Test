<%-- 
    Document   : jsp
    Created on : 2017.08.07., 8:45:28
    Author     : K
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebPage</title>
    </head>
    <body>
        
        <div class="container">
            
            <div class="title" align='center'>Create an account</div>
        
            <%
                if(request.getAttribute("errors") != null){
            %>
            <fieldset>
                <legend align='center'>Errors</legend>
                <ul>
                <% if(request.getAttribute("username_error") != null){ %>
                    <li class="error">Username is required!</li>
                <% } %>
                <% if(request.getAttribute("password_error") != null){ %>
                    <li class="error">Password is required!</li>
                <% } %>
                <% if(request.getAttribute("password_match_error") != null){ %>
                    <li class="error">Password doesn't match!</li>
                <% } %>
                <% if(request.getAttribute("email_error") != null){ %>
                    <li class="error">Email is required!</li>
                <% } %>
                <% if(request.getAttribute("invalid_email_error") != null){ %>
                    <li class="error">Invalid email address!</li>
                <% } %>
                </ul>
            </fieldset>
            <% } %>
            
            <fieldset>
                <legend align='center'>Account details</legend>
        
        <form action="TestServlet" method="post">
            <div class="inputField" align='center'>
                <label for="username" class="inputField">Username: </label>
                <input name="username" type="text" value='<%= request.getAttribute("username") %>'></input>
            </div>
            <div class="inputField" align='center'>
                <label for="password" class="inputField">Password: </label>
                <input name="password" type="password"></input>
            </div>
            <div class="inputField" align='center'>
                <label for="passwordConfirm" class="inputField">Confirm password: </label>
                <input name="passwordConfirm" type="password"></input>
            </div>
            <div class="inputField" align='center'>
                <label for="email" class="inputField">Email: </label>
                <input name="email" type="text" value='<%= request.getAttribute("email") %>'></input>
            </div>
            <div class="inputField" id="submitField" align='center'>
                <input id="submitButton" type="submit" value="Submit"></input>
            </div>
        </form>
            </fieldset>  
        </div>
    </body>
</html>
