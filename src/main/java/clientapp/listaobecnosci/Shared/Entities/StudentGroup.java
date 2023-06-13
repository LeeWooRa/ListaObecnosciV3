package clientapp.listaobecnosci.Shared.Entities;


public class StudentGroup {
    private Integer groupId;
    private String groupName;

    public StudentGroup() {
    }

    public StudentGroup(String groupName) {
        this.groupName = groupName;
    }

    public StudentGroup(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    // Getters and setters for the class variables

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
