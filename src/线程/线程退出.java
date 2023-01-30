package 线程;

public class 线程退出 {
    public static void main(String[] args) throws InterruptedException {
        T thread = new T();
        thread.start();
        System.out.println("主线程休眠十秒");
        //想要退出线程，需要修改while的判定结果
        //因此，先使主线程休眠，当主线程开始运作时修改loop
        //主线程休眠时间就是子线程运行时间
        Thread.sleep(10*1000);
        thread.setloop(false);

    }
}
class T extends Thread{
    private int count = 0;
    private boolean loop = true;
    @Override
    public void run(){
        while(loop){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程运行"+(++count)+"秒");
        }
    }
    public void setloop(boolean x){
        this.loop = x;
    }
}
