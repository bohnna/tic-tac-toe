import java.util.*;
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
   char [ ] [ ] gameBoard;
   
   public Board( )
   {
       gameBoard = new char[3][3];
       for(int row = 0; row < 3; row++)
        for(int col = 0; col < 3; col++)
            gameBoard[row][col] = ' ';
   }
   
   public boolean checkForWin (char marker, char[ ][ ] board)
   {
       boolean isAWinner = false;
       int consecutive;
       
       consecutive = 0;
       for(int row = 0; row < 3 && consecutive != 3; row++)
       {
         consecutive = 0;
         for(int col = 0; col < 3; col ++)
          if(board[row][col] == marker)
             consecutive++;
       }
       if (consecutive == 3)
         isAWinner = true;
       if(!isAWinner)
       {
          for(int col = 0; col < 3 && consecutive != 3; col++)
           {
             consecutive = 0;
             for(int row = 0; row < 3; row ++)
             if(board[row][col] == marker)
                consecutive++;
           }
           if(consecutive == 3)
              isAWinner = true;
        }
       //put in code for diagonals
       
       return isAWinner;
    } 
    
     public boolean checkForWin (char marker)
   {
       boolean isAWinner = false;
       int consecutive;
       int row;
       int col;
       
       consecutive = 0;
       for( row = 0; row < 3 && consecutive != 3; row++)
       {
         consecutive = 0;
         for(col = 0; col < 3; col ++)
           if(gameBoard[row][col] == marker)
              consecutive++;
       }
       if (consecutive == 3)
         isAWinner = true;
       if(!isAWinner)
          for(col = 0; col < 3 && consecutive != 3; col++)
           {
             consecutive = 0;
             for(row = 0; row < 3; row ++)
               if(gameBoard[row][col] == marker)
                 consecutive++;
           }
       if(consecutive == 3)
          isAWinner = true;
       //put in code for diagonals
       return isAWinner;
    } 
    
    public void computerMove(char marker)
    {
        boolean canComputerWin = false;
        char[ ][ ] copyBoard;
        int row;
        int col;
        int tryRow;
        int tryCol;
        row = 0;
        col = 0;
       //check for a win in a row     
       for(row= 0; row < 3 && !canComputerWin; row++)
       {
           for(col = 0; col < 3 && !canComputerWin; col++)
           {
               if(gameBoard[row][col] == ' ')
               {
                 copyBoard = copyTheBoard( );
                 copyBoard[row][col] = marker;
                 canComputerWin = checkForWin(marker, copyBoard);
               }
           }
        }
        
        if(!canComputerWin)
           for(col = 0; col< 3 && !canComputerWin; col++)
           {
         
              for(row = 0; row < 3&& !canComputerWin; row++)
              {
                 if(gameBoard[row][col] == ' ')
                 {
                   copyBoard = copyTheBoard( );
                   copyBoard[row][col] = marker;
                   canComputerWin = checkForWin(marker, copyBoard);
                  }
               }
            }
       
        if(canComputerWin)
           gameBoard[row-1][col-1] = marker;
        else
        {
            boolean placed = false;
            Random rand = new Random();
            
            do
            {
              tryRow = rand.nextInt(3);
              tryCol = rand.nextInt(3);
              if(gameBoard[tryRow][tryCol] == ' ')
              {
               gameBoard[tryRow][tryCol] = marker;
               placed = true;
               }
            }while(!placed);
         }
          
      }
      
      public boolean humanMoveOK(int row, int col)
      {
          
          return row <= 3 && col <= 3 && gameBoard[row-1][col-1] == ' ';
      }
      
      public void makeMove(int row, int col, char marker)
      {
          gameBoard[row-1][col-1] = marker;
      }
          
         
        
              
              
            
     private char[ ][ ] copyTheBoard( )
     {
         char [ ][ ] temp = new char[3][3];
         for(int row = 0; row < 3; row++)
          for(int col = 0; col < 3; col++)
            temp[row][col] = gameBoard[row][col];
         return temp;
     }
     
     public boolean humanMove(int row, int col, char marker)
     {
         boolean placed;
         if(gameBoard[row-1][col-1] == ' ')
          {
              gameBoard[row-1][col-1] = marker;
              placed = true;
          }
          else
          {
              placed = false;
          }
          return placed;
        }     
              
           
     
     public String toString( )
     {
         String result = "";
         int row;
         int col;
         result += "    1  2   3\n";
         for(row = 0; row < 3; row++)
         {
             result += "\n" + (row+1);
             for(col = 0; col < 3; col++)
             { 
              
               result += "  " + gameBoard[row][col] + " ";
              
             }
           
         }
         return result;
     }     
             
         
         
          
   
   
}
