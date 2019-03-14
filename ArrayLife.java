import java.util.Scanner;
public class ArrayLife{
    private int width;
    private int height;
    private boolean[][] world; 
    private boolean[][] worldCopy;
    private Scanner myObj = new Scanner(System.in);

    private void copyWorld(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                worldCopy[i][j] = world[i][j];
            }
        }        
    }

    public boolean getCell(int col, int row){
        if(col<0 || col>=width){
            return false;
        }
        if(row<0 || row>=height){
            return false;
        }
        return world[row][col];
        
    }
    public void setCell(int col, int row, boolean value){
        if(col<0 || col>=width){
            return;
        }
        if(row<0 || row>=height){
            return;
        }
        world[row][col] = value;
    }
    public void print(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(world[i][j]){
                    System.out.print("#");
                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    private int countNeighbours(int col, int row){
        int counter = 0;
        for(int i=row-1;i<row-1+3;i++){
            for(int j=col-1;j<col-1+3;j++){
                if(i==row && j=col){
                    continue;
                }
                if(getCell(i,j)){
                    counter++;
                }
            }
        }
        return counter;
    }
    public boolean computeCell(int col, int row){
        if(countNeighbours(col, row)<2 || countNeighbours(col, row)>3){
            return false;
        }
        return true;
    }
    public void nextGeneration(){
        copyWorld();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(computeCell(j,i)){
                    setCell(j,i,true);
                }
                else{
                    setCell(j,i,false);
                }
            }
        }   
    }
    public void play(){
        nextGeneration();
        print();
        char continueGame = myObj.next().charAt(0);
        if(continueGame == 's'){
            play();
        }
        else if(continueGame  = 'q'){
            return;
        }
    }
}