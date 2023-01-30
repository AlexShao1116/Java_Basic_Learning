package 泛类;

public class Employee {
    private int sal;
    private String name;
    private MyDate birthday;

    public void setSal(int sal) {
        this.sal = sal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public int getSal() {
        return sal;
    }

    public String getName() {
        return name;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public Employee( String name, int sal,MyDate birthday) {
            this.sal = sal;
            this.name = name;
            this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "sal=" + sal +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
