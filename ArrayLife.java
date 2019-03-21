import java.util.Scanner;
public class ArrayLife{
    private int width;
    private int height;
    private boolean[][] world; 
    private Pattern pattern;

    public ArrayLife(String format){
        pattern = new Pattern(format);
        width = pattern.getWidth();
        height = pattern.getHeight();
        world = new boolean[height][width];
        pattern.initialise(world);


    }

    public static void main(String[] args){
        ArrayLife al = new ArrayLife(args[0]);
        al.play();
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
    private void setCell(int col, int row, boolean value){
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
        if(getCell(col, row)){
            if(numOfNeighbours>=2 && numOfNeighbours<=3){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(numOfNeighbours==3){
                return true;
            }
            else{
                return false;
            }

        }   
    }
    private void nextGeneration(){
        boolean[][] worldCopy = new boolean[width][height];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(computeCell(j,i)){
                    worldCopy[i][j] = true;
                }
            }
        }   
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                 world[i][j] = worldCopy[i][j] ;
            }
        }
    }
    public void play(){
        Scanner myObj = new Scanner(System.in);   
        print();  
        String continueGame;   
        for(continueGame = myObj.next();!continueGame.equals("q") && continueGame.equals("s") && continueGame.length()==1;continueGame = myObj.next()){  
                nextGeneration();
                print();
        }
        if(continueGame.equals("q")){
            System.out.println("You succesfully quited the game");
        }
        else if(continueGame.length()!=1){
             throw new Error("U inputed more than one character");
        }
        else{
             throw new Error("U inputed wrong character");
        }
    }
}

///////////////////////////////////////////
// the format provided should be written in "" so the program takes it as a single argument.

// method play is public because we need to use it in our main method to write al.play
// method nextGeneration is used only inside a class in play method and there is no need to make it public.
// method computeCell is used only inside a class in nextGeneration method,  no need to make it public.
// method counNeighbours is used only inside a class in computeCell method,  no need to make it public.
// method print is public so the world could be just printed for e.g. from main method by al.print, in my case i did not use that option but anyway in the future could be heplful to test program
// method setCell is private so user could not mutate the cell artificially
// method getCell is public to get the cell when in testing the program or smth, in my case it also could haev been private, but for future tasting i left it public.
/////////////////////////////////////////// 