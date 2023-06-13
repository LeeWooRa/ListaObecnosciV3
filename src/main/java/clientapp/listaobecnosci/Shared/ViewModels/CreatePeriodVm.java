package clientapp.listaobecnosci.Shared.ViewModels;

import java.sql.Date;
import java.sql.Time;

public class CreatePeriodVm {
    private Date date;
    private Time startTime;
    private Time endTime;
    private int groupId;
    private int subjectId;

    public CreatePeriodVm() {
    }

    public CreatePeriodVm(Date date, Time startTime, Time endTime, int groupId, int subjectId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
