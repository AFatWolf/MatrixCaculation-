public class Row {
    double[] element;
    int length;
    int positionOfLeadingOne;
    
    Row(int size){
        this.length = size;
        this.element = new double[this.length];
        int i = 0;
        for(i = 0;i < this.length; i++){
            this.element[i] = (double) 0;
        }
        this.positionOfLeadingOne = 0;
        while (this.element[this.positionOfLeadingOne] == 0 || this.positionOfLeadingOne < size){
            this.positionOfLeadingOne ++;
        }
    }

    Row(double[] element){
        this.length = element.length;
        this.element = new double[this.length];
        int i = 0;
        for(i = 0;i < this.length; i++){
            this.element[i] = element[i];
        }
        this.positionOfLeadingOne = 0;
        while (this.element[this.positionOfLeadingOne] == 0 && this.positionOfLeadingOne < length){
            this.positionOfLeadingOne ++;
        }
    }
    // scale without return
    public void selfScale(double scalar){
        for(int i = 0;i < this.length;i++){
            this.element[i] *= scalar;
        }
    }

    public Row scale(double scalar){
        Row row = new Row(this.element);
        row.selfScale(scalar);
        return row;
    }

    public Row add(Row other){
        Row row = new Row(this.element);
        for(int i = 0; i < this.length;i++){
            row.element[i] += other.element[i];
        }
        return row;
    }
    // scale other then add
    public Row pivot(Row other, double scalar){
        other.selfScale(scalar);
        return this.add(other);
    }   

    public boolean isToTheLeftOf(Row other){
        return (this.positionOfLeadingOne <= other.positionOfLeadingOne);
    }

    public double valueAt(int position){
        return this.element[position];
    }

    public void assignAt(int position, double value){
        element[position] = value;
    }

    public double firstValue(){
        return valueAt(positionOfLeadingOne);
    }

    public void updateLeadingOne(){
        while(this.valueAt(this.positionOfLeadingOne) == 0 && this.positionOfLeadingOne < length)
            this.positionOfLeadingOne ++;
    }

    public void display(){
        for(int i = 0; i < this.length; i++){
            if(element[i] == -0.0)
                element[i] = 0.0;
            System.out.printf("%5.2f ", element[i] );
        }
        System.out.println();
    }

    public void displayInt(){
        for(int i = 0; i < this.length; i++){
            System.out.printf("%5d ", (int) element[i] );
        }
        System.out.println();
    }
}