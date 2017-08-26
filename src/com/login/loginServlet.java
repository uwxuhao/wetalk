package com.login;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import com.database.loginDb;

@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    @Resource(name = "jdbc/wetalk")
    private DataSource dataSource;
    loginDb dbUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            dbUtil = new loginDb(dataSource);
        }
        catch (Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        PrintWriter out = response.getWriter();
        if (dbUtil.comparePassword(username ,password)){
            out.print("ok");
            HttpSession session = request.getSession();
            session.setAttribute("user_name", username);
        }
        else{
            out.print("false");
        }
    }
}
