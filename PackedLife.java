import java.util.Scanner;
public class PackedLife{
    private int width;
    private int height;
    private long world;
    private long worldCopy;
    private Pattern pattern; 
    private Scanner myObj = new Scanner(System.in);   
    



    public static void main(String[] args){ 
        PackedLife al = new PackedLife(args[0]);
        al.play();
      
    }
    public PackedLife(String format){
        world = 0;
        pattern = new Pattern(format);
        width = pattern.getWidth();
        height = pattern.getHeight();

        initialise();
        
    }

    private void initialise(){
        String[] helparr = pattern.getPattern().split(" ");


        for(int i=0; i<helparr.length; i++){
            for(int j=0; j<helparr[i].length(); j++){
                if(Character.getNumericValue(helparr[i].charAt(j)) == 1){
                    setCell(j+pattern.getStartCol(),i+pattern.getStartRow(),true);
                }
            }
        } 
    }

    public boolean getCell(int col, int row) {
        if (row < 0 || row >= height) {
            return false;
        }
        if (col < 0 || col >= width) {
            return false;
        }
        if (((world >>> (row * width + col)) & 1) == 1)
            return true;
        else
            return false;
    }
    private void setCell(int col, int row, boolean value){
        if (row < 0 || row >= height) {
            return;
        }
        if (col < 0 || col >= width) {
            return;
        }
        if(value){
            world += Math.pow(2, (row*width+col));
        }
        else{
            if(getCell(col, row)){
                world -= Math.pow(2, (row*width+col));
            }
        }
    }
    private void setLiveCellsInCopy(int col, int row, boolean value){
        if (row < 0 || row >= height) {
            return;
        }
        if (col < 0 || col >= width) {
            return;
        }
        if(value){
            worldCopy += Math.pow(2, (row*width+col));
        }
    }
    public void print(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(((world >>> (i * width + j)) & 1) == 1){
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
    private void nextGeneration(){
        worldCopy = 0;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(computeCell(j,i)){
                    setLiveCellsInCopy(j,i,true);
                }
            }
        }   
        world = worldCopy;
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