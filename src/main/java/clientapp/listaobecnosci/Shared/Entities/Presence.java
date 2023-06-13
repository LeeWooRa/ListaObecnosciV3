package clientapp.listaobecnosci.Shared.Entities;

public class Presence {
    private Integer presenceId;

    private String studentIndex;

    private int periodId;
    private String status;

    public Presence() {
    }

    public Presence(Integer presenceId, String studentIndex, int periodId, String status) {
        this.presenceId = presenceId;
        this.studentIndex = studentIndex;
        this.periodId = periodId;
        this.status = status;
    }

    // Getters and setters for the class variables

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(Integer presenceId) {
        this.presenceId = presenceId;
    }
}

