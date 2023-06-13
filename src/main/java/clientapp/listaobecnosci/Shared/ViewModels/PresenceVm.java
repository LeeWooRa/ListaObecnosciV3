package clientapp.listaobecnosci.Shared.ViewModels;

public class PresenceVm {
    private String studentIndex;

    private int periodId;
    private String status;

    private String firstName;
    private String lastName;

    public PresenceVm(String studentIndex, int periodId, String status, String firstName, String lastName) {
        this.studentIndex = studentIndex;
        this.periodId = periodId;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PresenceVm() {

    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
