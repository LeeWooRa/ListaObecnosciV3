package clientapp.listaobecnosci.Shared.Entities;

import java.sql.Date;
import java.sql.Time;

public class Period {

    private Integer periodId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer groupId;
    private Integer subjectId;

    public Period() {
    }

    public Period(Date date, Time startTime, Time endTime, Integer groupId, Integer subjectId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    public Period(Integer periodId, Date date, Time startTime, Time endTime, Integer groupId, Integer subjectId) {
        this.periodId = periodId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    // Getters and setters for the class variables

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
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
