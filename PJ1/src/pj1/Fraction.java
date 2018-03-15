/*************************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are integers.
 *
 * Requirements:
 *      Implement interfaces: FractionInterface and Comparable (i.e. compareTo())
 *      Implement methods equals() and toString() from class Object
 *
 *      Should work for both positive and negative fractions
 *      Must always reduce fraction to lowest term 
 *      For numerator be such as 3/-10, it is same as -3/10 (see hints 2. below)
 *      Must display negative fraction as -x/y,
 *         example: (-3)/10 or 3/(-10), must display as -3/10
 *      Must throw FractionException in case of errors, do not throw other types of exception objects
 *      Must not add new or modify existing data fields
 *      Must not add new public methods
 *      May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 *    the numerator and the denominator by their greatest common denominator.
 *    The greatest common denominator of 4 and 8 is 4, so when you divide
 *    the numerator and denominator of 4/8 by 4, you get the fraction 1/2.
 *    The recursive algorithm which finds the greatest common denominator of
 *    two positive integers is implemented (see code)
 *       
 * 2. It will be easier to determine the correct sign of a fraction if you force
 *    the fraction's denominator to be positive. However, your implementation must 
 *    handle negative denominators that the client might provide.
 *           
 * 3. You need to downcast reference parameter FractionInterface to Fraction if  
 *    you want to use it as Fraction. See add, subtract, multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed
 *
 ************************************************************************************/


package pj1;

/**
 *
 * @author Gabriel Equitz
 */
public class Fraction implements FractionInterface, Comparable<Fraction> {
    
 
	private	int numerator;	
	private	int denominator;	

	public Fraction()
	{
		// set fraction to default = 0/1
            numerator = 0;
            denominator = 1;
	}	// end default constructor

	public Fraction(int num, int den)
	{
		// implement this method!
            // constructor
            
            numerator = num;
            denominator = den; 
            
            if (denominator == 0){
                throw new FractionException("Denominator is zero!");
              //  RuntimeException.FractionException();
            }
            
   
            
	}	// end constructor

        
        
	public void setFraction(int num, int den)
	{
		// implement this method!
		// return FractionException if initial Denominator is 0
            numerator = num;
            denominator = den;
            if(denominator < 0) {
              numerator *= -1;
              denominator *= -1;
            }
            if (denominator == 0){
                throw new FractionException("Denominator is zero!");
              //  RuntimeException.FractionException();
            }
	}	// end setFraction

	public double toDouble()
	{
		// return double floating point value
		// implement this method!
               
                return (double) numerator/(double) denominator;
	}	// end toDouble 

        
       // }
	public FractionInterface add(FractionInterface aFraction)
	{
                // return a new Fraction object
                // a/b + c/d is (ad + cb)/(bd)
                // implement this method!
               
             
                int tempNum = (numerator*((Fraction)aFraction).denominator) + (((Fraction)aFraction).numerator*denominator);
		int tempDen = denominator*((Fraction)aFraction).denominator;
               
                
              
                numerator = tempNum;
                denominator = tempDen;
                reduceFractionToLowestTerms();
                
                
                return(new Fraction(numerator, denominator));
                
                //return null;
	}	// end add

	public FractionInterface subtract(FractionInterface aFraction)
	{
                // return a new Fraction object
                // a/b - c/d is (ad - cb)/(bd)
                // implement this method!
                int tempNum = (numerator*((Fraction)aFraction).denominator) - (((Fraction)aFraction).numerator*denominator);
		int tempDen = denominator*((Fraction)aFraction).denominator;
               
               
                numerator = tempNum;
                denominator = tempDen;
                reduceFractionToLowestTerms();
                
                
                return(new Fraction(numerator, denominator));
                
                //return null;
	}	// end subtract

	public FractionInterface multiply(FractionInterface aFraction)
	{
                // return a new Fraction object
                // a/b * c/d is (ac)/(bd)
                // implement this method!
                int tempNum = (numerator*((Fraction)aFraction).numerator) ;
		int tempDen = denominator*((Fraction)aFraction).denominator;
                
               
                numerator = tempNum;
                denominator = tempDen;
                
                reduceFractionToLowestTerms();
                
                
                return(new Fraction(numerator, denominator));
                
                //return null;
	}	// end multiply

