
/**
 * @author (Yotam Combe)
 * @version (10.3.2022)
 */

import java.util.Scanner;

public class Chess
{
    public static void main(String [] args)
    {
        final int bottomLim = 1;
        final int upperLim = 8; //defining the borders
        int i = 0;
        
        Scanner scan = new Scanner (System.in);
        System.out.println("Please enter the type" + " of the first chessman");
        char first = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row1 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col1 = scan.nextInt();
        
        System.out.println("Please enter the type" + " of the second chessman");
        char second = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row2 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col2 = scan.nextInt(); //you gave us the code until here
        
        if (first == second) //if the Chessmen identical
            System.out.println("Chessmen should be different from each other");
        else
        {
            if ((row1 < bottomLim || row1  > upperLim) || (col1 < bottomLim || col1 > upperLim) || (row2 < bottomLim || row2  > upperLim) || (col2 < bottomLim || col2 > upperLim))
            //if the positions are nor legal
                System.out.println("Position is not legal");
            else
            {
                if (row1 == row2 && col1 == col2) //if the positions of the Chessmen identical
                    System.out.println("Chessmen positions should not be identical");
                else
                {
                    switch(first) //checking first the three options for the first Chessman
                    {
                        case 'k':
                        {
                            if ((row1 - 2 == row2 && col1 - 1 == col2) || (row1 + 2 == row2 && col1 + 1 == col2) || (row1 - 2 == row2 && col1 + 1 == col2) ||
                            (row1 + 2 == row2 && col1 - 1 == col2) || (row1 - 1 == row2 && col1 - 2 == col2) || (row1 + 1 == row2 && col1 + 2 == col2) ||
                            (row1 - 1 == row2 && col1 + 2 == col2) || (row1 + 1 == row2 && col1 - 2 == col2)) //covering every scenario for the knight to eat the other Chessman
                            {
                                i++;
                                if (second == 'b')
                                    System.out.println("knight threats bishop");
                                if (second == 'r')
                                    System.out.println("knight threats rook");
                            }
                            break;
                        }
                        case 'b':
                        {
                            if (Math.abs(row1 - row2) == Math.abs(col1 - col2)) //using abs helps here to know if the Chessmen in the same diagonal line
                            {
                                i++;
                                if (second == 'k')
                                    System.out.println("bishop threats knight");
                                if (second == 'r')
                                    System.out.println("bishop threats rook"); 
                            }
                            break;
                        }
                        case 'r':
                        {
                            if (row1 == row2 || col1 == col2) //if the Chessmen have the same row or column and one of them is a rook - the other one is in trouble
                            {
                                i++;
                                if (second == 'k')
                                    System.out.println("rook threats knight");
                                if (second == 'b')
                                    System.out.println("rook threats bishop");
                            }
                            break;
                        } //so far we covered every situation for the first Chessman to threatens the second Chessman
                    }
                    if (i == 0) //now, if the first Chessman is not threatening the second Chessman, we will be able to continue
                    {
                        switch (second) //we will cover every senario where the second Chessman theatens the first Chessman now
                        {
                            case 'k':
                            {
                                if ((row2 - 2 == row1 && col2 - 1 == col1) || (row2 + 2 == row1 && col2 + 1 == col1) || (row2 - 2 == row1 && col2 + 1 == col1) ||
                                (row2 + 2 == row1 && col2 - 1 == col1) || (row2 - 1 == row1 && col2 - 2 == col1) || (row2 + 1 == row1 && col2 + 2 == col1) || 
                                (row2 - 1 == row1 && col2 + 2 == col1) || (row2 + 1 == row1 && col2 - 2 == col1))
                                {
                                    i++;
                                    if (first == 'b')
                                        System.out.println("knight threats bishop");
                                    if (first == 'r')
                                        System.out.println("knight threats rook");
                                }
                                break;
                            }
                            case 'b':
                            {
                                if (Math.abs(row1 - row2) == Math.abs(col1 - col2))
                                {
                                    i++;
                                    if (first == 'k')
                                        System.out.println("bishop threats knight");
                                    if (first == 'r')
                                        System.out.println("bishop threats rook"); 
                                }
                                break;
                            }
                            case 'r':
                            {
                                if (row1 == row2 || col1 == col2)
                                {
                                    i++;
                                    if (first == 'k')
                                        System.out.println("rook threats knight");
                                    if (first == 'b')
                                        System.out.println("rook threats bishop");
                                }
                                break;
                            } //if every senario has npt happened so far, there is no risk for sure
                        }
                        
                        if (i == 0) //if there is no risk, int i should still be equal to zero
                            System.out.println("no threat");
                    }
                }
            }
        }
    } //end of method main
} //end of class Chess
