package connectfour;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class ties all the components into a command line Connect Four game.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jun 28, 2016)
 */
public class TextUI {

    // private static final String NEW_GAME_REQUEST = "Play again? Type \"no\" to exit, or anything " + "else to replay.";

    private static final String EXIT_MESSAGE = "Bye!";

    private static final String RED_PROMPT = "red> ";

    private static final String YELLOW_PROMPT = "yellow> ";

    private Scanner scanner = new Scanner(System.in);
    private Board board = new Board();
    private ConnectFour game = new ConnectFour();
     
    // public static int v = 0;

    //private ConnectFour game;
    private String currentPlayer;

    public int askUser(){
        System.out.println("Would you like to continue a saved game or start a new game?(1/0)");
        int user = scanner.nextInt();
        return user;
    }



    public void run(int i) throws IOException {
        
        
        //this.setUser();
        //this.getUser();
        // board.getUserInput(getUser());
        // System.out.println("i #2: " + i);
        board = new Board();
        this.gameLoop(i);
        
            // String winnerColor = gameLoop(board);
            String winnerColor = game.checkWin(i);
            String outcome = game.reportOutcome(winnerColor);
            System.out.println(outcome);

            // if (!newGameRequested()) {
            //     break;
            // }
        
        
        // System.out.println(EXIT_MESSAGE);
    }

    public void printBoard(){
        board = new Board();
        System.out.println(board);
    }

    private void gameLoop(int i) throws IOException {
        // this.getUser();
        // board.getUserInput(getUser());
        // System.out.println("i #4: " + i);

        currentPlayer = "X";
        // board = new Board();
        System.out.println();
        this.printBoard();
        // System.out.println(board);
        game.checkWin(i);
    }

    // public boolean newGameRequested() {
    //     System.out.println(NEW_GAME_REQUEST);
    //     final String input = scanner.next().trim().toLowerCase();
    //     return !input.equals("no");
    // }


    public int readPly(int i) throws FileNotFoundException {
        while (true) {
            // this.getUser();
            // board.getUserInput(getUser());
            if (currentPlayer == "X") {
                System.out.print(RED_PROMPT);
            } else if (currentPlayer == "O") {
                System.out.print(YELLOW_PROMPT);
            } else {
                throw new IllegalStateException("Should not get here.");
            }
            final String command = scanner.next().trim();

            try {
                final int column = Integer.parseInt(command);
                if(column == 0){
                    board.writeData(currentPlayer);  
                    System.out.println(EXIT_MESSAGE);
                    System.exit(0);
                } else if (column < 1) {
                    System.out.println("Column index must be at least 1.");
                    continue;
                } else if (column > board.getWidth()) {
                    System.out.println("Column index must be at most " + board.getWidth());
                    continue;
                }

                final int columnIndex = column - 1;

                if (board.getColumnHeight(columnIndex, i) == board.getHeight()) {
                    System.out.println("Column " + column + " is " + "full.");
                }



                return columnIndex;
            } catch (final NumberFormatException ex) {
                System.out.println("\"" + command + "\" is not an integer!");
            }
        }
    }
}