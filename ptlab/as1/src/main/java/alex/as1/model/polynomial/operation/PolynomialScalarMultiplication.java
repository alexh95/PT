package alex.as1.model.polynomial.operation;

import java.math.BigDecimal;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialScalarMultiplication extends PolynomialOperation {

	private BigDecimal scalar;
	private Polynomial polynomial;

	/**
	 * @param scalar
	 * @param polynomial
	 *            execute() will return a new polynomial obtained by the scalar
	 *            multiplication of the two parameters. The parameters will not
	 *            be changed.
	 */
	public PolynomialScalarMultiplication(Polynomial polynomial, BigDecimal scalar) {
		setPolynomial(polynomial);
		setScalar(scalar);
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();

		for (Monomial monomial : getPolynomial().getMonomials()) {
			Monomial multipliedMonomial = new Monomial(monomial.getDegree(),
					getScalar().multiply(monomial.getCoefficient()));
			result.addMonomial(multipliedMonomial);
		}

		return result;
	}

	public BigDecimal getScalar() {
		return scalar;
	}

	public Polynomial getPolynomial() {
		return polynomial;
	}

	public void setScalar(BigDecimal scalar) {
		this.scalar = scalar;
	}

	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}
}
