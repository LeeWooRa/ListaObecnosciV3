package clientapp.listaobecnosci.Shared.ViewModels;

public class CreateStudentVm {
    private String firstName;
    private String lastName;
    private String studentIndex;

    public CreateStudentVm() {
    }

    public CreateStudentVm(String firstName, String lastName, String studentIndex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentIndex = studentIndex;
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

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }
}
