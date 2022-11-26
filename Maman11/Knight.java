
/**
 * @author (Yotam Combe)
 * @version (10.3.2022)
 */

import java.util.Scanner;

public class Knight
{
    public static void main(String [] args) 
    {
        final int bottomLim = 1;
        final int upperLim = 8; //defining the borders
        
        Scanner scan = new Scanner(System.in);
        System.out.println ("This program reads two integers which " + "represent the knight's location on the chess board: ");
        System.out.println ("Please enter the number of row");
        int row = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col = scan.nextInt(); //you gave us the code until here
        
        if ((row < bottomLim || row > upperLim) || (col < bottomLim || col > upperLim))
            System.out.println("input is illegal"); //if one of the positions is not valid the program will stop here
        else
        {
            System.out.println("Moves:"); //heading for the output
            if (row - 2 > 0 && col - 1 > 0)
                System.out.println((row - 2) + " " + (col - 1));
            if (row - 1 > 0 && col - 2 > 0)
                System.out.println((row - 1) + " " + (col - 2));
            if (row + 2 < 9 && col + 1 < 9)
                System.out.println((row + 2) + " " + (col + 1));
            if (row + 1 < 9 && col + 2 < 9)
                System.out.println((row + 1) + " " + (col + 2));
            if (row - 2 > 0 && col + 1 < 9)
                System.out.println((row - 2) + " " + (col + 1));
            if (row - 1 > 0 && col + 2 < 9)
                System.out.println((row - 1) + " " + (col + 2));
            if (row + 2 < 9 && col - 1 > 0)
                System.out.println((row + 2) + " " + (col - 1));
            if (row + 1 < 9 && col - 2 > 0)
                System.out.println((row + 1) + " " + (col - 2)); //checking if any position exists and if it does, the prodram prints it
        }
    } //end of method main
} //end of class Knight
