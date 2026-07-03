package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Registration;
import utility.DBConnection;

public class RegistrationDAO {

    public boolean registerCourse(Registration reg) {

        boolean status = false;

        try {

            System.out.println("===== DAO START =====");

            Connection con = DBConnection.getConnection();

            System.out.println("Connection = " + con);

            String sql = "INSERT INTO registrations(name,email,phone,gender,course,skills) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.println(reg.getName());
            System.out.println(reg.getEmail());
            System.out.println(reg.getPhone());
            System.out.println(reg.getGender());
            System.out.println(reg.getCourse());
            System.out.println(reg.getSkills());

            ps.setString(1, reg.getName());
            ps.setString(2, reg.getEmail());
            ps.setString(3, reg.getPhone());
            ps.setString(4, reg.getGender());
            ps.setString(5, reg.getCourse());
            ps.setString(6, reg.getSkills());

            int row = ps.executeUpdate();

            System.out.println("Rows Inserted = " + row);

            if (row > 0) {
                status = true;
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
            e.printStackTrace();
        }

        return status;
    }
}