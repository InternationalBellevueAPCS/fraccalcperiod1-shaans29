import java.util.Scanner;
public class FracCalc {

	/**
	 * Prompts user for input, passes that input to produceAnswer, then outputs the result.
	 * @param args - unused
	 */
	public static void main(String[] args) 
    {
       Scanner console = new Scanner(System.in); //user inputs their equation
       System.out.print("Type in your equation (type quit to stop): ");
       String equation = console.nextLine();
       while (equation!="x") {
             System.out.println(produceAnswer(equation));
             System.out.print("Type in an equation (press quit to stop): ");
             equation = console.nextLine();
             if (equation.equals("quit")) { //if user enters quit, breaks from code
                    break;
             }
       }
             System.out.print("");
             
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
		String op="";
		String op1="";
		String op2="";
		//Tests
		int test=0;
		int counts=0;
		while(test==0) {
			if(input.charAt(counts)==' '){
				test=1;
				op=input.substring(counts+1,counts+2);
				op1=input.substring(0,counts);
				op2=input.substring(counts+3);
			}
			counts++;
		}
		//numerator + whole number + denominator of op1
		int _Index=op1.indexOf("_");
		int slash_Index1=op1.indexOf("/");
		int Index1=0;
		int num1=0;
		int whole1=0; 
		int denom1=1;

		if(slash_Index1!=-1){
			if(_Index!=-1) {
				whole1=Integer.parseInt(op1.substring(0,_Index));
				Index1=_Index+1;
			}
			num1=Integer.parseInt(op1.substring(Index1,slash_Index1));
			denom1=Integer.parseInt(op1.substring(slash_Index1+1));
		}else {
			whole1=Integer.parseInt(op1);
		}
		////whole number + numerator + denominator of op2
		int _Index2=op2.indexOf("_");
		int slash_Index2=op2.indexOf("/");
		int Index2=0;
		int num2=0;
		int whole2=0; 
		int denom2=1;

		if(slash_Index2!=-1){
			if(_Index2!=-1) {
				whole2=Integer.parseInt(op2.substring(0,_Index2));
				Index2=_Index2+1;
			}
			num2=Integer.parseInt(op2.substring(Index2,slash_Index2));
			denom2=Integer.parseInt(op2.substring(slash_Index2+1));
		}else {
			whole2=Integer.parseInt(op2);
		}
		//fractions are converted to the  correct sign (positive or negative)
		if(whole1<0) {
			num1*=-1;
		}
		if(whole2<0) {
			num2*=-1;
		}
		//mixed numbers are converted into improper fractions
		num1+=whole1*denom1;
		num2+=whole2*denom2;

		int numFinal=0;
		int denomFinal=0;
		//executes based on the sign 
		if(op.equals("*")) {
			numFinal=num1*num2;
			denomFinal=denom1*denom2;
		}else if(op.equals("/")) {
			numFinal=num1*denom2;
			denomFinal=num2*denom1;
		}else if(op.equals("+")) {
			numFinal=num1*denom2+num2*denom1;
			denomFinal=denom1*denom2;
		}else if(op.equals("-")){
			numFinal=num1*denom2-num2*denom1;
			denomFinal=denom1*denom2;
		}
		//denominator is not negative
		if(denomFinal<0) {
			numFinal*=-1;
			denomFinal*=-1;
		}
		//fraction is reduced by finding the greatest common divisor
		int gcd=greatestCommonDivisor(numFinal,denomFinal);
		numFinal/=gcd;
		denomFinal/=gcd;
		//improper fraction is converted to a mixed number 
		int wholeFinal=0;
		if(numFinal>0) {
			for(;numFinal>denomFinal;numFinal-=denomFinal) {
				wholeFinal++;
			}
			//improper negative fraction turns into mixed number 
		}else{ 	
			for(;Math.abs(numFinal)>denomFinal;numFinal+=denomFinal) {
				wholeFinal--;
			}
		}
		//Checks to see if the entered fraction is whole
		if(denomFinal==1) {
			wholeFinal+=numFinal;
			numFinal=0;
		}
		//Returns the final output
		if(numFinal==0) {
			return wholeFinal+"";
		}
		else if(wholeFinal!=0) {
			return wholeFinal+"_"+Math.abs(numFinal)+ "/" + denomFinal;
		}else {
			return numFinal + "/" + denomFinal;
		}
	}


	// TODO: Implement this function to produce the solution to the input
	// Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
	// Checkpoint 2: Return the second operand as a string representing each part.
	//               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
	// Checkpoint 3: Evaluate the formula and return the result as a fraction.
	//               Example "4/5 * 1_2/4" returns "6/5".
	//               Note: Answer does not need to be reduced, but it must be correct.
	// Final project: All answers must be reduced.
	//               Example "4/5 * 1_2/4" returns "1_1/5".



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