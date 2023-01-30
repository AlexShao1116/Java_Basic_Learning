package 异常处理;

public class CostomExeption {
    public static void main(String[] args) {
        int a = 180;
        if(!(a >120)){
            throw new RangeExeption("请控制参数范围大于120");
        }
        System.out.println("输出成功");
        System.out.println(20%3);
    }
}
class RangeExeption extends RuntimeException{
    public RangeExeption(String message){
        super(message);
    }

}