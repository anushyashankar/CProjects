import java.util.LinkedList;
import java.util.ListIterator;

public class Polynomial {
	private LinkedList<Term> poly;

	public Polynomial() {
		poly = new LinkedList<Term>();
	}

	public void addTerm(int coeff, int power) {
		ListIterator<Term> iter = poly.listIterator();
		if (coeff == 0)
			return;
		
		ListIterator<Term> iter2 = poly.listIterator();
		while (iter2.hasNext()) {
			if (iter2.next().power < power) {
				iter2.previous();
				iter2.add(new Term(coeff, power));
				return;
			}
			else if (iter.next().power == power) {
				iter2.add(new Term(coeff, power));
				return;
			}
		}
		iter2.add(new Term(coeff, power));
	}

	public Polynomial add(Polynomial poly2) {
		ListIterator<Term> iter = this.poly.listIterator();
		ListIterator<Term> iter2 = poly2.poly.listIterator();

		Polynomial sum = new Polynomial();

		while (iter.hasNext() && iter2.hasNext()) {
			int exponent = iter.next().power;
			int exponent2 = iter2.next().power;

			if (exponent == exponent2)
				sum.addTerm(iter.next().coefficient + iter2.next().coefficient, exponent);
			else if (exponent < exponent2)
				while (iter2.hasNext())
					if (exponent == iter2.next().power)
						sum.addTerm(iter.next().coefficient + iter2.next().coefficient, exponent);
			else if (exponent > exponent2)
				while (iter.hasNext())
					if (exponent2 == iter.next().power)
						sum.addTerm(iter.next().coefficient + iter2.next().coefficient, exponent2);
		}

		while (iter.hasNext()) //poly is longer
			sum.addTerm(iter.next().coefficient, iter.next().power);
		while (iter2.hasNext()) //poly2 is longer
			sum.addTerm(iter2.next().coefficient, iter2.next().power);
		
		return sum;
	}
	
	public Polynomial multiply(Polynomial poly2) {
		ListIterator<Term> iter = this.poly.listIterator();
		
		Polynomial product = new Polynomial();
		
		while (iter.hasNext()) {
			ListIterator<Term> iter2 = poly2.poly.listIterator();
			while (iter2.hasNext()) {
				product.addTerm(iter.next().coefficient * iter2.next().coefficient, iter.next().power + iter2.next().power);
				iter2.next();
			}
			iter.next();
		}
		
		return product;
	}
	
	public Polynomial derivative() {
		ListIterator<Term> iter = this.poly.listIterator();
		Polynomial deriv = new Polynomial();
		
		while (iter.hasNext())
			deriv.poly.add(iter.next().firstDerivative());
		
		return deriv;
	}
	
	public String toString() {
		ListIterator<Term> iter = poly.listIterator();
		String output = "";
		
		while (iter.hasNext()) {
			output += iter.next().toString();
			if (iter.hasNext())
				output += " + ";
		}
		
		return output;
	}

}
