package com.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class user_listDb {
    private DataSource dataSource;
    private String userRelationshipTable = "wetalk.user_relationship";

    public user_listDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getContactList(String userName) {
        System.out.println("userName: " +userName);
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        List<String> res = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT user1, user2 FROM " + userRelationshipTable +
                    " WHERE " + "user1 =\"" + userName + "\" or " +
                    "user2 =\"" + userName + "\"";
            System.out.println("the sql: " + sql);
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String user1 = rs.getString("user1");
                String user2 = rs.getString("user2");
                String anotherUser = "";
                if (user1.equals(userName))
                    anotherUser = user2;
                else
                    anotherUser = user1;
                res.add(anotherUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            return res;
        }
    }
}
