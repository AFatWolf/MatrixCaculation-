import java.util.*;

public class Test {
    public static void debug(){
        System.out.println("--------------------------Debug");
    }

    public static void main(String[] args){
        Matrix mat;
        int numberOfRows,numberOfColumns;
        double[][] board;
        Scanner in = new Scanner(System.in);
        numberOfRows = in.nextInt();
        numberOfColumns = in.nextInt();
        in.nextLine();
        board = new double[numberOfRows][numberOfColumns];
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                board[i][j] = in.nextDouble();
            }
            in.nextLine();
        }
        System.out.println("Hello darkness");
        mat = new Matrix(board);
        Matrix ret = mat.applyGaussElimination();
        ret.display();
        //ret.displayInt();
        //mat.display();
        Matrix ret2 = mat.applyGaussJordanElimination();
        ret2.display();
    }
}