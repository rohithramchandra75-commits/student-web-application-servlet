package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Task;
import utility.DBConnection;

public class TaskDAO {

    // Add Task
    public boolean addTask(Task task) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO tasks(user_id, task_name) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, task.getUserId());
            ps.setString(2, task.getTaskName());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // View Tasks
    public ArrayList<Task> getTasks(int userId) {

        ArrayList<Task> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM tasks WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTaskName(rs.getString("task_name"));

                list.add(task);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete Task
    public boolean deleteTask(int id) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM tasks WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}