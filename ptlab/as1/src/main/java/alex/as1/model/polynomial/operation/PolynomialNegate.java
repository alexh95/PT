package alex.as1.model.polynomial.operation;

import java.util.ArrayList;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialNegate extends PolynomialOperation {

	private Polynomial polynomial;

	/**
	 * @param polynomial
	 *            execute() will return the negated polynomial set in the
	 *            constructor. It will return a new object; the parameter will
	 *            not be modified
	 */
	public PolynomialNegate(Polynomial polynomial) {
		setPolynomial(polynomial);
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();
		ArrayList<Monomial> negatedMonomials = new ArrayList<Monomial>();

		for (Monomial monomial : getPolynomial().getMonomials()) {
			Monomial negatedMonomial = new Monomial(monomial.getDegree(), monomial.getCoefficient().negate());
			negatedMonomials.add(negatedMonomial);
		}

		result.addMonomials(negatedMonomials);

		return result;
	}

	public Polynomial getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}
}
