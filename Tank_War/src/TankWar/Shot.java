package TankWar;

public class Shot extends Thread{
    int x;
    int y;
    int dir = 0;
    int speed = 5 ;
    boolean isLive = true;
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(dir){
                case(0)://向上
                    y = y-speed;
                    break;
                case(2)://向下
                    y = y+speed;
                    break;
                case(3)://向左
                    x = x-speed;
                    break;
                case(1)://向右
                    x = x+speed;
                    break;
            }
            if( x >= 1000 && x <= 0 && y >= 750 && y <= 0 ){
                isLive = false;
                break;
            }
        }
    }
    public Shot(int x,int y,int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
