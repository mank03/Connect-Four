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

  public void setReportOutcome(String winnerPlayer) {
    if (winnerPlayer == "X") {
      string = PLAYER1_WIN;
    } else if (winnerPlayer == "O") {
      string = PLAYER2_WIN;
    } else {
      string = TIE_MESSAGE;
    }
  }

  public String getReportOutcome() {
    return string;
  }

  public String getTurn(String playerTurn) {
    if (playerTurn.equals("X")) {
      return "O";
    } else {
      return "X";
    }
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

  public String checkWin(int i) throws FileNotFoundException {
    currentPlayer = "X";

    while (true) {

      String winnerPlayer = board.checkVictory(i);
      if (winnerPlayer == "X" || winnerPlayer == "O" || board.isFull(i)) {
        return winnerPlayer;
      }
      final int column = text.readPlay(i);
      board.putPiece(column, currentPlayer, i);

      String x = this.getTurn(currentPlayer);
      System.out.println("x is " + x);
      text.savePlayer(x);
      currentPlayer = x;
      text.printBoard();
    }
  }
  
  public static void main(final String[] args) throws Exception {
    ConnectFour main = new ConnectFour();
    Board board = new Board();
    main.setUser();
    int i = main.getUser();
    board.getUserInput(i);
    text.run(i);
    System.out.println("thanks for playing!\n");

  }
}