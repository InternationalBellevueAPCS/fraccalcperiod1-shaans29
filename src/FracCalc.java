import java.util.*;
public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in); 
    	System.out.println("Please enter the operation you would like.");
    	String operation = "";
    	operation = console.nextLine();
    	while (!operation.equals("quit"))
    	{
    	String secondoperand = produceAnswer(operation);
    	System.out.println(secondoperand);
    	System.out.println("Please enter another operation.");
		operation = console.nextLine();
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    	}
    }
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
    	int x= input.indexOf(" ");
    	
    	String first = input.substring(0,x+1);
    	String space = input.substring(x+1);
    	int space2=space.indexOf(" ");
    	String operator=space.substring(0, space2+1);
    	String secondoperand =space.substring(space2+1);
    	int slash = secondoperand.indexOf("/");
    	int underscore = secondoperand.indexOf("_");
    	int denom = 0;
    	int num = 0;
    	int numerator = 0;
    	String numS = ("");
    	String denomS= ("");
    	String numeratorS = ("");
    	if (slash != -1 && underscore>0) {
			numS = secondoperand.substring(0, underscore);
			numeratorS = secondoperand.substring(underscore + 1, slash);
			denomS = secondoperand.substring(slash + 1, secondoperand.length());
		} else if (slash == -1) {
			numS = secondoperand;
			numeratorS = ("0");
			denomS = ("1");
		} else if (underscore == -1) {
			numS = ("0");
			numeratorS = secondoperand.substring(underscore + 1, slash);
			denomS = secondoperand.substring(slash + 1, secondoperand.length());
		}
		num = Integer.parseInt(numS);
		numerator = Integer.parseInt(numeratorS);
		denom = Integer.parseInt(denomS);
		secondoperand=("whole:" + num + " " + "numerator:" + numerator + " " + "denominator:" + denom );
		return secondoperand;
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
    }

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
