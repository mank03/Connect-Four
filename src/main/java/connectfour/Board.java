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
 * This class is responsible for interacting with the board and checking victory.
 * 
 * @author Manu Konnur
 * 
 */
public class Board {

  private static final int WIDTH = 7;
  private static final int HEIGHT = 6;
  private static final int WIN_PATTERN= 4;
  private static String[][] board = new String[HEIGHT][WIDTH];
  private int user;
  private static int r;

  /**
   * This method is an accessor to get the width of the board
   * @return widths of the board
   */
  public int getWidth() {
    return board[0].length;
  }

  /**
   * This method is an accessor to get the height of the board
   * @return height of the board
   */
  public int getHeight() {
    if (r == 1) {
      return board.length - 2;
    } else {
      return board.length;
    }
  }

  /**
   * This method is an accessor to set the user input from the TextUI 
   * class of if they want start new game or load an existing board
   *
   * @param i user input
   */
  public void setUserInput(int i) {
    user = i;
  }

  /**
   * This method is an mutuator to get the user input from the TextUI 
   * class of if they want start new game or load an existing board
   *
   * @return the user input of load or new
   */
  public int getUserInput() {
    return user;
  }

  /**
   * This method get the column height the tiles placed
   * 
   * @param player the player piece.
   * @param i user input if load or new
   * @return column height of pieces
   */
  public int getColumnHeight(final int x, int i) {
    int height = 0;
    if (i == 1) {
      for (int y = board.length - 3; y >= 0; --y, ++height) {
        if (board[y][x].equals("0")) {
          return height;
        }
      }
      return height;
    } else {
      for (int y = board.length - 1; y >= 0; --y, ++height) {
        if (board[y][x] == null) {
          return height;
        }
      }
      return height;
    }
  }

  /**
   * This method puts a piece at column index the user inputted
   * 
   * @param x     the column index.
   * @param player the player piece.
   * @param i user input if load or new
   */
  public void putPiece(final int x, final String player, int i) {
    Objects.requireNonNull(player, "The input player is null.");
    if (i == 1) {
      int y = board.length - 3;
      while (board[y][x].equals("X") || board[y][x].equals("O")) {
        --y;
      }
      board[y][x] = player;
    } else {
      int y = board.length - 1;
      while (board[y][x] != null) {
        --y;
      }
      board[y][x] = player;
    }
  }

  /**
   * This method checks if victory is found
   * 
   * @param i user input if load or new
   * @return the winning player or null if not victory found
   */
  public String checkVictory(int i) {
    if (checkHorizontal("X", i) || checkVertical("X", i) || checkDiagonal("X", i)) {
      return "X";
    } else if (checkHorizontal("O", i) || checkVertical("O", i) || checkDiagonal("O", i)) {
      return "O";
    }
    return null;
  }

