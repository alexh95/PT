package alex.as1.model.polynomial.operation;

import alex.as1.model.polynomial.Polynomial;

/**
 * Holds the types of operand the operation support. Also facilitates the
 * parsing form string of said operands. It assumes the input is correct since
 * every input change triggers a validity check (which ensures correction)
 */
public enum OperandType {
	PolynomialValue, IntegerValue;

	public Object parseValue(String regex) {
		Object result = null;

		switch (this) {
		case IntegerValue:
			if (regex.charAt(0) == ' ') {
				regex = regex.substring(1);
			}
			result = Integer.parseInt(regex);
			break;
		case PolynomialValue:
			result = Polynomial.parsePolynomial(regex);
			break;
		}

		return result;
	}
}
