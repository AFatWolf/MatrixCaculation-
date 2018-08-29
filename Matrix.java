import java.util.*;

public class Matrix {
    private int numberOfRows, numberOfColumns;
    // we will display matrix as a set of rows for now
    private Row[] rows;
    private double[][] board;

    Matrix(double[][] board){
        numberOfRows = board.length;
        numberOfColumns = board[0].length;
        this.board = board;

        rows = new Row[numberOfRows];
        for(int i = 0; i < numberOfRows;i++){
            rows[i] = new Row(board[i]);
        }
    }

    // swap 2 rows with firstRow and secondRow position
    public void swapRows(int firstRow, int secondRow){
        Row middle = rows[firstRow];
        rows[firstRow] = rows[secondRow];
        rows[secondRow] = middle;
    }

    // scale a whole row by a scalar
    public void scale(int position, double scalar){
        rows[position].selfScale(scalar);
    }
    
    // add the row[dest] with row[source] and assign to row[dest]
    public void add(int dest, int source){
        rows[dest] = rows[dest].add(rows[source]);
    }

    // row[dest] = row[dest] + row[source] * scalar
    public void pivot(int dest, int source, double scalar){
        rows[dest] = rows[dest].pivot(rows[source],scalar);
    }
    // row[dest] = row[dest] * scalarOfDest + row[source] * scalarOfSource
    public void pivot(int dest, double scalarOfDest, int source, double scalarOfSource){
        rows[dest].selfScale(scalarOfDest);
        pivot(dest,source,scalarOfSource);
    }

    // sort the row with the criteria that: the row with leading one being on the most left
    // will be on top
    // implement merge sort
    public void sortRows(){
        int size = this.numberOfRows;
        sortHelper(this.rows, 0, size - 1);    
    }
    // used to split for sort
    private void sortHelper(Row[] rows, int lo, int hi){
        if(lo < hi){
            int mid = (lo + hi) >> 1;
            sortHelper(rows, lo, mid);
            sortHelper(rows, mid + 1, lo);
            sortMergeTool(rows, lo, hi);
        }
    }
    // used to merge 
    private void sortMergeTool(Row[] rows, int lo, int hi){
        Row[] temp = new Row[hi - lo + 1];
        int fHalf = lo;
        int mid = (lo + hi) >> 1;
        int sHalf = mid  + 1;
        int posOfTemp = 0;
        //System.out.println("lo " + lo + " hi " + hi);
        while(fHalf <= mid && sHalf <= hi){
            //System.out.println("fHalf " + fHalf + " sHalf " + sHalf);
            if(rows[fHalf].isToTheLeftOf(rows[sHalf])){
                temp[posOfTemp++] = rows[fHalf++];
            }
            else{
                temp[posOfTemp++] = rows[sHalf++];
            }
        }
        while(fHalf <= mid){
            temp[posOfTemp++] = rows[fHalf++];
        }
        while(sHalf <= hi){
            temp[posOfTemp++] = rows[sHalf++];
        }
        //System.out.println("Temp: " + posOfTemp);
        for(int i = 0; i < posOfTemp; i++){
            rows[lo + i] = temp[i];
            //temp[i].display();
        }

    }

    public Matrix applyGaussElimination(){
        Matrix ret = new Matrix(this.board);
        ret.sortRows();
        
        // pivot 
        // eg: [5 2 3] and [4 5 6]
        // we have to eliminate the 4
        // so we scale: [20 8 12] and [20 25 30]
        // then eliminate: [20 8 12] and [0 -17 -18]
        for(int i = 0; i < ret.rows.length;i++){
            // 4 in the above example
            
            for(int j = i; j < ret.rows.length;j++){
                double down = ret.rows[j].valueAt(ret.rows[i].positionOfLeadingOne);
                if(down != 0){
                // 5 in the above example
                  double up = ret.rows[i].valueAt(ret.rows[i].positionOfLeadingOne);
                  // -down to eliminate
                  ret.pivot(j,up,i,-down);
                  ret.rows[j].updateLeadingOne();
                 }
                 
            }
        }
        for(int i = 0; i < ret.rows.length; i++){
            // scale every thing down by the first element
            ret.scale(i, (double) (1.0/ret.rows[i].firstValue()) );
        }
        return ret;
    }

    public Matrix applyGaussJordanElimination(){
        Matrix ret = new Matrix(this.board);
        ret.sortRows();
        
        // pivot 
        // eg: [5 2 3] and [4 5 6]
        // we have to eliminate the 4
        // so we scale: [20 8 12] and [20 25 30]
        // then eliminate: [20 8 12] and [0 -17 -18]
        for(int i = 0; i < ret.rows.length;i++){
            // 4 in the above example
            
            for(int j = i; j < ret.rows.length;j++){
                double down = ret.rows[j].valueAt(ret.rows[i].positionOfLeadingOne);
                if(down != 0){
                // 5 in the above example
                  double up = ret.rows[i].valueAt(ret.rows[i].positionOfLeadingOne);
                  // -down to eliminate
                  ret.pivot(j,up,i,-down);
                  ret.rows[j].updateLeadingOne();
                 }
                 
            }
        }
        // Here comes real GaussJordan
        for(int i = ret.rows.length - 1; i >= 0;i--){
            // 4 in the above example
            
            for(int j = i; j >= 0;j--){
                double down = ret.rows[j].valueAt(ret.rows[i].positionOfLeadingOne);
                if(down != 0){
                // 5 in the above example
                  double up = ret.rows[i].valueAt(ret.rows[i].positionOfLeadingOne);
                  // -down to eliminate
                  ret.pivot(j,up,i,-down);
                 // ret.rows[j].updateLeadingOne();
                 }
                 
            }
        }
        for(int i = 0; i < ret.rows.length; i++){
            // scale every thing down by the first element
            ret.scale(i, (double) (1.0/ret.rows[i].firstValue()) );
        }
        return ret;
    }

    public void display(){
        System.out.println("The matrix is: ");
        for(int i = 0; i < numberOfRows; i++){
            rows[i].display();
        }
        System.out.println();
    }

    public void displayInt(){
        System.out.println("The matrix is: ");
        for(int i = 0; i < numberOfRows; i++){
            rows[i].displayInt();
        }
        System.out.println();        
    }
}