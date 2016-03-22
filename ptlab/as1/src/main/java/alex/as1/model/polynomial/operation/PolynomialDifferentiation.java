package alex.as1.model.polynomial.operation;

import java.math.BigDecimal;

import alex.as1.model.polynomial.Monomial;
import alex.as1.model.polynomial.Polynomial;

public class PolynomialDifferentiation extends PolynomialOperation{

	private Polynomial polynomial;
	private int value;
	
	public PolynomialDifferentiation(Polynomial polynomial, int value) {
		super();
		
		this.polynomial = polynomial;
		this.value = Math.abs(value);
	}
	
	@Override
	public Polynomial execute() {
		Polynomial result = new Polynomial();
		
		for (Monomial monomial : polynomial.getMonomials()) {
			BigDecimal coefficient = monomial.getCoefficient();
			int degree = monomial.getDegree();
			
			if (degree >= value) {
				BigDecimal newCoefficient = coefficient;
				int newDegree = degree;
				
				for (int i = 0; i < value; i++) {
					newCoefficient = newCoefficient.multiply(new BigDecimal(newDegree));
					newDegree--;
				}
				
				Monomial newMonomial = new Monomial(newDegree, newCoefficient);
				result.addMonomial(newMonomial);
			}
		}
		
		return result;
	}
}
