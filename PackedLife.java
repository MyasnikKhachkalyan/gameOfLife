import java.util.Scanner;
public class PackedLife{
    private int width;
    private int height;
    private long world;
    private Pattern pattern; 

    



    public static void main(String[] args){ 
        PackedLife al = new PackedLife(args[0]);
        al.play();
      
    }
    public PackedLife(String format){
        pattern = new Pattern(format);
        world = 0L;
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
                world = world | (1L << row*width+col);
        }
        else{
            if(getCell(col, row)){
                world =  world ^ (1L << row*width+col);
            }
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
        long worldCopy = 0L;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(computeCell(j,i)){
                    worldCopy = worldCopy | (1L << i*width+j);
                }
            }
        }   
        world = worldCopy;
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
// method play is public because we need to use it in our main method to write al.play
// method nextGeneration is used only inside a class in play method and there is no need to make it public.
// method computeCell is used only inside a class in nextGeneration method,  no need to make it public.
// method counNeighbours is used only inside a class in computeCell method,  no need to make it public.
// method print is public so the world could be just printed for e.g. from main method by al.print, in my case i did not use that option but anyway in the future could be heplful to test program
// method setCell is private so user could not mutate the cell artificially
// method getCell is public to get the cell when in testing the program or smth, in my case it also could haev been private, but for future tasting i left it public.
// method initialise is privite as it is used only  in constructor and no need to be public
///////////////////////////////////////////

////
//for untill 8*8 world it is better to use packedlife as it save some space, uses space for upto8*8 world, but for bigger worlds we cannot rely on this and should use Arraylife
////
//because there is no need to pass world as an argument, as it is visible inside the class