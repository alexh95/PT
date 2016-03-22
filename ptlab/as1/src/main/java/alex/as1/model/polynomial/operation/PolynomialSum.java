package alex.as1.model.polynomial.operation;

import java.util.ArrayList;

import alex.as1.model.polynomial.Polynomial;

public class PolynomialSum extends PolynomialOperation {

	private ArrayList<Polynomial> polynomials;

	/**
	 * @param polynomials
	 *            execute() will return a new polynomial: p1 + p2 + p3 + ... The
	 *            parameters won't be modified.
	 */
	public PolynomialSum(Polynomial... polynomials) {
		this.polynomials = new ArrayList<Polynomial>();
		for (Polynomial p : polynomials) {
			getPolynomials().add(p);
		}
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();

		for (Polynomial ip : getPolynomials()) {
			result.addMonomials(ip.getMonomials());
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
