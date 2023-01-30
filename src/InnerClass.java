public class InnerClass {
    public static void main(String[] args) {
            test t1 = new test("gg");
            t1.inner();
            f1(new IA(){
                @Override
                public void cry(){
                    System.out.println("经典main里创建匿名内部类");
                }
            });
            f1(new tiger());
            Cellphone cellphone = new Cellphone();
            cellphone.alarmclock(new Bell(){
                @Override
                public void ring() {
                    System.out.println("main里面写匿名内部类override interface method");
                }
            });

            test.inner2 test2 = t1.new inner2();
            test2.describe();
            t1.getinner2();
    }
    public static void f1(IA ia){
        ia.cry();
    }

}
class test {
    private String name;
    private int n1 = 300;

    public test(String name) {
        this.name = name;
    }
    class inner2{
        public void describe(){
            System.out.println("外部类调用成员内部类方法1");
        }
    }
    public inner2 getinner2(){
        System.out.println("外部类调用成员内部类方法2");
        return new inner2();


    }
    public void inner() {
        /*class test2 {
            private int n1 = 200;

            public void ok() {
                System.out.println(n1 + "out" + test.this.n1);
            }
        }*/
        new IA() {
            @Override
            public void cry() {
                System.out.println("在局部内部类里调用并重写interface方法"+test.this.n1);
            }
        }.cry();

    }

    @Override
    public String toString() {
        return "test{" +
                "name='" + name + '\'' +
                '}';
    }
}
interface IA{
    public int n1 = 20;
    default public void cry(){
        System.out.println("wuwu");
    }
}
class tiger implements IA{
    public int n1 = 30;

    @Override
    public void cry(){
        System.out.println("经典外部类调用并重写interface方法" + IA.n1);
    }

}
interface Bell{
    default void ring(){
        System.out.println("上课了！！");
    }
}
class Cellphone{
    public static void alarmclock(Bell bell){
        bell.ring();
    }

}


