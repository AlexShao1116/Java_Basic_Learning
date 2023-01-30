package 异常处理;

public class trycatch异常处理 {
    public static void main(String[] args) {
        //ctrl+alt+t调用try catch等套路
        //若代码块可能有多个异常，可用多个catch处理
        //要求子类异常写在父类异常前(否则由于已经弹出父类异常，子类异常不会弹出）
        //
        try {
            String str = "邵浩然";
            int a = Integer.parseInt(str);
            System.out.println("数字："+a);
        } catch (NumberFormatException e) {
            System.out.println("异常信息：" + e.getMessage());
        }finally {
            System.out.println("执行finally");
        }
        System.out.println("程序继续");
    }
}
