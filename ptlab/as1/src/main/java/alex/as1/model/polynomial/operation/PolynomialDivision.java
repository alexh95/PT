package alex.as1.model.polynomial.operation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialDivision extends PolynomialOperation {

	Polynomial polynomial1;
	Polynomial polynomial2;

	/**
	 * @param polynomial1
	 * @param polynomial2
	 *            execute() will return the quotient of p1 / p2 and p1 will
	 *            become the remainder of p1 / p2. p2 will not change
	 */
	public PolynomialDivision(Polynomial polynomial1, Polynomial polynomial2) {
		setPolynomial1(polynomial1);
		setPolynomial2(polynomial2);
	}

	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();

		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		Monomial partialQuotient;
		boolean endAfterOnePass = false;
		while ((polynomial1.getDegree() >= 0 && polynomial2.getDegree() >= 0)
				&& (polynomial1.getDegree() >= polynomial2.getDegree()) && !endAfterOnePass) {

			if (polynomial1.getDegree() == 0) {
				endAfterOnePass = true;
			}
			
			Monomial dividendFisrtMonomial = polynomial1.getMonomials().get(0);
			Monomial divisorFisrtMonomial = polynomial2.getMonomials().get(0);

			int partialQuotientDegree = dividendFisrtMonomial.getDegree() - divisorFisrtMonomial.getDegree();
			BigDecimal partialQuotientCoefficient = dividendFisrtMonomial.getCoefficient()
					.divide(divisorFisrtMonomial.getCoefficient(), mc);
			partialQuotient = new Monomial(partialQuotientDegree, partialQuotientCoefficient);
			result.addMonomial(partialQuotient);

			Polynomial partialMult = new PolynomialMultiplication(partialQuotient.getPolynomial(), polynomial2)
					.execute();

			Polynomial newDividend = new PolynomialSubtraction(polynomial1, partialMult).execute();
			polynomial1.setMonomials(newDividend);
		}
		// If the division was not performed. the output is already in its
		// place, p1 is the remainder and result (the quotient) is 0

		return result;
	}

	public Polynomial getPolynomial1() {
		return polynomial1;
	}

	public Polynomial getPolynomial2() {
		return polynomial2;
	}

	public void setPolynomial1(Polynomial polynomial1) {
		this.polynomial1 = polynomial1;
	}

	public void setPolynomial2(Polynomial polynomial2) {
		this.polynomial2 = polynomial2;
	}
}
