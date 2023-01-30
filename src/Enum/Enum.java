package Enum;

public class Enum {
    public static void main(String[] args){
        Season spring = Season.SPRING;
        spring.get();

    }
}

enum Season{
    SPRING("春天","花开"),SUMMER("夏天","叶绿"),FALL("秋天","果收"),WINTER("冬天","树死");
    private String name;
    private String desc;
    private Season(String name,String desc){
            this.name = name;
            this.desc = desc;
    }
    public String get(){
       return name;
    }
}