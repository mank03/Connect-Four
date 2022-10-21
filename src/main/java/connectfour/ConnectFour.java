// package connectfour;

// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.Scanner;

// // public class ConnectFour{

// // }

// /**
//  * This enumeration defines the two player colors.
//  * 
//  * @author Manu Konnur
//  * @version 1.6 (Jun 28, 2016)
//  */
// public class ConnectFour {
//     // private static final String p1 = "X";
//     // private static final String p2 = "O";
//     // RED ("X");
//     // YELLOW ("O");

//     //make getters and setters for ALL INSTANCE VARIABLES

//     private String string;
//     private Board board = new Board();
//     private TextUI text = new TextUI();
//     private String currentPlayer;
//     // public int v = 0;
//     private int q;

//     private static Scanner scanner = new Scanner(System.in);

//     private static final String RED_WINS_MESSAGE = "[GAME OVER] The red player wins!";

//     private static final String YELLOW_WINS_MESSAGE = "[GAME OVER] The yellow player wins!";    

//     private static final String TIE_MESSAGE = "[GAME OVER] It's a tie!";
    
//     @Override
//     public String toString() {
//         return string;
//     }

//     public void setUser() throws IOException{
//       // System.out.println("here1\n");
//       int user = text.askUser();
//       if(user == 1){
//           board.readFile();
//       }
//       q = user;
//     }

//   public int getUser(){
//       return q;
//   }

//     public String setTurn(String playerTurn) {
//         if (playerTurn == "X") {
//           return "O";
//         } else {
//           return "X";
//         }
//     }

//     public String checkWin(int i) throws FileNotFoundException{
//       currentPlayer = "X";
//       // System.out.println("i #3: " + i);

//       while (true) {
            
//         String winnerColor = board.checkVictory(i);


//         // System.out.println("6. v is: " + v);

//         // if(v==1)
//         //     {
//             if (winnerColor == "X"|| winnerColor == "O" || board.isFull(i)) {
//                 return winnerColor;
//             }
//         // }
//         // else{
//         //     if (winnerColor != null || board.isFull()) {
//         //         return winnerColor;
//         //     }
//         // }

//         final int column = text.readPly(i);
//         board.put(column, currentPlayer, i);
        
//         String x = this.setTurn(currentPlayer);
//         //System.out.println("x is " + x);
//         // String x = game.invert();
//         currentPlayer = x;
//         text.printBoard();
//     }
//     }

//       public String reportOutcome(final String winnerColor) {
//         if(winnerColor == "X") {
//           return RED_WINS_MESSAGE;
//         } else if(winnerColor == "O") {
//           return YELLOW_WINS_MESSAGE;
//         } else{
//           return TIE_MESSAGE;
//         }
//         }

//       public boolean newGameRequested() {
//           System.out.println("new game?: ");
//           final String input = scanner.next().trim().toLowerCase();
//           return !input.equals("no");
//       }
  
//       public static void main(final String[] args) throws Exception {
//         TextUI text = new TextUI();
//         // new ConnectFour();
        

//         while(true){
//           ConnectFour main = new ConnectFour();
//           Board board = new Board();
//           main.setUser();
//           int i = main.getUser();
//           board.getUserInput(i);
//           text.run(i);
//           if (!main.newGameRequested()) {
//             break;
//           }

//         }
//           System.out.println("thanks for playing!\n");

//     }
// }





package connectfour;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// public class ConnectFour{

// }

/**
 * This enumeration defines the two player colors.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jun 28, 2016)
 */
public class ConnectFour {
    // RED ("X");
    // YELLOW ("O");

    private String string;
    private Board board = new Board();
    private static TextUI text = new TextUI();
    private String currentPlayer;
    private int v = 0;
    private int q;

    private Scanner scanner = new Scanner(System.in);

    private static final String RED_WINS_MESSAGE = "[GAME OVER] The red player wins!";

    private static final String YELLOW_WINS_MESSAGE = "[GAME OVER] The yellow player wins!";    

    private static final String TIE_MESSAGE = "[GAME OVER] It's a tie!";
    
    @Override
    public String toString() {
        return string;
    }

    public void setUser() throws IOException{
      // System.out.println("here1\n");
      int user = text.askUser();
      if(user == 1){
          Board.readFile();
      }
      q = user;
  }

  public int getUser(){
      return q;
  }

    public String setTurn(String playerTurn) {
        if (playerTurn == "X") {
          return "O";
        } else {
          return "X";
        }
    }

    public String checkWin(int i) throws FileNotFoundException{
      currentPlayer = "X";
      // System.out.println("i #3: " + i);

      while (true) {
            
        String winnerColor = board.checkVictory(i);


        // System.out.println("6. v is: " + v);

        // if(v==1)
        //     {
            if (winnerColor == "X"|| winnerColor == "O" || board.isFull(i)) {
                return winnerColor;
            }
        // }
        // else{
        //     if (winnerColor != null || board.isFull()) {
        //         return winnerColor;
        //     }
        // }

        final int column = text.readPly(i);
        board.put(column, currentPlayer, i);
        
        String x = this.setTurn(currentPlayer);
        //System.out.println("x is " + x);
        // String x = game.invert();
        currentPlayer = x;
        text.printBoard();
    }
    }

      public String reportOutcome(final String winnerColor) {
        if(winnerColor == "X") {
          return RED_WINS_MESSAGE;
        } else if(winnerColor == "O") {
          return YELLOW_WINS_MESSAGE;
        } else{
          return TIE_MESSAGE;
        }
        }

      // public static boolean newGameRequested() {
      //     System.out.println("new game?: ");
      //     final String input = scanner.next().trim().toLowerCase();
      //     return !input.equals("no");
      // }
  
      public static void main(final String[] args) throws Exception {
        
        

        // while(true){
          ConnectFour main = new ConnectFour();
          Board board = new Board();
          main.setUser();
          int i = main.getUser();
          board.getUserInput(i);
          text.run(i);
          // if (!newGameRequested()) {
          //   break;
          // }

        // }
          System.out.println("thanks for playing!\n");

    }
}

