/** A class to represent a rational number
    with a numerator and denominator

    @author Zach Friedland

	*/
	
package edu.ucsb.cs56.ratcalc.model;

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
	if(this.denom < 0){
		this.num*= -1;
		this.denom*= -1;
	}

    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    /** 
     * Our implemented work below
     */

    /** 
	least common multiple of a and b
	@param a first number
	@param b second number
	@return lcm of a and b
    */

    public static int lcm(int a, int b){
	    return Math.abs(a*b)/Rational.gcd(a,b);
    }

    public Rational plus(Rational r){
	    int num = (this.num * r.denom) + (r.num * this.denom);
	    int denom = (this.denom * r.denom);
	    return new Rational(num,denom);
    }

    public static Rational sum(Rational a, Rational b){
	    int num = (a.num * b.denom) + (b.num * a.denom);
	    int denom = (a.denom * b.denom);
	    return new Rational(num,denom);
    }

    public Rational minus(Rational r){
	    Rational negativeR = r.times(new Rational(-1,1));
	    return this.plus(negativeR);
    }

    public static Rational difference (Rational a, Rational b){
	    Rational negativeB = b.times(new Rational(-1,1));
	    return a.plus(negativeB);
    }

    public Rational reciprocalOf(){
	    if(num == 0)
		    throw new ArithmeticException("for reciprocal, numerator cannot be zero");
	    return new Rational(this.denom, this.num);
    }

    public Rational dividedBy(Rational r){
	    Rational reciprocalR = r.reciprocalOf();
	    return this.times(reciprocalR);
    }
    
    public static Rational quotient(Rational a, Rational b){
	    Rational reciprocalB = b.reciprocalOf();
	    return a.times(reciprocalB);
    }

    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }    
}
