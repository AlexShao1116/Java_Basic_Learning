package 线程;

public class 线程中断 {
    public static void main(String[] args) throws InterruptedException {
        Inter t = new Inter();
        t.start();
        Thread.sleep(10000);
        t.interrupt();
    }
}

class Inter extends Thread{
    private int count = 0;
    @Override
    public void run(){

        while (true) {
            for (int i = 1;i < 8;i++){
                System.out.println("浩然吃了"+(i)+"个包子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("浩然喝了口粥，继续开始吃");
                }
            }

            try {
                System.out.println("浩然吃顶了");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println("浩然喝了口粥，继续开始吃");
            }
        }
    }


}