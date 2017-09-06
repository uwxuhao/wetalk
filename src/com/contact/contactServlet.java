package com.contact;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import com.database.user_listDb;

@WebServlet(name = "contactServlet")
public class contactServlet extends HttpServlet {
    @Resource(name = "jdbc/wetalk")
    private DataSource dataSource;
    user_listDb dbUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dbUtil = new user_listDb(dataSource);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        System.out.println("username: " + username);
        String type = (String) request.getParameter("type");

        if(type.equals("get contact list")){
            List<String> contactList = dbUtil.getContactList(username);
            System.out.println(contactList);
            String contactJson = new Gson().toJson(contactList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(contactJson);
        }
        else if(type.equals("add contact")){
            String contactUserName = (String) request.getParameter("contact");
            String result = dbUtil.addContact(username, contactUserName);
            PrintWriter out = response.getWriter();
            out.print(result);
        }

    }

}
