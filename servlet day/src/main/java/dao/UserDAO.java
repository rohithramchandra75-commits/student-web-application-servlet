package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import utility.DBConnection;

public class UserDAO {

    // =========================
    // Register User
    // =========================
    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database Connection Failed!");
                return false;
            }

            System.out.println("Database Connected Successfully");

            String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            System.out.println("Executing INSERT Query...");

            int rows = ps.executeUpdate();

            System.out.println("Rows Inserted : " + rows);

            if (rows > 0) {
                status = true;
                System.out.println("User Registered Successfully");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Registration Error");
            e.printStackTrace();

        }

        return status;
    }

    // =========================
    // Login User
    // =========================
    public User loginUser(String email, String password) {

        User user = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                System.out.println("Login Successful");

            } else {

                System.out.println("Invalid Email or Password");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Login Error");
            e.printStackTrace();

        }

        return user;
    }

}