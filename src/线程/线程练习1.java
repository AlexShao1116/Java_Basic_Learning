package 线程;

public class 线程练习1 {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        cat.start();
        for(int j = 0;j<50;j++){
            System.out.println("主线造成不会阻塞"+j);
            Thread.sleep(1000);
        }
    }
}
class Cat extends Thread{

    @Override
    public void run(){
        int times = 0;
        while (times < 80) {
            System.out.println("喵喵"+(++times));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

