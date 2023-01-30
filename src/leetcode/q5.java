package leetcode;




public class q5 {
    public static String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }else {
            String[][] M = Matrix(s, numRows);
            String result = myString(M);
            return result;
        }

    }
    public static String[][] Matrix(String s, int numRows){
        String[] array = s.split("");
        int length = array.length;
        int x = 0;
        int count = 0;
        if (length%(numRows*2-2) != 0){
            String[] myArray = new String[length+((numRows*2-2)-(length%(numRows*2-2)))];
            for(int i = 0; i < length; i++){
                myArray[i] = array[i];
            }
            array = myArray;
        }
        int length1 = array.length;
        int row = (length1/(numRows*2-2))*(numRows-1);
        String[][] matrix = new String[numRows][row];
        while(x<=row){
            if(count >= length1){
                break;
            }
            if (x % (numRows-1) == 0) {
                for (int y = 0;y < numRows;y++){
                    if(count >= length1){
                        break;
                    }
                    matrix[y][x] = array[count];
                    count++;
                }
                x++;
            }
            while(x % (numRows-1) != 0){
                if(count >= length1){
                    break;
                }
                matrix[numRows-(x % (numRows-1))-1][x] = array[count];
                count++;
                x++;
            }
        }
        return matrix;
    }
    public static String myString(String[][] matrix){
        String[] st = new String[matrix.length*matrix[0].length];
        int c = 0;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                st[c] = matrix[i][j];
                c++;
            }

        }
        String nospace1 = String.join("",st);
        String nospace = (nospace1.replaceAll("null",""));

        return nospace;
    }
    public static void main(String[] args) {

        System.out.println(convert("qweqweP", 1));

    }
}
