package sample.model;

import java.sql.Timestamp;

public class Task {

    private String taskName;
    private String taskDesc;
    private int userId;
    private int taskId;
    private Timestamp datecreated;

    public Task() {
    }

    public Task(Timestamp datecreated, String taskName, String taskDesc, int userId) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Timestamp getDatecreated() { return datecreated; }

    public void setDatecreated(Timestamp datecreated) { this.datecreated = datecreated; }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}