	public FractionInterface divide(FractionInterface aFraction)
	{
                // return a new Fraction object
                // return FractionException if aFraction is 0
                // a/b / c/d is (ad)/(bc)
                // implement this method!
                int tempNum = (numerator*((Fraction)aFraction).denominator) ;
		int tempDen = denominator*((Fraction)aFraction).numerator;
               
                
                
                numerator = tempNum;
                denominator = tempDen;
                
                
             //   if (denominator == 0){
             //     throw new FractionException("Denominator is zero!");
              //  RuntimeException.FractionException();
             //   }
                reduceFractionToLowestTerms();
                return(new Fraction(numerator, denominator));
                
               // return null;
	}	// end divide

	public FractionInterface getReciprocal() 
	{
                // return a new Fraction object
                // return FractionException if new Fraction is 0
                // implement this method!
                //return null;
            
            
          
               int tempNum = denominator;
               int tempDen = numerator; 
               
               
               numerator = tempNum;
               denominator = tempDen;
               if (denominator == 0){
                throw new FractionException("Denominator is zero!");
              //  RuntimeException.FractionException();
               }
               if(denominator < 0) {
                 numerator *= -1;
                 denominator *= -1;
               }
               return(new Fraction(numerator, denominator));
          
            
          
	} // end getReciprocal


	public boolean equals(Object other)
	{
              
         
            boolean result = false;
            
            if (other instanceof Fraction){
              Fraction aFraction = (Fraction)other;  
             
              double nf1 = (double)numerator;
              double df1 = (double)denominator;
              double nf2 = (double)(aFraction.numerator);
              double df2 = (double)(aFraction.denominator);
              
              if (nf1/df1 == nf2/df2){
                  result = true;
                 
              }
                
            }
            return result;
          
            
	} // end equals


	public int compareTo(Fraction other)
	{
                // implement this method!
               // reduceFractionToLowestTerms();
		double d1Numerator= numerator, d1Denominator = denominator;
		double d2Numerator = other.numerator, d2Denominator = other.denominator;
                
		if((d1Numerator/d1Denominator)<(d2Numerator/d2Denominator)){
                       
			return -1;
		} else if((d1Numerator/d1Denominator)>(d2Numerator/d2Denominator)){
                        
			return 1;
		} else{
                        
			return 0;
		} 
                
                
                
                
                
	} // end compareTo

	
	public String toString()
	{
		return numerator + "/" + denominator;
	} // end toString


	/** Task: Reduces a fraction to lowest terms. */

        //-----------------------------------------------------------------
        //  private methods start here
        //-----------------------------------------------------------------

	private void reduceFractionToLowestTerms()
	{
                // implement this method!
                //
                // Outline:
                // compute GCD of numerator & denominator
                // GCD works for + numeratorbers.
                // So, you should eliminate - sign
                // then reduce numeratorbers : numerator/GCD and denominator/GCD
		
            if (denominator == 0){
                  throw new FractionException("Denominator is zero!");
              //  RuntimeException.FractionException();
            }
            
            if(denominator < 0){
                denominator = -denominator ;
                numerator = -numerator;
            }
		int gcd = GCD(Math.abs(numerator), Math.abs(denominator));

		numerator = numerator / gcd;
		denominator = denominator / gcd;
	}	// end reduceFractionToLowestTerms

  	/** Task: Computes the greatest common divisor of two integers.
	 *  @param integerOne	 an integer
	 *  @param integerTwo	 another integer
	 *  @return the greatest common divisor of the two integers */
	private int GCD(int integerOne, int integerTwo)
	{
		 int result;

		 if (integerOne % integerTwo == 0)
			result = integerTwo;
		 else
			result = GCD(integerTwo, integerOne % integerTwo);

		 return result;
	}	// end GCD

