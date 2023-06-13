package clientapp.listaobecnosci.Shared.ViewModels;

public class GetPeriodsListVm {
    private Integer groupId;

    private Integer subjectId;

    public GetPeriodsListVm(Integer groupId, Integer subjectId) {
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    public GetPeriodsListVm() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
