package com.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class loginDb {
    private DataSource dataSource;
    private String loginTable = "wetalk.login_information";

    public loginDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean comparePassword(String username, String password) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT credential FROM " + loginTable + " WHERE "
                    + "username =\"" + username + "\"";
            System.out.println("the sql: " + sql);
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String actual_password = rs.getString("credential");
                System.out.println("actual: " + actual_password);
                if (actual_password.equals(password)) return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
