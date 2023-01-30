package 线程;

public class 线程练习3 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
    }
}
class gg implements Runnable{
    private Runnable target = null;
    @Override
    public void run() {
        while(target != null){
            target.run();
        }
    }
    public gg(Runnable x){
        target = x;
    }
    public void start(){
        start0();
    }
    public void start0(){
        run();
    }


}
class T1 implements Runnable{

    @Override
    public void run() {
        int count = 0;
        while(count < 10){
            System.out.println("红萝卜蹲");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            System.out.println("红萝卜蹲");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException a) {
                    a.printStackTrace();
            System.out.println("红萝卜蹲完" + (++count));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException b) {
                b.printStackTrace();
            }
        }
    }
}}}
class T2 implements Runnable{

    @Override
    public void run() {
        int count = 0;
        while(count < 10){
            System.out.println("粉萝卜蹲");
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("粉萝卜蹲");
            try {
                Thread.sleep(1001);
            } catch (InterruptedException c) {
                c.printStackTrace();
            }
            System.out.println("粉萝卜蹲完" + (++count));
            try {
                Thread.sleep(1001);
            } catch (InterruptedException d) {
                d.printStackTrace();
            }
        }
    }
}