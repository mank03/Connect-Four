package connectfour;

import org.junit.Test;
// import org.junit.Assert;
// import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import java.io.IOException;
// import java.nio.file.NoSuchFileException;


/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board objectToTest; //or fixture;
    private Board testObject; //or fixture;
    private String[][] newBoard = new String[6][7];
    private String setUpNewBoard() throws IOException{

        final StringBuilder sbNew = new StringBuilder();
        objectToTest = new Board();
        objectToTest.readFile("assets/testNewBoard.csv");

        for (int y = 0; y < newBoard.length; ++y) {
            sbNew.append("|");
            for (int x = 0; x < newBoard[0].length; ++x) {
                final String color = newBoard[y][x];
                sbNew.append(color != null ? color.toString() : " ");
                sbNew.append("|");
                if(newBoard[y][x] == null){
                    newBoard[y][x] = ("0");  
                }
            }
            sbNew.append("\n");
        }
        sbNew.append("---------------\n");
        sbNew.append(" 1 2 3 4 5 6 7 (0 to save)");
        return sbNew.toString();
    }

    private String setUpLoadedBoard() throws IOException{
        String[][] board = new String[6][7];
        ArrayList<String[]> lines = new ArrayList<String[]>();
        String line = null;
        final StringBuilder sb = new StringBuilder();
        objectToTest = new Board();
        // objectToTest.readFile("assets/test3.csv");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("assets/test3.csv"))) {
            int i = 0;
            while(((line = bufferedReader.readLine()) != null) && i<27) {
                lines.add(line.split(","));
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
            }
        }
        board = array;

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
        return sb.toString();
    }


    @Test
    public void testIsFullFalse() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testIsNotFull.csv");
        boolean actualResult = objectToTest.isFull(1);
        assertFalse(actualResult);
    }

    @Test
    public void testIsFullTrue() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testIsFull.csv");
        boolean actualResult = objectToTest.isFull(1);
        assertTrue(actualResult);
    }

    @Test
    public void testToStringNewBoard() throws IOException{
        setUpNewBoard();
        testObject = new Board();
        testObject.readFile("assets/testNewBoard.csv");
        String theBoard = testObject.toString();
        assertEquals(setUpNewBoard(), theBoard);
    }

    @Test
    public void testToStringLoadedBoard() throws IOException{
        setUpLoadedBoard();
        objectToTest = new Board();
        objectToTest.readFile("assets/test3.csv");
        String returnString = objectToTest.toString();
        assertEquals(setUpLoadedBoard(), returnString);
    }


    @Test
    public void testCheckHorizontalP1() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testHorizontalWinP1.csv");
        boolean horizontalResult = objectToTest.checkHorizontal("X",1);
        assertTrue(horizontalResult);
    }

    @Test
    public void testCheckHorizontalP2() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testHorizontalWinP2.csv");
        boolean horizontalResult = objectToTest.checkHorizontal("O",1);
        assertTrue(horizontalResult);
    }

    @Test
    public void testNoHorizontalWinner() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testNoHorizontalWin.csv");
        boolean horizontalResult = objectToTest.checkHorizontal("O",1);
        assertFalse(horizontalResult);
    }

    @Test
    public void testCheckVerticalP1() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testVerticalWinP1.csv");
        boolean verticalResult = objectToTest.checkVertical("X",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testCheckVerticalP2() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testVerticalWinP2.csv");
        boolean verticalResult = objectToTest.checkVertical("O",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testNoVerticalWin() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testNoVerticalWin.csv");
        boolean verticalResult = objectToTest.checkVertical("O",1);
        assertFalse(verticalResult);
    }

    @Test
    public void testCheckDiagonal1P1() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testDiagonal1P1.csv");
        boolean verticalResult = objectToTest.checkDiagonal("X",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testCheckDiagonal2P1() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testDiagonal2P1.csv");
        boolean verticalResult = objectToTest.checkDiagonal("X",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testCheckDiagonal1P2() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testDiagonal1P2.csv");
        boolean verticalResult = objectToTest.checkDiagonal("O",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testCheckDiagonal2P2() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testDiagonal2P2.csv");
        boolean verticalResult = objectToTest.checkDiagonal("O",1);
        assertTrue(verticalResult);
    }

    @Test
    public void testNoDiagonalWin() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testNoDiagonalWin.csv");
        boolean verticalResult = objectToTest.checkDiagonal("O",1);
        assertFalse(verticalResult);
    }

    @Test
    public void testGetColumnHeightOfTiles() throws IOException{
        objectToTest = new Board();
        objectToTest.readFile("assets/testDiagonal2P1.csv");
        int actualResult = objectToTest.getColumnHeight(1,1);
        assertEquals(2,actualResult);
    }
}