package 线程;

public class 线程练习2 {
    public static void main(String[] args) {
        Dog t2 = new Dog();
        //t2.start(); 无法调用
        T3 thread = new T3(t2);
        thread.start();
    }
}
class Animal{}
class Dog extends Animal implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }
}
class T3 implements Runnable{
    private Runnable target = null;
    @Override
    public void run() {
        if(target != null){
            target.run();
        }
    }
    public T3(Runnable x){
        target = x;
    }
    public void start(){
        start0();
    }
    public void start0(){
        run();
    }

}