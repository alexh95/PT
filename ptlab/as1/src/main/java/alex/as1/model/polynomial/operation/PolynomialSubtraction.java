package alex.as1.model.polynomial.operation;

import java.util.ArrayList;

import alex.as1.model.polynomial.Polynomial;

public class PolynomialSubtraction extends PolynomialOperation {

	private ArrayList<Polynomial> polynomials;

	/**
	 * @param polynomials
	 * execute() returns a new polynomial p1 - p2 - p3 - ... Parameters are not changed
	 */
	public PolynomialSubtraction(Polynomial... polynomials) {
		this.polynomials = new ArrayList<Polynomial>();
		for (Polynomial p : polynomials) {
			this.polynomials.add(p);
		}
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();
		boolean firstPolynomial = true;

		for (Polynomial ip : getPolynomials()) {
			Polynomial operand = new Polynomial();

			if (firstPolynomial) {
				firstPolynomial = false;
				operand.addMonomials(ip.getMonomials());
			} else {
				PolynomialNegate pn = new PolynomialNegate(ip);
				operand.addMonomials(pn.execute().getMonomials());
			}

			result.addMonomials(operand.getMonomials());
		}

		return result;
	}

	public ArrayList<Polynomial> getPolynomials() {
		return polynomials;
	}

	public void setPolynomials(ArrayList<Polynomial> polynomials) {
		this.polynomials = polynomials;
	}
}
