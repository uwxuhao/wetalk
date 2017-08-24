package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        PrintWriter out = response.getWriter();
        if (username.equals("hao") && password.equals("asdf95")){
            out.print("ok");
            HttpSession session = request.getSession();
            session.setAttribute("first_name", "Hao");
            session.setAttribute("user_name", username);
        }
        else if(username.equals("test") && password.equals("asdf95")){
            out.print("ok");
            HttpSession session = request.getSession();
            session.setAttribute("first_name", "Tester");
            session.setAttribute("user_name", username);
        }

        else{
            out.print("false");
        }

    }


}
