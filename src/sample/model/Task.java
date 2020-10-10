package sample.model;

public class Task {

    private String taskName;
    private String taskDesc;
    private long dateCreated;

    public Task() {
    }

    public Task(String taskName, String taskDesc, long dateCreated) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.dateCreated = dateCreated;
    }

    public String getTaskName() { return taskName; }

    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
}