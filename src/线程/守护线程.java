package 线程;

public class 守护线程 {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread thread = new MyDaemonThread();
        thread.setDaemon(true);//守护线程定义为当所有线程结束时守护线程也结束
        thread.start();
        for(int i =1;i<=5;i++){
            System.out.println("老师在讲课");
            Thread.sleep(1000);
            if(i == 5){
                System.out.println("老师不讲课");
            }
        }
        /*Thread.sleep(5000);//必须完全结束进程，休眠并不能结束守护进程。。。
        for(int i =1;i<=3;i++){
            System.out.println("老师在讲课");
            Thread.sleep(1000);
            if(i == 3){
                System.out.println("老师不讲课");
            }
        }*/
    }
}
class MyDaemonThread extends Thread{
    @Override
    public void run(){
        for(; ;){
            System.out.println("学生在接下茬");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

