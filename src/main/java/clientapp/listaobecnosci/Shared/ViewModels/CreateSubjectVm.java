package clientapp.listaobecnosci.Shared.ViewModels;

public class CreateSubjectVm {
    private String subjectName;

    public CreateSubjectVm() {
    }

    public CreateSubjectVm(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
