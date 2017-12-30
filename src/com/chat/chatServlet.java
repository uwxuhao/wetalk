package com.chat;

import com.google.gson.Gson;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.database.user_listDb;

@WebServlet(name = "chatServlet")
public class chatServlet extends HttpServlet {
    @Resource(name = "jdbc/wetalk")
    private DataSource dataSource;

    Map<String, List<conversationStruct>> conversions = new HashMap<>();
    Map<String, Map<String, String>> unread = new HashMap<>();
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
        String type = request.getParameter("post_type");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String key = "";
        if (from.compareTo(to) < 0)
            key = from + "," + to;
        else
            key = to + "," + from;


        if (type.equals("get_message")) {
            PrintWriter out = response.getWriter();
            if (conversions.containsKey(key)) {
                for (conversationStruct conversion : conversions.get(key)) {
                    out.println(conversion.toString());
                }
            }
        } else if (type.equals("send_message")) {
            String content = request.getParameter("content");
            conversationStruct conversion =
                    new conversationStruct(content, from, to);
            System.out.println(from + " -> " + to + ": " + content);
            String[] users = key.split(",");
            String user1 = users[0];
            String user2 = users[1];
            dbUtil.storeHistory(user1, user2, from + ": " + content);
            if (!conversions.containsKey(key)) {
                List<conversationStruct> list = new ArrayList<>();
                list.add(conversion);
                conversions.put(key, list);
            } else {
                conversions.get(key).add(conversion);
            }

            // add to the unread map
            if (!unread.containsKey(to)) {
                Map<String, String> message_count = new HashMap<>();
                message_count.put(from, "1");
                unread.put(to, message_count);
            } else {
                Map<String, String> message_count = unread.get(to);
                if (!message_count.containsKey(from))
                    message_count.put(from, "1");
                else
                    message_count.put(from, String.valueOf(Integer.valueOf(message_count.get(from)) + 1));
            }
        } else if (type.equals("check_unread")) {
            String unreadJson = new Gson().toJson(unread.get(to));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(unreadJson);
        } else if (type.equals("initialize")) {
            //TODO
        }

    }

    @Override
    public void destroy() {
        System.out.println("destroy");
        super.destroy();
    }
}
