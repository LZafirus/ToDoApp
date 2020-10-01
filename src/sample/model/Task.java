package sample.model;

public class Task {

    private String taskDesc;
    private long dateCreated;

    public Task() {
    }

    public Task(String taskDesc, long dateCreated) {
        this.taskDesc = taskDesc;
        this.dateCreated = dateCreated;
    }

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
