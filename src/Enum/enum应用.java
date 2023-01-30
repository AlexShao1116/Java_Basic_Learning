package Enum;

    public enum enum应用 {
        SUN(0),MON(1),TUS(2),WED(3),THU(4),FRI(5),SAT(6);

        private int value;

        private enum应用(int value){
            this.value = value;
        }

        public static enum应用 getNextDay(enum应用 nowDay){
            int nextDayValue = nowDay.value;

            if (++nextDayValue == 7){
                nextDayValue =0;
            }

            return getWeekdayByValue(nextDayValue);
        }

        public static enum应用 getWeekdayByValue(int value) {
            for (enum应用 c : enum应用.values()) {
                if (c.value == value) {
                    return c;
                }
            }
            return null;
        }
    }

    class Test2 {
        public static void main(String[] args) {
            System.out.println("nowday ====> " + enum应用.SAT);
            System.out.println("nowday int ====> " + enum应用.SAT.ordinal());
            System.out.println("nextday ====> " + enum应用.getNextDay(enum应用.SAT)); // 输出 SUN

        }
    }
