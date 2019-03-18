public class Pattern{
    private String name;
    private String author;
    private int width;
    private int height;
    private int startUpperCol;
    private int startUpperRow;
    private String pattern;

    public Pattern(String initialiser){
        String[] arr = initialiser.split(":");
        name = arr[0];
        author = arr[1];
        width = Integer.parseInt(arr[2]);
        height = Integer.parseInt(arr[3]);
        startUpperCol = Integer.parseInt(arr[4]);
        startUpperRow = Integer.parseInt(arr[5]);
        pattern = arr[6];
    }

    public static void main(String[] args){
        Pattern pat = new Pattern(args[0]);
        pat.printFormat();
    }

    public void initialise(boolean[][] world){
        String[] helparr = pattern.split(" ");


        for(int i=0; i<helparr.length; i++){
            for(int j=0; j<helparr[i].length(); j++){
                if(Character.getNumericValue(helparr[i].charAt(j)) == 1){
                    setCell(world,j+startUpperCol,i+startUpperRow,true);
                }
            }
        } 
    }

    public void setCell(boolean[][] world ,int col, int row, boolean value){
        if(col<0 || col>=width){
            return;
        }
        if(row<0 || row>=height){
            return;
        }
        world[row][col] = value;
    }

    public void printFormat(){
        System.out.println("Name: " + getName());
        System.out.println("Author: " + getAuthor());
        System.out.println("Width: " + getWidth());
        System.out.println("Height: " + getHeight());
        System.out.println("StartCol: " + getStartCol());
        System.out.println("StartRow: " + getStartCol());
        System.out.println("Pattern: " + getPattern());
    }
    
    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getStartCol(){
        return startUpperCol;
    }

    public int getStartRow(){
        return startUpperRow;
    }

    public String getPattern(){
        return pattern;
    }
}