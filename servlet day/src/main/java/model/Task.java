package model;

public class Task {

    private int id;
    private int userId;
    private String taskName;

    // Default Constructor
    public Task() {

    }

    // Parameterized Constructor
    public Task(int id, int userId, String taskName) {
        this.id = id;
        this.userId = userId;
        this.taskName = taskName;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for User ID
    public int getUserId() {
        return userId;
    }

    // Setter for User ID
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter for Task Name
    public String getTaskName() {
        return taskName;
    }

    // Setter for Task Name
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}