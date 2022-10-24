package connectfour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is responsible for the game's functionality and includes the main method
 * 
 * @author Manu Konnur
 * 
 */
public class ConnectFour {

  private String string;
  private Board board = new Board();
  private static TextUI text = new TextUI();
  private String currentPlayer;
  private int q;
  private static final String PLAYER1_WIN = "Player 1 wins!";
  private static final String PLAYER2_WIN = "Player 2 wins!";
  private static final String TIE_MESSAGE = "It is a tie!";

  @Override
  public String toString() {
    return getReportOutcome();
  }

  public void setUser() throws IOException {
    int user = text.askUser();
    if (user == 1) {
      String askUser = text.askFileName();
      board.readFile(askUser);
    }
    q = user;
  }

  public int getUser() {
    return q;
  }

  public boolean validateFile(String filename) {
    File f = new File(filename);
    if (f.exists()) {
      System.out.println("Exists");
      return true;
    } else {
      System.out.println("Does not Exists");
      return false;
    }
  }

  public boolean checkColumn(int column) {
    if (column < 1) {
      return true;
    } else if (column > board.getWidth()) {
      return true;
    }
    return false;
  }

  public String setTurn(String playerTurn) {
    if (playerTurn.equals("X")) {
      return "O";
    } else {
      return "X";
    }
  }

  public String checkWin(int i) throws FileNotFoundException {
    currentPlayer = "X";

    // System.out.println("i #3: " + i);

    while (true) {

      String winnerColor = board.checkVictory(i);
      if (winnerColor == "X" || winnerColor == "O" || board.isFull(i)) {
        return winnerColor;
      }

      final int column = text.readPly(i);
      board.put(column, currentPlayer, i);

      String x = this.setTurn(currentPlayer);
      System.out.println("x is " + x);
      text.savePlayer(x);
      // String x = game.invert();
      currentPlayer = x;
      text.printBoard();
    }
  }

  public void setReportOutcome(String winnerColor) {
    if (winnerColor == "X") {
      string = PLAYER1_WIN;
    } else if (winnerColor == "O") {
      string = PLAYER2_WIN;
    } else {
      string = TIE_MESSAGE;
    }
  }

  public String getReportOutcome() {
    return string;
  }

  public static void main(final String[] args) throws Exception {
    // while(true){
    ConnectFour main = new ConnectFour();
    Board board = new Board();
    main.setUser();
    int i = main.getUser();
    board.getUserInput(i);
    text.run(i);
    System.out.println("thanks for playing!\n");

  }
}