package 类作业测试;

public class Q5 {
    public static void main(String[] args) {
        A test = new A();
        test.getB();
    }
}
class A{
    private  String name = "Bob";
    public void getB(){
        class B{
            private  final String name = "James";
            public void show(){
                System.out.println(name + A.this.name);
            }
        }
        B gg = new B();
        gg.show();


    }
}