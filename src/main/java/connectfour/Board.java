package connectfour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This class implements the actual Connect Four board.
 * 
 * @author Manu Konnur
 * 
 */
public class Board {

    /**
     * The number of columns in the board.
     */
    private static final int BOARD_WIDTH  = 7;
    /**
     * The number of rows in the board.
     */
    private static final int BOARD_HEIGHT = 6;
    /**
     * The minimum length of a winning pattern.
     */
    private static final int WINNING_PATTERN_LENGTH = 4;
    private static String[][] board = new String[BOARD_HEIGHT][BOARD_WIDTH];
    // public static int v = 0;           
    private int user;
    private static int r;
    
    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        if(r==1){
            return board.length-2;
        }else{
            return board.length;
        }
    }
    
    public void getUserInput(int i){
        user = i;
        // return i;
    }

    public int setUserInput(){
        return user;
    }

    /**
     * Performs a ply of the player with the color {@code color} putting a new
     * piece at column {@code x}.
     * 
     * @param x     the column index.
     * @param color the player color.
     * @throws IOException
     */
    public void put(final int x, final String color, int i) {
        checkX(x);
        checkColumnNotFull(x, i);
        Objects.requireNonNull(color, "The input color is null.");
    
        if(i==1){
            int y = board.length - 3;
            while (board[y][x].equals("X") || board[y][x].equals("O")) {
                --y;
            }
            board[y][x] = color;
        }else{
            int y = board.length - 1;
            while (board[y][x] != null) {
                --y;
            }
            board[y][x] = color;
        }
    }

    public String checkVictory(int i) {
        if (checkHorizontal("X", i) || checkVertical("X", i) || checkDiagonal("X", i)){
            return "X";
        }else if(checkHorizontal("O", i) || checkVertical("O", i) || checkDiagonal("O", i)){
            return "O";
        }
       return null;
    }

    public boolean isFull(int i) {
        if(i==1){
            for (int y = 0; y < board.length-2; ++y) {
                for (int x = 0; x < board[0].length; ++x) {
                    if (board[y][x].equals("0")) {
                        return false;
                    }
                }
            }
            return true;
        }else{
            for (int y = 0; y < board.length; ++y) {
                for (int x = 0; x < board[0].length; ++x) {
                    if (board[y][x] == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public String toString() {
 
        if(r == 1){
            new Board();

            //String[][] newBoard = Board.readFile();
            final StringBuilder sb = new StringBuilder();
   
            for (int y = 0; y < board.length-2; ++y) {
                sb.append("|");
      
                for (int x = 0; x < board[0].length; ++x) {
                    final String color = board[y][x];
                    sb.append(color != null ? color.toString() : " ");
                    sb.append("|");
                }
      
                sb.append("\n");
            }

            sb.append("---------------\n");
            sb.append(" 1 2 3 4 5 6 7 (0 to save)");
            // System.out.println("newBoard[5][0]  is " + board[5][0]);
            return sb.toString();
        }else if(r==0){
        new Board();
        final StringBuilder sb = new StringBuilder();
        // System.out.println("board[0].length is " + board[0].length);

        for (int y = 0; y < board.length; ++y) {
            sb.append("|");
   
            for (int x = 0; x < board[0].length; ++x) {
                final String color = board[y][x];
                sb.append(color != null ? color.toString() : " ");
                sb.append("|");
            }
   
            sb.append("\n");
        }
   
        sb.append("---------------\n");
        sb.append(" 1 2 3 4 5 6 7 (0 to save)");
        
        return sb.toString();
    }
        return null;
    }

  
    public boolean checkHorizontal(final String red, int i) {
        int horizontalPatterns = getWidth() - WINNING_PATTERN_LENGTH + 1;

        for (int startY = 0; startY < getHeight(); ++startY) {
            next_pattern:
            for (int startX = 0; startX < horizontalPatterns; ++startX) {
                for (int offset = 0; offset < WINNING_PATTERN_LENGTH; offset++) {
                    if(i==1){
                        if(!board[startY][startX + offset].equals(red)){
                            continue next_pattern;
                        }
                    }else{
                        if (board[startY][startX + offset] != (red)) {
                            continue next_pattern;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkVertical(final String red, int i) {
        int verticalPatterns = getHeight() - WINNING_PATTERN_LENGTH + 1;

        for (int startX = 0; startX < getWidth(); ++startX) {
            next_pattern:
            for (int startY = 0; startY < verticalPatterns; ++startY) {
                for (int offset = 0; offset < WINNING_PATTERN_LENGTH; offset++) {
                    if(i==1){
                        if(!board[startY + offset][startX].equals(red)){
                            continue next_pattern;
                        }
                    }else{
                        if (board[startY + offset][startX] != (red)) {
                            continue next_pattern;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal(final String red, int i) {
        int verticalPatterns = getHeight() - WINNING_PATTERN_LENGTH + 1;
        int horizontalPatterns = getWidth() - WINNING_PATTERN_LENGTH + 1;

        for (int startY = 0; startY < verticalPatterns; ++startY) {
            next_pattern:
            for (int startX = 0; startX < horizontalPatterns; ++startX) {
                for (int offset = 0; offset < WINNING_PATTERN_LENGTH; offset++) {
                    if(i==1){
                        if (!board[startY + offset][startX + offset].equals(red)) {
                            continue next_pattern;
                        }
                    }else{
                        if(board[startY][startX + offset] != red){
                        continue next_pattern;
                        }
                    }
                }
                return true;
            }
        }
        for (int startY = 0; startY < verticalPatterns; ++startY) {
            next_pattern:
            for (int startX = WINNING_PATTERN_LENGTH - 1; startX < getWidth(); startX++) {
                for (int offset = 0; offset < WINNING_PATTERN_LENGTH; offset++) {
                    if(i==1){
                        if (!board[startY + offset][startX - offset].equals(red)) {
                            continue next_pattern;
                        }
                    }else{
                        if(board[startY][startX + offset] != red){
                            continue next_pattern;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    

    public int getColumnHeight(final int x, int i) {
        int height = 0;
        if(i==1){
            for (int y = board.length - 3; y >= 0; --y, ++height) {
                if (board[y][x].equals("0")) {
                    return height;
                }
            }
            return height;
        }else{
            for (int y = board.length - 1; y >= 0; --y, ++height) {
                if (board[y][x] == null) {
                    return height;
                }
            }
            return height;
        } 
    }

    private void checkX(final int x) {
        if (x < 0) {
            throw new IndexOutOfBoundsException("The x-coordinate is negative: " + x);
        }
        if (x >= board[0].length) {
            throw new IndexOutOfBoundsException("The x is too large: " + x + ". Must be at most "+(board[0].length-1));
        }
    }

    private void checkColumnNotFull(final int x, int i) {
        if(i==1){
            if (getColumnHeight(x, i) == board.length-2) {
                throw new IllegalStateException("The column at x-coordinate " + x + " is full.");
            }
        }else{
            if (getColumnHeight(x, i) == board.length) {
                throw new IllegalStateException("The column at x-coordinate " + x + " is full.");
            }
        }
    }

public void writeData(final String currentPlayer) throws FileNotFoundException{

    String path = "assets/test3.csv";
    File file = new File(path);
    PrintWriter writer = new PrintWriter(file);

    // Loop to name .csv files
    for (int i = 0; i < 6; i++){
        for (int j = 0; j < 7; j++){
            if(board[i][j] == "0" || board[i][j] == null){
                writer.print(0);  
            }else if((board[i][j]) == "X"){
                writer.print(1);
            }else if((board[i][j]) == "O"){
                writer.print(2);
            }
            if(j<board[i].length-1) {
                writer.print(",");
            }
        }
        writer.println();   
    }
    writer.print("\n" + currentPlayer);
    writer.close();
}

public static void readFile() throws IOException {
    String file = "assets/test4.csv";
    ArrayList<String[]> lines = new ArrayList<String[]>();
    String line = null;

    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        int i = 0;
        while(((line = bufferedReader.readLine()) != null) && i<27) {
            lines.add(line.split(","));
            //System.out.println(Arrays.toString(lines.get(i)));
            i++;
        }
    }catch (IOException e) {
        e.printStackTrace();
    }

    String[][] array = new String[lines.size()][0];
    lines.toArray(array);

    for (int i = 0; i < array.length-2; i++) { //this equals to the row in our matrix.
        for (int j = 0; j < array[i].length; j++) { //this equals to the column in each row.
            if(array[i][j].equals("0")){
                array[i][j] = ("0");  
            }else if(array[i][j].equals("1")){
                array[i][j] = ("X"); 
            }else if(array[i][j].equals("2")){
                array[i][j] = ("O"); 
            }
           System.out.print(array[i][j] + " ");
        }
        System.out.println(); //change line on console as row comes to end in the matrix.
    }
   board = array;
    r = 1;
}

public static int getReadFile(){
    return r;
}

}