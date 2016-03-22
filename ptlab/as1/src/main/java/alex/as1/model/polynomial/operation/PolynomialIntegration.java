package alex.as1.model.polynomial.operation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialIntegration extends PolynomialOperation {

	private Polynomial polynomial;
	private BigDecimal solution;
	
	public PolynomialIntegration(Polynomial polynomial, BigDecimal solution) {
		super();
		
		this.polynomial = polynomial;
		this.solution = solution;
	}

	@Override
	public Polynomial execute() {
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		Polynomial result = new Polynomial();

		for (Monomial monomial : polynomial.getMonomials()) {
			BigDecimal coefficient = monomial.getCoefficient();
			int degree = monomial.getDegree();
			
			int newDegree = degree + 1;
			BigDecimal newCoeffecient = coefficient.divide(new BigDecimal(newDegree), mc);
			
			Monomial newMonomial = new Monomial(newDegree, newCoeffecient);
			result.addMonomial(newMonomial);
		}
		
		Monomial solutionMonomial = new Monomial(0, solution);
		result.addMonomial(solutionMonomial);
		
		return result;
	}

}
