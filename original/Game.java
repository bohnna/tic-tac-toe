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
        boolean winner;
        char winMarker;
        
        Board tictactoe;
        
        final char humanMarker;
        final char computerMarker;
        
        in = new Scanner(System.in);
        winMarker = ' ';
        rand = new Random( );
        winner = false;
        tictactoe = new Board( );
        
        System.out.println("Pick (h)eads or (t)ails");
        choice = in.next( ).charAt(0 );
        toss = rand.nextInt(2);
        if(toss == 1 && choice == 'h')
        {
         humanMarker = 'X';
         computerMarker = 'O';
        }
        else
        {
            humanMarker = 'O';
            computerMarker = 'X';
        }
        System.out.println("You are " + humanMarker + " and the computer is " + computerMarker);
      
        if(humanMarker == 'X')
        {
            do
            {
              System.out.print("Enter a row 1 to 3: ");
              row = in.nextInt( );
              System.out.print("Enter a column 1 to 3: ");
              col = in.nextInt( );
            }while(!tictactoe.humanMoveOK(row, col));
            tictactoe.makeMove(row, col, humanMarker);
        }
        System.out.println(tictactoe);
        
        while(!winner)
        {
            tictactoe.computerMove(computerMarker);
            System.out.println("____________________________\n");
            System.out.println(tictactoe);
            winner = tictactoe.checkForWin(computerMarker);
            if(winner)
              winMarker = computerMarker;
            else 
            {
              do
              {
                System.out.print("Enter a row 1 to 3: ");
                row = in.nextInt( );
                System.out.print("Enter a column 1 to 3: ");
                col = in.nextInt( );
              }while(!tictactoe.humanMoveOK(row, col));
             tictactoe.makeMove(row, col, humanMarker);
             winner = tictactoe.checkForWin(humanMarker);
             if(winner)
               winMarker = humanMarker;
           }
          
        }//end of while loop for game 
         System.out.println("___________________________");
         System.out.println(tictactoe + "\n");
        System.out.println("***********\n      " +winMarker + " wins!");
       
    } //end of main()
        
}