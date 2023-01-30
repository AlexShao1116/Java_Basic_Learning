package 泛类;

import java.util.ArrayList;
import java.util.Comparator;

public class Generic_Exercise_1 {
    public static void main(String[] args) {
        ArrayList<Employee> gg = new ArrayList<>();
        gg.add(new Employee("Haoran",2000,new MyDate(2001,11,16)));
        gg.add(new Employee("Haoran",3000,new MyDate(2000,11,16)));
        gg.add(new Employee("Haoqi",2000,new MyDate(2000,11,16)));
        System.out.println(gg);
        gg.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int i = o1.getName().compareTo(o2.getName());
                if(i != 0){
                    return i;
                }
                int year = o1.getBirthday().getYear() - o2.getBirthday().getYear();
                if(year != 0){
                    return year;
                }
                int month = o1.getBirthday().getMonth() - o2.getBirthday().getMonth();
                if(month != 0){
                    return year;
                }
                return o1.getBirthday().getDay() - o2.getBirthday().getDay();
            }
        });
      System.out.println(gg);
    }

}
