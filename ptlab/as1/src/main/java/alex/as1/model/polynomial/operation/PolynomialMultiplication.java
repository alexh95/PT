package alex.as1.model.polynomial.operation;

import java.math.BigDecimal;
import java.util.ArrayList;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialMultiplication extends PolynomialOperation {

	private ArrayList<Polynomial> polynomials;

	/**
	 * @param polynomials
	 *            execute() will return a new polynomial (p1 * p2 * ...)
	 *            Parameters will not be modified
	 */
	public PolynomialMultiplication(Polynomial... polynomials) {
		this.polynomials = new ArrayList<Polynomial>();
		for (Polynomial p : polynomials) {
			getPolynomials().add(p);
		}
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();

		int size = getPolynomials().size();
		if (size > 0) {
			result.addMonomials(getPolynomials().get(0).getMonomials());
		}
		
		Polynomial newResult = new Polynomial();
		for (int i = 1; i < size; i++) {
			newResult.clear();
			for (Monomial m1 : result.getMonomials()) {
				for (Monomial m2 : getPolynomials().get(i).getMonomials()) {
					int newDegree = m1.getDegree() + m2.getDegree();
					BigDecimal newCoefficient = m1.getCoefficient().multiply(m2.getCoefficient());
					Monomial newMonomial = new Monomial(newDegree, newCoefficient);
					newResult.addMonomial(newMonomial);
				}
			}

			result.setMonomials(newResult);
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
