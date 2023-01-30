package 类作业测试;

public class Q6 {
    public static void main(String[] args) {
            Person tang = new Person("唐僧");
            tang.reg();
            tang.passwater();
    }
}
interface Vehicle{
        void work();
}
class Factory{
    public static Horse useHorse(){
        return new Horse();
    }
    public static Boat useBoat(){
        return new Boat();
    }
}
class Horse implements Vehicle{
    public void work(){
        System.out.println("骑白龙马");
    }
}
class Boat implements Vehicle{
    public void work(){
        System.out.println("坐大乌龟");
    }
}
class Person{
    private String name = "唐僧";
    private static Vehicle vehicle =null;
    public Person(String name){
        this.name = name;
    }

    public void reg(){
        this.vehicle = Factory.useHorse();
        System.out.print(name);
        vehicle.work();
    }
    public void passwater(){
        this.vehicle = Factory.useBoat();
        System.out.print(name);
        vehicle.work();
    }
}