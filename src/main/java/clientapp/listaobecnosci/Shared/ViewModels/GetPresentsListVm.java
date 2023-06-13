package clientapp.listaobecnosci.Shared.ViewModels;

public class GetPresentsListVm {

    private Integer groupId;

    private Integer periodId;

    public GetPresentsListVm(Integer groupId, Integer periodId) {
        this.groupId = groupId;
        this.periodId = periodId;
    }

    public GetPresentsListVm() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }
}
