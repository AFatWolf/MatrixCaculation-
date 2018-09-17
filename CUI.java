import java.util.*;
import java.lang.*;


public class CUI {
    Matrix[] matrices;
    static final int numberOfMatrix = 5;
    // index of Matrix Ans which is saved after each calculation
    int matAns;
    Scanner in;
    // if we continue to promt user choice or not
    boolean signalToContinue;
    static final int numberOfOptions = 8;
    CUI(){
        matrices = new Matrix[numberOfMatrix];
        in = new Scanner(System.in);
        matAns = numberOfMatrix - 1;
        signalToContinue = true;
    }

    public static void debug(){
        System.out.println("------------------------Debug------------------------");
    }

    public static void debug(int i){
        System.out.println(new Integer(i).toString() + " ------------------------Debug------------------------");
    }

    public void greets(){
        System.out.println("Hi! This is an app to calculus matrix for no-brain schoolers.");
        System.out.println("Here are your choice:");
    }

    public void showOptions(){
        System.out.println("Options:");
        String[] options = {"Input matrix",
                            "View matrix (to review the data you input).",
                            "Copy one Matrix to another.",
                            "Apply Gauss Elimination.",
                            "Apply Gauss Jordan Ultimate Elimination.",
                            "Add Matrix.",
                            "Multiply Matrix.",
                            "End."
                            };
        for (int i = 0; i < options.length; i++){
            System.out.println(Integer.toString(i+1) + ". " + options[i]);
        }
    }
    // promt user choice
    public void promtChoice(){
        System.out.print("Made your choice using number from 1 to " + Integer.toString(numberOfOptions) + ": ");
        int choice;
        boolean done = false;
        while(!done){
            choice = in.nextInt();
            in.nextLine();
            //debug();
            switch(choice){
                //done = true;
                case 1:{
                    done = true;
                    input();
                    break;
                }
                case 2:{
                    done = true;
                    view();
                    break;
                }
                case 3:{
                    done = true;
                    assign();
                    break;
                }
                case 4:{
                    done = true;
                    applyGaussElimination();
                    break;
                }
                case 5:{
                    done = true;
                    applyGaussJordanElimination();
                    break;
                }
                case 6:{
                    done = true;
                    addMatrix();
                    break;
                }
                case 7:{
                    done = true;
                    multiplyMatrix();
                    break;
                }
                case 8:{
                    done = true;
                    end();
                    break;
                }
                default:{
                    done = false;
                    break;
                }
            }
        }
    }
    // check if a Matrix has not been initialized
    boolean checkEmptyMatrix(Matrix a){
        return (a == null);
    }
    // return the index of the Matrix user choose
    int chooseMatrix(){
        for(int i = 1; i < numberOfMatrix; i++){
            System.out.println(new Integer(i).toString() + ". Mat" + new Integer(i).toString());
        }
        System.out.println(new Integer(numberOfMatrix).toString() + ". MatAns");
        int choice = in.nextInt();
        in.nextLine();
        if(choice > numberOfMatrix || choice < 1){
            //System.out.println("Invaid choice");
            return -1;
        }
        //debug(1);
        // index in array
        return choice - 1;
    }
    // update data of a Matrix in memory
    private void inputMatrix(int choice){
        int numberOfRows = in.nextInt();
        int numberOfColumns = in.nextInt();
        in.nextLine();
        //debug(3);
        double[][] board = new double[numberOfRows][numberOfColumns];
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                board[i][j] = in.nextDouble();
            }
            in.nextLine();
        }
        matrices[choice] = new Matrix(board);
    }
    // take Matrix data
    // call inputMatrix(...)
    private void input(){
        System.out.println("Select one of the following Matrix to input data: ");
        int choice = chooseMatrix();
        if(choice == -1){
            System.out.println("Invalid index number.");
        }
        //debug(2);
        inputMatrix(choice);
    }  
    // review data of a matrix
    private void view(){
        System.out.println("Select one of the following Matrix to view data: ");
        int choice = chooseMatrix();
        if(checkEmptyMatrix(matrices[choice]))
            System.out.println("The Matrix has not been initialized");
        else if (choice == -1)
            System.out.println("Invalid choice number.");
        else
            matrices[choice].display();
    }
    // apply Gause Elimination

    // assign/duplicate a mtrix to another
    private void assign(){
        int sourceMatrix = chooseMatrix();
        int destinationMatrix = chooseMatrix();
        if(sourceMatrix == -1 || destinationMatrix == -1){
            System.out.println("Invalid index number");
            return;
        }
        if(checkEmptyMatrix(matrices[sourceMatrix])){
            System.out.println("The source Matrix cannot be null.");
            return;
        }
        matrices[destinationMatrix] = Matrix.duplicate(matrices[sourceMatrix]);
    }
    private void applyGaussElimination(){
        System.out.println("Select one of the following Matrix to apply Gause Elimination: ");
        int choice = chooseMatrix();
        if(choice == -1  ){
            System.out.println("Invalid choice number");
        }
        if (checkEmptyMatrix(matrices[choice])){
            System.out.println("The Matrix has not been initialized");
        }
        matrices[matAns] = matrices[choice].applyGaussElimination();
        matrices[matAns].display();
    }
    // apply Gauss - Jordan Elimination
    private void applyGaussJordanElimination(){
        System.out.println("Select one of the following Matrix to apply Gause Jordan Elimination: ");
        int choice = chooseMatrix();
        if(choice == -1  ){
            System.out.println("Invalid choice number");
        }
        if (checkEmptyMatrix(matrices[choice])){
            System.out.println("The Matrix has not been initialized");
        }
        matrices[matAns] = matrices[choice].applyGaussJordanElimination();
        matrices[matAns].display();
    }
    // add Matrix
    private void addMatrix(){
        int firstMatrix =  chooseMatrix();
        int secondMatrix = chooseMatrix();
        if(firstMatrix == -1 || checkEmptyMatrix(matrices[firstMatrix]) ){
            System.out.println("Invalid Matrix, either uninitialized or invalid number");
        }
        if(secondMatrix == -1 || checkEmptyMatrix(matrices[secondMatrix]) ){
            System.out.println("Invalid Matrix, either uninitialized or invalid number");
        }
        Matrix tempMatrix = matrices[firstMatrix].addMatrix(matrices[secondMatrix]);
        if(tempMatrix != null){
            matrices[matAns] = tempMatrix;
            matrices[matAns].display();
        }
    }
    // multiply Matrix
    private void multiplyMatrix(){
        int firstMatrix =  chooseMatrix();
        int secondMatrix = chooseMatrix();
        if(firstMatrix == -1 || checkEmptyMatrix(matrices[firstMatrix]) ){
            System.out.println("Invalid Matrix, either uninitialized or invalid number");
        }
        if(secondMatrix == -1 || checkEmptyMatrix(matrices[secondMatrix]) ){
            System.out.println("Invalid Matrix, either uninitialized or invalid number");
        }
        Matrix tempMatrix = matrices[firstMatrix].multiplyMatrix(matrices[secondMatrix]);
        if(tempMatrix != null){
            matrices[matAns] = tempMatrix;
            matrices[matAns].display();
        }
    }

    private void end(){
        in.close();
        signalToContinue = false;
    }

    public void _start(){
        greets();
        while(signalToContinue == true){
            showOptions();
            promtChoice();
        }
    }
    public static void main(String[] args){
        //CUI();
        new CUI()._start();
        //Matrix ret3 = mat.multiplyMatrix();
    }
}