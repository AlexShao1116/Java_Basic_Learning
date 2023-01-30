package Enum;

public class enum2 {
    public static void main(String[] args){
        Season fall = Season.FALL;
        //输出枚举对象名称
        System.out.println(fall.name());
        //输出枚举对象次序（从零开始计算）
        System.out.println(fall.ordinal());
        Season[] values = Season.values();
        for(Season ele : values){
            System.out.println(ele.get());
        }
    }
}
