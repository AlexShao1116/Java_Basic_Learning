package 线程;

public class 线程死锁 {
    public static void main(String[] args) {
        Deadlock lock1 = new Deadlock(true);
        Deadlock lock2 = new Deadlock(false);
        lock1.start();
        lock2.start();
    }
}
class Deadlock extends Thread {
    boolean flag;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public Deadlock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println("Position 1");
                synchronized (o2) {
                    System.out.println("Position 2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println("Position 3");
                synchronized (o1) {
                    System.out.println("Position 4");
                }
            }
        }
    }
}