  /**
   * This method checks if board is full
   * 
   * @param i user input if load or new
   * @return either true or false if board is full
   */
  public boolean isFull(int i) {
    if (i == 1) {
      for (int y = 0; y < board.length - 2; ++y) {
        for (int x = 0; x < board[0].length; ++x) {
          if (board[y][x].equals("0")) {
            return false;
          }
        }
      }
      return true;
    } else {
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

  /**
   * This method is the toString method which appends the game board
   * @return the string representation of the game board, null otherwise
   */
  @Override
  public String toString() {
    if (r == 1) {
      new Board();
      final StringBuilder sb = new StringBuilder();

      for (int y = 0; y < board.length - 2; ++y) {
        sb.append("|");

        for (int x = 0; x < board[0].length; ++x) {
          final String player = board[y][x];
          sb.append(player != null ? player.toString() : " ");
          sb.append("|");
        }

        sb.append("\n");
      }

      sb.append("---------------\n");
      sb.append(" 1 2 3 4 5 6 7 (0 to save)");
      return sb.toString();
    } else if (r == 0) {
      new Board();
      final StringBuilder sb = new StringBuilder();

      for (int y = 0; y < board.length; ++y) {
        sb.append("|");

        for (int x = 0; x < board[0].length; ++x) {
          final String player = board[y][x];
          sb.append(player != null ? player.toString() : " ");
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

  /**
   * This method checks if victory is found horizontally
   * 
   * @param player the player piece.
   * @param i user input if load or new
   * @return true if horizontal win found, false otherwise
   */
  public boolean checkHorizontal(final String player, int i) {
    int horizontalPatterns = getWidth() - WIN_PATTERN + 1;

    for (int startY = 0; startY < getHeight(); ++startY) {
      next_pattern: for (int startX = 0; startX < horizontalPatterns; ++startX) {
        for (int offset = 0; offset < WIN_PATTERN; offset++) {
          if (i == 1) {
            if (!board[startY][startX + offset].equals(player)) {
              continue next_pattern;
            }
          } else {
            if (board[startY][startX + offset] != (player)) {
              continue next_pattern;
            }
          }
        }
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if victory is found vertically
   * 
   * @param player the player piece.
   * @param i user input if load or new
   * @return true if vertical win found, false otherwise
   */
  public boolean checkVertical(final String player, int i) {
    int verticalPatterns = getHeight() - WIN_PATTERN + 1;

    for (int startX = 0; startX < getWidth(); ++startX) {
      next_pattern: for (int startY = 0; startY < verticalPatterns; ++startY) {
        for (int offset = 0; offset < WIN_PATTERN; offset++) {
          if (i == 1) {
            if (!board[startY + offset][startX].equals(player)) {
              continue next_pattern;
            }
          } else {
            if (board[startY + offset][startX] != (player)) {
              continue next_pattern;
            }
          }
        }
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if victory is found diagonally
   * 
   * @param player the player piece.
   * @param i user input if load or new
   * @return true if diagonal win found, false otherwise
   */
  public boolean checkDiagonal(final String player, int i) {
    int verticalPatterns = getHeight() - WIN_PATTERN + 1;
    int horizontalPatterns = getWidth() - WIN_PATTERN + 1;

    for (int startY = 0; startY < verticalPatterns; ++startY) {
      next_pattern: for (int startX = 0; startX < horizontalPatterns; ++startX) {
        for (int offset = 0; offset < WIN_PATTERN; offset++) {
          if (i == 1) {
            if (!board[startY + offset][startX + offset].equals(player)) {
              continue next_pattern;
            }
          } else {
            if (board[startY][startX + offset] != player) {
              continue next_pattern;
            }
          }
        }
        return true;
      }
    }
    for (int startY = 0; startY < verticalPatterns; ++startY) {
      next_pattern: for (int startX = WIN_PATTERN - 1; startX < getWidth(); startX++) {
        for (int offset = 0; offset < WIN_PATTERN; offset++) {
          if (i == 1) {
            if (!board[startY + offset][startX - offset].equals(player)) {
              continue next_pattern;
            }
          } else {
            if (board[startY][startX + offset] != player) {
              continue next_pattern;
            }
          }
        }
        return true;
      }
    }
    return false;
  }

  public void writeData(final String currentPlayer, String save) throws FileNotFoundException {

    String path = save;
    File file = new File(path);
    PrintWriter writer = new PrintWriter(file);

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == "0" || board[i][j] == null) {
          writer.print(0);
        } else if ((board[i][j]) == "X") {
          writer.print(1);
        } else if ((board[i][j]) == "O") {
          writer.print(2);
        }
        if (j < board[i].length - 1) {
          writer.print(",");
        }
      }
      writer.println();
    }
    writer.print("\n" + currentPlayer);
    writer.close();
  }

  public void readFile(String askUser) throws IOException {
    ArrayList<String[]> lines = new ArrayList<String[]>();
    String line = null;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(askUser))) {
      int i = 0;
      while (((line = bufferedReader.readLine()) != null) && i < 27) {
        lines.add(line.split(","));
        i++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String[][] array = new String[lines.size()][0];
    lines.toArray(array);

    for (int i = 0; i < array.length - 2; i++) {
      for (int j = 0; j < array[i].length; j++) { 
        if (array[i][j].equals("0")) {
          array[i][j] = ("0");
        } else if (array[i][j].equals("1")) {
          array[i][j] = ("X");
        } else if (array[i][j].equals("2")) {
          array[i][j] = ("O");
        }
      }
    }
    board = array;
    r = 1;
  }

}