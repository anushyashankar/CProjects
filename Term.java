
public class Term {
	int coefficient;
	int power;
	
	public Term (int c, int p) {
		coefficient = c;
		power = p;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public Term add(Term q) {
		if (this.power != q.power)
			return null;
		Term one = new Term(this.coefficient + q.coefficient, this.power);
		return one;
	}
	
	public Term multiply(Term q) {
		Term one = new Term(this.coefficient * q.coefficient, this.power + q.power);
		return one;
	}
	
	public Term firstDerivative() {
		Term one = new Term(this.coefficient * this.power, this.power - 1);
		return one;
	}
	
	public String toString() {
		if (this.power == 1)
			return "0";
		else if (this.power == 0)
			return "1";
		else 
			return "" + this.coefficient + "x^" + this.power;
	}
}