       public static void main(String[] args) {
        {
		FractionInterface firstOperand = null;
		FractionInterface secondOperand = null;
		FractionInterface result = null;
                double doubleResult = 0.0;

		Fraction nineSixteenths = new Fraction(9, 16);	// 9/16
		Fraction oneFourth = new Fraction(1, 4);        // 1/4

		System.out.println("\n=========================================\n");
		// 7/8 + 9/16
		firstOperand = new Fraction(7, 8);
		result = firstOperand.add(nineSixteenths);
		System.out.println("The sum of " + firstOperand + " and " +
				nineSixteenths + " is \t\t" + result);
		System.out.println("\tExpected result :\t\t23/16\n");

		// 9/16 - 7/8
		firstOperand = nineSixteenths;
		secondOperand = new Fraction(7, 8);
		result = firstOperand.subtract(secondOperand);
		System.out.println("The difference of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);
		System.out.println("\tExpected result :\t\t-5/16\n");


		// 15/-2 * 1/4
		firstOperand = new Fraction(15, -2); 
		result = firstOperand.multiply(oneFourth);
		System.out.println("The product of " + firstOperand	+
				" and " +	oneFourth + " is \t" + result);
		System.out.println("\tExpected result :\t\t-15/8\n");

		// (-21/2) / (3/7)
		firstOperand = new Fraction(-21, 2); 
		secondOperand= new Fraction(3, 7); 
		result = firstOperand.divide(secondOperand);
		System.out.println("The quotient of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);
		System.out.println("\tExpected result :\t\t-49/2\n");

		// -21/2 + 7/8
		firstOperand = new Fraction(-21, 2); 
		secondOperand= new Fraction(7, 8); 
		result = firstOperand.add(secondOperand);
		System.out.println("The sum of " + firstOperand	+
				" and " +	secondOperand + " is \t\t" + result);
		System.out.println("\tExpected result :\t\t-77/8\n");


                // 0/10, 5/(-15), (-22)/7
		firstOperand = new Fraction(0, 10); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t0.0\n");
		firstOperand = new Fraction(1, -3); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t-0.333333333...\n");
		firstOperand = new Fraction(-22, 7); 
                doubleResult = firstOperand.toDouble();
		System.out.println("The double floating point value of " + firstOperand	+ " is \t" + doubleResult);
		System.out.println("\tExpected result \t\t\t-3.142857142857143");
		System.out.println("\n=========================================\n");
		firstOperand = new Fraction(-21, 2); 
		System.out.println("First = " + firstOperand);
		// equality check
		System.out.println("check First equals First: ");
		if (firstOperand.equals(firstOperand))
			System.out.println("Identity of fractions OK");
		else
			System.out.println("ERROR in identity of fractions");

		secondOperand = new Fraction(-42, 4); 
		System.out.println("\nSecond = " + secondOperand);
		System.out.println("check First equals Second: ");
		if (firstOperand.equals(secondOperand))
			System.out.println("Equality of fractions OK");
		else
			System.out.println("ERROR in equality of fractions");

		// comparison check
		Fraction first  = (Fraction)firstOperand;
		Fraction second = (Fraction)secondOperand;
		
		System.out.println("\ncheck First compareTo Second: ");
		if (first.compareTo(second) == 0)
			System.out.println("Fractions == operator OK");
		else
			System.out.println("ERROR in fractions == operator");

		second = new Fraction(7, 8);
		System.out.println("\nSecond = " + second);
		System.out.println("check First compareTo Second: ");
		if (first.compareTo(second) < 0)
			System.out.println("Fractions < operator OK");
		else
			System.out.println("ERROR in fractions < operator");

		System.out.println("\ncheck Second compareTo First: ");
		if (second.compareTo(first) > 0)
			System.out.println("Fractions > operator OK");
		else
			System.out.println("ERROR in fractions > operator");

		System.out.println("\n=========================================");

		System.out.println("\ncheck FractionException: 1/0");
		try {
			Fraction a1 = new Fraction(1, 0);		    
		        System.out.println("Error! No FractionException");
		}
		catch ( FractionException fe )
           	{
              		System.err.printf( "Exception: %s\n", fe );
           	} // end catch
		System.out.println("Expected result : FractionException!\n");

		System.out.println("\ncheck FractionException: division");
		try {
			Fraction a2 = new Fraction();		    
			Fraction a3 = new Fraction(1, 2);		    
			a3.divide(a2);
		        System.out.println("Error! No FractionException");
		}
		catch ( FractionException fe )
           	{
              		System.err.printf( "Exception: %s\n", fe );
           	} // end catch
		System.out.println("Expected result : FractionException!\n"); 

    //        static private Scanner scanner;

   
    
 
    }
    }
	
}
