package clientapp.listaobecnosci.Shared.ViewModels;

public class CreateStudentGroupVm {
    private String studentGroupName;

    public CreateStudentGroupVm() {
    }

    public CreateStudentGroupVm(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }

    public String getStudentGroupName() {
        return studentGroupName;
    }

    public void setStudentGroupName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }
}
