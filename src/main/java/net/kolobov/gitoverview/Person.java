package net.kolobov.gitoverview;


public class Person extends BaseEntity implements Speakable, Moveble {
    private String firstName;
    private String lastname;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + super.getId() +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String makeNoise() {
        return isOld() ? "Person make: Hello! Glad to see you!" : "Mama!";
    }

    public String moveWithSpeed() {
        return isOld() ? "5 m\\h" : "0.2 m\\h";
    }
}
