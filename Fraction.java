/**
 * Program Name: Fraction.java
 * Purpose: This class contains some methods which are used in GUI Calculator
 * Coder: Sandeep Singh (ID: 0869722), Mihyeong(Sue) Koh (ID: 0863496)
 * Date: Jul 26, 2018
 */

public class Fraction implements Comparable<Fraction>{

	//declare variables 
	private int num;
	private int den;
	public static final int DIVISOR = 2;
	
	//default constructor 
	Fraction(){
		this.num = 1;
		this.den = 1;
	}
	//custom constructor
	Fraction(int num, int den)
	{
		this.num = num;
		this.den = den;
	}
	
	//getters & setters 
	public int getNum()
	{
		return this.num;
	}
	
	public int getden()
	{
		return this.den;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}
	
	public void setden(int den)
	{
		this.den = den;
	}
	
	/**
	 * convertToDecimal
	 * @return - double - fraction converted to decimal 
	 */
	public double convertToDecimal()
	{
		double result = ((double)num / (double)den);
		return result;
	}
	
	/**
	 * convertToReciprocal
	 * flips fraction to be reciprocal 
	 * @return - fraction
	 */
	public Fraction convertToResiprocal()
	{
		int num = this.den;
		int den = this.num;
		return new Fraction(num, den);
	}
	
	/**
	 * GCD
	 * Greatest Common Factor (GCD) is used to calculate lowest terms
	 * @param - int a & b 
	 * @return - int 
	 */
	public int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
		}
	/**
	 * lowestTerms 
	 * finds the smallest fraction from the list of fractions 
	 * @return - fraction 
	 */
	public Fraction lowestTerms()
	{
		int newnum = 0;
		int newden = 0;
		int gcd = GCD(num, den);
		if (gcd == 1)
		{
			 newnum = num;
			 newden = den;
		}
		else if (gcd > 1)
		{
			 newnum = num/gcd;
			 newden = den/gcd;
		}
		
		return new Fraction(newnum, newden);
	}
/**
 * toString method
 */
	public String toString()
	{
		String result = num + "/" + den; 
		return result;
	}
	/**
	 * add
	 * adds to fractions together 
	 * @param - fraction - the second fraction 
	 * @return - fraction - the sum of 2 fractions added 
	 */
	public Fraction add(Fraction frac)
	{	
		int tempFrac1Num = this.getNum();
		int tempFrac1Denom = this.getden();
		
		int tempFrac2Num = frac.getNum();
		int tempFrac2Denom = frac.getden();
		
		//multiply num and denom of calling fraction by frac2 denom
		int newNumerator1 = (tempFrac1Num * tempFrac2Denom);
		int newDenominator1 = (tempFrac1Denom * tempFrac2Denom);
		
		//multiply num and denom of parameter fraction by denom of calling fraction object
		int newNumerator2 = (tempFrac2Num * tempFrac1Denom);
		//int newDenominator2 = (tempFrac2Denom * tempFrac1Denom);
		
		//sum the numerators
		int sumNumerators = newNumerator1 + newNumerator2;
		
		//call the constructor
		
		return new Fraction(sumNumerators, newDenominator1);
	}
	/**
	 * multiply 
	 * multiplies two fractions together
	 * @param - fraction 
	 * @return - fraction 
	 */
	public Fraction multiply(Fraction frac)
	{
		int tempfrac1Num = this.getNum();
		int tempfrac1Den = this.getden();
		
		int tempfrac2Num = frac.getNum();
		int tempfrac2Den = frac.getden();
		
		int newnum = tempfrac1Num * tempfrac2Num;
		int newden = tempfrac1Den * tempfrac2Den;
		
		return new Fraction (newnum, newden);
		
	}
	/**
	 * equals 
	 * checks to see if two fractions are equal 
	 * @param - fraction 
	 * @return - boolean 
	 */
	public boolean equals(Fraction frac1)
	{
		double temp1 = frac1.convertToDecimal();
		double temp2 = this.convertToDecimal();
		
		return (temp1 == temp2);
	}
	/**
	 * greaterThan
	 * checks to see if one fraction is greater than the other 
	 * @param - fraction 
	 * @return - boolean 
	 */
	public boolean greaterThan(Fraction frac)
	{
		double temp2 = this.convertToDecimal();
		double temp1 = frac.convertToDecimal();
		return temp1 > temp2;
	}
	
	/**
	 * compareTo
	 * compares two fractions 
	 * @param - fraction
	 * @return - int 
	 */
	@Override
	public int compareTo(Fraction frac) {
		if (this.convertToDecimal() == frac.convertToDecimal())
			return 0;
		else if (this.convertToDecimal() > frac.convertToDecimal() )
			return 1;
		else
			return -1;
	}
}
//end class