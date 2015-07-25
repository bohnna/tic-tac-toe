import java.util.*;
/**
 * Write a description of class Board here.
 * 
 * @author BUNZ 
 * @version 1.BUNZ
 */
public class Board {
    char [][] gameBoard;
   
    public Board() {
        gameBoard = new char[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gameBoard[row][col] = ' ';
            }
        }
    }
   
    public boolean checkForWinner (char marker) {
        boolean isAWinner = false;
        int consecutive   = 0;
        int col;
        int row;

        for (row = 0; row < 3 && consecutive != 3; row++) {
            consecutive = 0;

            for (col = 0; col < 3; col ++) {
                if (gameBoard[row][col] == marker) {
                    consecutive++;
                }
            }

        }

        if (consecutive == 3) {
            isAWinner = true;
        } else {

            if (gameBoard[1][1] == marker) {

                for (row = 0; row < 3; row += 2) {
                    if (gameBoard[row][0] == marker) {

                        if (row == 0 && gameBoard[2][2] == marker) {
                            isAWinner = true;
                            break;
                        } else if (row == 2 && gameBoard[0][2] == marker) {
                            isAWinner = true;
                            break;
                        }

                    }
                }

                if (!isAWinner) {
                    for (col = 0; col < 3 && consecutive != 3; col++) {
                        consecutive = 0;

                        for (row = 0; row < 3; row ++) {

                            if (gameBoard[row][col] == marker) {
                                consecutive++;
                            }
                        }

                    }

                    if (consecutive == 3) {
                        isAWinner = true;
                    }
                }
            }
        }

        return isAWinner;
    } 
    
    public void computerMove(char marker, char humanMarker) {

        boolean canComputerWin  = false;
        boolean canHumanWin     = false;
        Board copyBoard;
        int row;
        int col;
        int tryRow              = 0;
        int tryCol              = 0;
        int[] humanWin          = checkForWin(humanMarker);
        int[] computerWin       = checkForWin(marker);

        if (computerWin[0] > 0) {
            System.out.println("Potential win detected at " + computerWin[0] + "x" + computerWin[1] + ". Going for gold!");
            gameBoard[computerWin[0]-1][computerWin[1]-1] = marker;
        } else if (humanWin[0] > 0) {
            System.out.println("Potential win detected at " + humanWin[0] + "x" + humanWin[1] + ". Initiating Block!");
            gameBoard[humanWin[0]-1][humanWin[1]-1] = marker;
        } else {
            System.out.println("No wins detected. Randomizing spot...");

            boolean placed  = false;
            Random rand     = new Random();
            
            do {
                tryRow = rand.nextInt(3);
                tryCol = rand.nextInt(3);

                if (gameBoard[tryRow][tryCol] == ' ') {
                    gameBoard[tryRow][tryCol] = marker;
                    placed = true;
                }

            } while(!placed);
        }
    }

    private int[] checkForWin(char marker) {
      Board copyBoard;

      boolean canWin    = false;
      int row;
      int col;
      row               = 0;
      col               = 0;
      int[] winPosition = new int[2];
      

      for (row = 0; row < 3 && !canWin; row++) {
          for(col = 0; col < 3 && !canWin; col++) {

              if (gameBoard[row][col] == ' ') {
                  copyBoard           = copyTheBoard();
                  copyBoard.makeMove(row, col, marker);
                  canWin              = copyBoard.checkForWinner(marker);

                  if (canWin) {
                    System.out.println("Win Detected at " + row + "x" + col);
                    winPosition[0] = row+1;
                    winPosition[1] = col+1;
                  }
              }
          }
      }

      if (!canWin) {

          for (col = 0; col < 3 && !canWin; col++) {
              for (row = 0; row < 3 && !canWin; row++) {

                  if (gameBoard[row][col] == ' ') {
                      copyBoard           = copyTheBoard();
                      copyBoard.makeMove(row, col, marker);
                      canWin              = copyBoard.checkForWinner(marker);

                      if (canWin) {
                          System.out.println("Win Detected at " + row + "x" + col);
                          winPosition[0] = row+1;
                          winPosition[1] = col+1;
                      }
                  }

              }
          }
      }

      return winPosition;

    }

    public boolean humanMoveOK(int row, int col) {
        if (row > 0 && row <= 3 && col > 0 && col <= 3 && gameBoard[row-1][col-1] == ' ') {
            return true;
        } else {
            System.out.print("Invalid selection. Try again. \n");
            return false;
        }
    }
    
    public void makeMove(int row, int col, char marker) {
        gameBoard[row][col] = marker;
    }
            
    private Board copyTheBoard() {
        Board temp;
        temp = new Board();

        for (int row = 0; row < 3; row++) {
          for (int col = 0; col < 3; col++) {
              temp.makeMove(row,col, gameBoard[row][col]);
          }
        }

        return temp;
    }
     
    public String toString( ) {
        String result = "";
        int row;
        int col;

        result += "    1  2   3\n";

        for(row = 0; row < 3; row++) {
            result += "\n" + (row+1);

            for (col = 0; col < 3; col++) {
                result += "  " + gameBoard[row][col] + " ";
            }
        }

        return result;
    }     
}
