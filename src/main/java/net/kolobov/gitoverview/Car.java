package net.kolobov.gitoverview;


public class Car extends BaseEntity implements Speakable {

    private String name;

    private Integer speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", speed=" + speed +
                '}';
    }

    public String makeNoise() {
        return "Car make: Br-br-br";
    }
}
