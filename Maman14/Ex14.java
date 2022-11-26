/**
 * Maman 14
 *
 * @author Yotam Combe
 * @date 26.5.2022
 */

public class Ex14
{
    public static void main(String[] args) {
    }
    
    ////////////////////////////////////QUESTION NO.1/////////////////////////////////////////
   
   /*
    * Paragraph A:
    * 1 - False
    * 2 - False
    * 3 - True
    * 4 - False
    * 5 - True
    * 6 - True
    */

    /**
     * checks if value is inside a matrix
     * the function is in O(n) time complexity because there is only one loop in use and not all of the cells are scanned
     * the function is in O(1) space complexity because there is no saving of objects in the memory, only integers
     * @param an array to scan, value to look for
     * @return true if number is in the array, false if not
     */
    public static boolean findValWhat(int [][] m, int val) {
        if(m[0].length == 0 || val < m[0][0] || val > m[m.length - 1][m[0].length - 1]) //no reason to look inside the matrix
            return false;
        
        int row = 0, col = m[0].length - 1;
        while(row != m.length && col != -1) { //inside the boundaries of the matrix
            if(m[row][col] == val)
                return true;
                
            if(m[row][col] > val) //need to go to lower numbers
                col--;
                
            if(m[row][col] < val) //need to go to higher numbers
                row++;
        }
        
        return false;
    }
    
    /**
     * checks if value is inside a matrix
     * the function is in O(n) time complexity because there is only one loop in use and not all of the cells are scanned
     * the function is in O(1) space complexity because there is no saving of objects in the memory, only integers
     * @param an array to scan, value to look for
     * @return true if number is in the array, false if not
     */
    public static boolean findValTest(int [][] m, int val) {
        if(m[0].length == 0) //no reason to look inside the matrix
            return false;
        
        int row = 0, col = 0;
        while(row < m.length && col < m[0].length) { //inside the boundaries of the matrix
            if(val == m[row][col])
                return true;
            else if(val < m[row][col]) //go to next place in this row
                col++;
            else if(row != m.length - 1 && val >= m[row + 1][col]) { //its not in this row, go to next row
                row++;
                col = 0;
            } else
                col++;
        }
        return false;
    }

    ////////////////////////////////////QUESTION NO.1 DONE////////////////////////////////////

    
    ////////////////////////////////////QUESTION NO.2/////////////////////////////////////////
    /**
     * calculate number of mini ascending arrays inside of one big array
     * the time complexity og the function is O(n), although there is a function inside a function but the i index gets -->
     * --> increased also inside the while loop, therefore the total amount of actions is n, when n is the length of the array
     * the function is in O(1) space complexity because there is no saving of objects in the memory, only integers
     * the function uses a helper function with time complexity of O(1), although there is a loop in this function but -->
     * --> the amount of time it is accuring is depended on the number and not on the length of the array
     * the helper function does not harm the space complexity of the main function because there is no saving of objects -->
     * --> or numbers in the helper function
     * @param an array to check
     * @return number of mini ascending arrays options inside the big one
     */
    public static int strictlyIncreasing(int[] a) {
        if(a.length < 2) //no need to scan the array
            return 0;
            
        int count = 1, sum = 0;
        for(int i = 0; i < a.length - 1; i++, count = 1) {
            while(i < a.length - 1 && a[i] < a[i + 1]) { //got a mini ascending array
                count++;
                i++;
            }
            sum += calculateOptions(count, 0, 2); //adds to sum the total amount of ascending arrays
        }
        return sum;
    }
    
    /**
     * helper function to calculate number of options for each mini accending array
     * @param length of accending array, count and miniArray for saving of storage
     * @return number of mini accending arrays options
     */
    public static int calculateOptions(int len, int count, int lenOfMiniArray) {
        if(len == 1) //no need to get into the loop
            return 0;
          
        while(lenOfMiniArray <= len) { //still adds to count
            if(lenOfMiniArray == len) //one miniArray in the size of len fits in len
                count += 1;
            else
                count = count + len - lenOfMiniArray + 1; //adds number of miniArray len option inside len size array
            lenOfMiniArray++; //in order to calculate next miniArray option
        }
        return count;
    }
    ////////////////////////////////////QUESTION NO.2 DONE////////////////////////////////////

    ////////////////////////////////////QUESTION NO.3/////////////////////////////////////////
    /**
     * calculates to longest Flat sequence in an array
     * @param array of integers
     * @return length of longest Flat sequence inside the array
     */
    public static int longestFlatSequence(int[] arr) {
        if(arr.length == 0) //stopping condition
            return 0;
        
        return longestFlatSequence(arr, 0);
    }
    
    /**
     * calculates to longest Flat sequence in an array
     * @param array of integers, index to start the scan from
     * @return length of longest Flat sequence inside the array
     */
    private static int longestFlatSequence(int[] arr, int index) {
        if(index == arr.length - 1) //stopping condition
            return 1;
        
        int currentMax = Math.max( //splitted lines for readability
            flatLength(arr, index, 1, 0),
            flatLength(arr, index + 1, 1, 0)
            ); //calculates length of each possible Flat sequence in the array
        
        return Math.max( //splitted lines for readability
            currentMax, 
            longestFlatSequence(arr, index + 1)
            ); //always chooses the longer option out of two Flat sequences
    }
    
    /**
     * calculates length of current index Flat sequence
     * @param array of integers, index to start the scan from, count that represents the length of the sequence and -->
     * --> difference to keep track of previous differences between numbers in the sequence
     * @return length of current Flat sequence
     */
    public static int flatLength(int[] arr, int index, int count, int difference) {
        if(index == arr.length - 1) //stopping condition
            return 1;
            
        if(index + count == arr.length) //stopping condition
            return count;
            
        if(difference == 0 && Math.abs(arr[index] - arr[index + count]) < 2)
            difference = arr[index] - arr[index + count]; //grabs the first difference between two different numbers inside -->
            //--> same Flat sequence to make sure that this is the only difference possible for upcoming numbers in this -->
            //--> sequence
            
        if(arr[index] - arr[index + count] != difference && arr[index] - arr[index + count] != 0) //stopping condition
            return count;
            
        return flatLength(arr, index, count + 1, difference);
    }
    ////////////////////////////////////QUESTION NO.3 DONE////////////////////////////////////

    ////////////////////////////////////QUESTION NO.4/////////////////////////////////////////
    /**
     * calculates the highest maximum points route
     * @param matrix array to scan
     * @return points of the highest points route
     */
    public static int findMaximum(int[][] mat) {
        if(mat[0].length == 0 || mat[0][0] == -1) //stopping condition
            return -1;
            
        return findMaximum(mat, 0, 0);
    }
    
    /**
     * calculates the highest maximum points route
     * @param matrix to scan, current row and column indexes
     * @return points of the highest points route
     */
    private static int findMaximum(int[][] mat, int row, int col) {
        if(col == -1 || col == mat[0].length || row == mat.length || mat[row][col] == -1) //all stopping conditions
            return 0;
        
        int points = mat[row][col];
        if(row % 2 == 0)
            points += Math.max( //splitted lines for readability
                findMaximum(mat, row + 1, col),
                findMaximum(mat, row, col + 1)
                ); //if index of row is even, the points gets added with every option possible (right or down movement)
        else
            points += Math.max( //splitted lines for readability
                findMaximum(mat, row + 1, col),
                findMaximum(mat, row, col - 1)
                ); //if row is odd, the points gets added with every option possible (left or down movement)
        
        return points;
    }
    ////////////////////////////////////QUESTION NO.4 DONE////////////////////////////////////

}