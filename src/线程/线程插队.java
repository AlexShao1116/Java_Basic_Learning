package 线程;

public class 线程插队 {
    public static void main(String[] args) throws InterruptedException {
        Son son = new Son();
        son.start();
        son.setPriority(Thread.MIN_PRIORITY);
        for(int i = 1;i<10;i++) {
            System.out.println("儿子吃包子"+i);
            Thread.sleep(1000);
            if(i == 5) {
                System.out.println("爸爸先吃");
                son.join();
            }
        }
    }
}
class Son extends Thread{
    private int count = 0;
    @Override
    public void run(){
            for (int i= 1;i<15;i++) {
                System.out.println("爸爸吃包子"+(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

}
