/* Write your documentation here
 * 
 */
import java.util.*;

public class Game
{
    public static void main(String[ ] args)
    {
        Scanner in;
        Random rand;
        char choice;
        int toss;
        int row;
        int col;
        int turns;
        boolean winner;
        char winMarker;
        
        Board tictactoe;
        
        final char humanMarker;
        final char computerMarker;
        
        in        = new Scanner(System.in);
        winMarker = ' ';
        rand      = new Random( );
        winner    = false;
        turns     = 0;
        tictactoe = new Board( );
        
        System.out.println("Pick (h)eads or (t)ails");

        choice    = in.next( ).charAt(0 );
        toss      = rand.nextInt(2);

        if (toss == 1 && choice == 'h') {
            humanMarker     = 'X';
            computerMarker  = 'O';
        } else {
            humanMarker     = 'O';
            computerMarker  = 'X';
        }

        System.out.println("You are " + humanMarker + " and the computer is " + computerMarker);
      
        if (humanMarker == 'X') {
            do {
                System.out.print("Enter a row 1 to 3: ");
                row = in.nextInt();

                System.out.print("Enter a column 1 to 3: ");
                col = in.nextInt();
            } while(!tictactoe.humanMoveOK(row, col));

            tictactoe.makeMove(row-1, col-1, humanMarker);
        }

        System.out.println(tictactoe);
        turns++;
        
        while(!winner) {
            tictactoe.computerMove(computerMarker, humanMarker);
            winner = tictactoe.checkForWinner(computerMarker);
            System.out.println("____________________________\n");
            turns++;

            if (winner) {
                winMarker = computerMarker;
            } else if (turns >= 9) {
                System.out.println("Cat. I'll get you next time.....");
                System.exit(0);
            } else {
                do {
                    System.out.println(tictactoe);
                    System.out.print("Enter a row 1 to 3: ");
                    row = in.nextInt( );
                    System.out.print("Enter a column 1 to 3: ");
                    col = in.nextInt( );
                } while(!tictactoe.humanMoveOK(row, col));

                tictactoe.makeMove(row-1, col-1, humanMarker);
                turns++;
                winner = tictactoe.checkForWinner(humanMarker);

                if (winner) {
                    winMarker = humanMarker;
                } else if (turns >= 9) {
                    System.out.println("Cat. I'll get you next time.....");
                    System.exit(0);
                }
            }
          
        } // end of while loop for game 

        System.out.println("___________________________");
        System.out.println(tictactoe + "\n");
        System.out.println("***********\n      " +winMarker + " wins!");
       
    } // end of main()
        
}
