public class PackedLife{
    private int width;
    private int height;
    private long world = 0;
    private Pattern pattern;    
    



//     public static void main(String[] args){
//         long a = 1;
//         a = a << ((0*8) + 5);
//         // a = a << ((1*8) + 6);
//         System.out.println(a);
//         if(a>>>((1*8) + 6) & 1){
//   System.out.println(a>>>((1*8) + 6) & 1);
//         }
      
//     }
    public PackedLife(String format){
        pattern = new Pattern(format);

        width = pattern.getWidth();
        height = pattern.getHeight();
    }

    public void initialise(){
        String[] helparr = pattern.pattern.split(" ");

        int[][] cells = new int[helparr.length][];

        for(int i=0; i<cells.length; i++){
            cells[i] = new int[helparr[i].length()];
            for(int j=0; j<cells[i].length; j++){
                cells[i][j] = Character.getNumericValue(helparr[i].charAt(j));
            }
        } 
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[i].length; j++){
               if(cells[i][j]==1){
                //    setCell(world,j+startUpperCol,i+startUpperRow,true);
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
        if ((world >>> (row * width + col)) & 1 == 1)
            return true;
        else
            return false;
    }
    public void setCell(int col, int row, boolean value){
        if (row < 0 || row >= height) {
            return;
        }
        if (col < 0 || col >= width) {
            return;
        }
        if(value){
            world += Math.pow(2, (row*width+col));
        }
    }
// • void print(): printing the state of the whole board
// • int countNeighbours(int col, int row): counting the number of neighbours
// alive
// 1• boolean computeCell(int col, int row): determining if the cell will be alive or
// dead in the next generation, based on the rules of the game
// • void nextGeneration(): updating the game board to the next generation
// • void play()
}