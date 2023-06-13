package clientapp.listaobecnosci.Shared.ViewModels;

public class StudentToGroupVm {
    private Integer groupId;
    private String studentIndex;

    public StudentToGroupVm(Integer groupId, String studentIndex){
        this.groupId = groupId;
        this.studentIndex = studentIndex;
    }
    public StudentToGroupVm(){

    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getStudentIndex() {
        return studentIndex;
    }
}
