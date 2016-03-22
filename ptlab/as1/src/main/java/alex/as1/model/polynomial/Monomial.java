package alex.as1.model.polynomial;

import java.math.BigDecimal;

public class Monomial implements Polynomializable{

	private int degree;
	private BigDecimal coefficient;

	public Monomial(int degree, BigDecimal coefficient) {
		setDegree(degree);
		setCoefficient(coefficient);
	}

	/**
	 * @param monomialRegex
	 *            Receives a string having the format
	 *            "[sign][coefficient] &| [x[^power]]"
	 */
	public static Monomial parseMonomial(String monomialRegex)  {
		Monomial result = new Monomial(0, BigDecimal.ZERO);
		
		String m = new String(monomialRegex);
		int newDegree = 0;
		int sign = 1;
		BigDecimal newCoefficient;
		
		// Determine the coefficient of the monomial
		// hasSign will act both as a boolean if there is is a sign (=1)
		// and as an offset in case it is
		int hasSign = 0;
		char firstChar = m.charAt(0);
		if (firstChar == '+') {
			hasSign = 1;
		} else if (firstChar == '-') {
			hasSign = 1;
			sign = -1;
		}
		// If the variable is missing then parsing the coefficient is trivial
		int posX = m.indexOf('x');
		if (posX == -1) {
			newCoefficient = new BigDecimal(m.substring(hasSign));
		} else {
			newDegree = 1;
			// Coefficient = 1 case
			if (m.charAt(hasSign) == 'x') {
				newCoefficient = BigDecimal.ONE;
			} else {
				//	General case
				newCoefficient = new BigDecimal(m.substring(hasSign, posX));
			}
		}

		// Determine the degree of the monomial, this is trivial
		int posCarret = m.indexOf('^');
		if (posCarret != -1) {
			newDegree = Integer.parseInt(m.substring(posCarret + 1));
		}
		// in case the caret is missing the coefficient is already 0

		result.setDegree(newDegree);
		result.setCoefficient(newCoefficient.multiply(new BigDecimal(sign)));
		return result;
	}

	public int getDegree() {
		return degree;
	}

	public BigDecimal getCoefficient() {
		return coefficient;
	}

	private void setDegree(int degree) {
		this.degree = degree;
	}

	private void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}
	
	@Override
	public Polynomial getPolynomial() {
		Polynomial result = new Polynomial();
		result.addMonomial(this);
		return result;
	}

	/**
	 * The reverse of parsing. It returns the proper format for printing.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		char sign;
		if (coefficient.doubleValue() < 0) {
			sign = '-';
		} else {
			sign = '+';
		}

		if (degree == 0) {
			if (coefficient.doubleValue() > 0) {
				sb.append(sign);
			}
			sb.append(String.valueOf(coefficient));
		} else {
			if (coefficient.doubleValue() < 0) {
				if (Math.abs(coefficient.doubleValue()) == 1.0) {
					sb.append(sign);
				} else {
					sb.append(String.valueOf(coefficient));
				}
			} else {
				sb.append(sign);
				if (coefficient.abs().compareTo(BigDecimal.ONE) != 0) {
					sb.append(String.valueOf(coefficient));
				}
			}
		}

		if (degree >= 1) {
			sb.append("x");
		}

		if (degree >= 2) {
			sb.append("^");
			sb.append(String.valueOf(degree));
		}

		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		
		if (obj instanceof Monomial) {
			Monomial m = (Monomial) obj;
			result = (this.getDegree() == m.getDegree()) && (this.getCoefficient().equals(m.getCoefficient()));
		} else {
			result = false;
		}
		
		return result;
	}
}
