import java.util.Scanner;
public class ArrayLife{
    private int width;
    private int height;
    private boolean[][] world; 
    private boolean[][] worldCopy;
    private Pattern pattern;
    private Scanner myObj = new Scanner(System.in);

    public ArrayLife(String format){
        pattern = new Pattern(format);
        width = pattern.getWidth();
        height = pattern.getHeight();
        world = new boolean[height][width];
        worldCopy = new boolean[height][width];
        pattern.initialise(world);


    }

    public static void main(String[] args){
        ArrayLife al = new ArrayLife(args[0]);
        al.play();
    }

    private void setCopiedWorld(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                 world[i][j] = worldCopy[i][j] ;
            }
        }
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                worldCopy[i][j] = false;
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
    private void setCellCopy(int col, int row, boolean value){
        if(col<0 || col>=width){
            return;
        }
        if(row<0 || row>=height){
            return;
        }
        worldCopy[row][col] = value;
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
                if(i==row && j==col){
                    continue;
                }
                if(getCell(j,i)){
                    counter++;
                }
            }
        }
        return counter;
    }
    private boolean computeCell(int col, int row){
        int numOfNeighbours = countNeighbours(col, row); 
        if(numOfNeighbours<2 || numOfNeighbours>3){
            return false;
        }
        return true;
    }
    public void nextGeneration(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(computeCell(j,i)){
                    setCellCopy(j,i,true);
                }
                else{
                    setCellCopy(j,i,false);
                }
            }
        }   
        setCopiedWorld();
    }
    public void play(){
        print();
        char continueGame = myObj.next().charAt(0);
        if(continueGame == 's'){
            nextGeneration();
            play();
        }
        else if(continueGame  == 'q'){
            return;
        }
    }
}