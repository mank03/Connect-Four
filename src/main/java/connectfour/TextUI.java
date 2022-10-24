package connectfour;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is entirely responsible for interacting with the user and prints the output
 * 
 * @author Manu Konnur
 * 
 */
public class TextUI {

  // private static final String NEW_GAME_REQUEST = "Play again? Type \"no\" to exit, or anything ");

  private static final String EXIT = "Bye!";
  private static final String PLAYER1 = "player 1: ";
  private static final String PLAYER2 = "player2: ";
  private Scanner scanner = new Scanner(System.in);
  private Scanner scanner2 = new Scanner(System.in);
  private Board board = new Board();
  private ConnectFour game = new ConnectFour();
  private String stringToReturn;
  private String currentPlayer;

  public int askUser() {
    while (true) {
      try {
        System.out.println("Would you like to continue a saved game or start a new game?(1/0)");
        int user = scanner.nextInt();
        if (user == 1 || user == 0) {
          return user;
        } else {
          System.out.println("please enter either 1 or 0\n");
          continue;
        }
      } catch (InputMismatchException ex) {
        System.out.println("not a correct value!");
      }
    }
  }

  public String askFileName() {
    while (true) {
      String userFile;
      System.out.println("Which file would you like to read from?: ");
      userFile = scanner2.nextLine();
      if (game.validateFile(userFile)) {
        return userFile;
      } else {
        continue;
      }
    }
  }

  public void run(int i) throws IOException {
    board = new Board();
    this.gameLoop(i);
    String winnerColor = game.checkWin(i);
    game.setReportOutcome(winnerColor);
    game.getReportOutcome();
    System.out.println(game);
  }

  public void printBoard() {
    board = new Board();
    System.out.println(board);
  }

  private void gameLoop(int i) throws IOException {
    currentPlayer = "X";
    System.out.println();
    this.printBoard();
    game.checkWin(i);
  }

  private void setUserCommand() {
    String command = scanner.next().trim();
    stringToReturn = command;
  }

  private String getUserCommand() {
    return stringToReturn;
  }

  @Override
  public String toString() {
    return getUserCommand();
  }

  public String savePlayer(String player) {
    currentPlayer = player;
    return currentPlayer;
  }

  public int readPly(int i) throws FileNotFoundException {
    while (true) {
      String q = savePlayer(currentPlayer);
      if (q == "X") {
        System.out.print(PLAYER1);
      } else if (q == "O") {
        System.out.print(PLAYER2);
      } else {
        throw new IllegalStateException("Should not get here.");
      }
      this.setUserCommand();
      this.toString();

      try {
        final int column = Integer.parseInt(toString());
        final int columnIndex = column - 1;
        if (column == 0) {
          board.writeData(q);
          System.out.println(EXIT);
          System.exit(0);
        } else if (game.checkColumn(column)) {
          System.out.println("Column index must be at least 1.");
          continue;
        } else if (board.getColumnHeight(columnIndex, i) == board.getHeight()) {
          System.out.println("Column " + column + " is " + "full.");
          continue;
        }
        return columnIndex;
      } catch (final NumberFormatException ex) {
        System.out.println("\"" + toString() + "\" is not an integer!");
      }
    }
  }
}