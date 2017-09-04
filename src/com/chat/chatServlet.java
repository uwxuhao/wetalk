package com.chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "chatServlet")
public class chatServlet extends HttpServlet {

    Map<String, List<conversationStruct>> conversions = new HashMap<>();

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
            if (!conversions.containsKey(key)) {
                List<conversationStruct> list = new ArrayList<>();
                list.add(conversion);
                conversions.put(key, list);
            } else {
                conversions.get(key).add(conversion);
            }


        } else if (type.equals("check_other_contact")) {
            

        } else if (type.equals("initialize")) {
            //TODO
        }

    }

}
