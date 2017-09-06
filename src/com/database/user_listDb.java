package com.database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class user_listDb {
    private DataSource dataSource;
    private String userRelationshipTable = "wetalk.user_relationship";

    public user_listDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getContactList(String userName) {
        System.out.println("userName: " + userName);
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        List<String> res = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT user1, user2 FROM " + userRelationshipTable +
                    " WHERE " + "user1 =\"" + userName + "\" OR " +
                    "user2 =\"" + userName + "\"";
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

    public String addContact(String self, String contactUserName) {
        if (self.equals(contactUserName)) {
            return "You cannot add yourself.";
        }
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT user1, user2 FROM " + userRelationshipTable +
                    " WHERE " + "user1 =\"" + self + "\" AND " +
                    "user2 =\"" + contactUserName + "\" OR  " +

                    "user1 =\"" + contactUserName + "\" AND " +
                    "user2 =\"" + self + "\"";
            System.out.println(sql);
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                return "Already in contact list";
            }

            sql = "INSERT INTO " + userRelationshipTable + "(user1, user2)" +
                    "VALUES (?, ?)";
            PreparedStatement insertStat = conn.prepareStatement(sql);
            insertStat.setString(1, self);
            insertStat.setString(2, contactUserName);
            insertStat.execute();
            return "Success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL error";
        }
    }
}
