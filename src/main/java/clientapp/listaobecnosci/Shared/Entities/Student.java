package clientapp.listaobecnosci.Shared.Entities;


public class Student {
    private String firstName;
    private String lastName;

    private String studentIndex;
    private Integer groupId;

    public Student() {
    }

    public Student(String firstName, String lastName, String studentIndex, Integer groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentIndex = studentIndex;
        this.groupId = groupId;
    }

    // Getters and setters for the class variables

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}

