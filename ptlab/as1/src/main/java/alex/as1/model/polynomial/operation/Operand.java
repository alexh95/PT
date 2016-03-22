package alex.as1.model.polynomial.operation;

/**
 * Operand enum is used to set the labels of the class OperandField.
 * It is also used to determine what kind of checking to perform on the operand fields
 * 
 */
public enum Operand {
	
	AugendPolynomial(OperandType.PolynomialValue, "Polynomial for addition", "P1", "A polynomial with integer coefficients"),
	AdeendPolynomial(OperandType.PolynomialValue, "Polynomial for addition", "P2", "A polynomial with integer coefficients"),
	MinuendPolynomial(OperandType.PolynomialValue, "Polynomial to be subtracted from", "P1", "A polynomial with integer coefficients"),
	SubtrahendPolynomial(OperandType.PolynomialValue, "Polynomial to be subtracted to", "P2", "A polynomial with integer coefficients"),
	MultiplicandPolynomial(OperandType.PolynomialValue, "Polynomial to be multiplied", "P1", "A polynomial with integer coefficients"),
	MultiplierPolynomial(OperandType.PolynomialValue, "Polynomail to be multiplied", "P2", "A polynomial with integer coefficients"),
	DividendPolynomial(OperandType.PolynomialValue, "Polynomial to be divided", "P1", "A polynomial with integer coefficients"),
	DivisorPolynomial(OperandType.PolynomialValue, "Polynomial to divide with", "P2", "A polynomial with integer coefficients"),
	MultiplicandScalarPolynomial(OperandType.PolynomialValue, "Polynomial to be multiplied with a scalar", "P", "A polynomial with integer coefficients"),
	MultiplierScalar(OperandType.IntegerValue, "Integer Scalar for scalar multiplication", "S", "An integer"),
	DifferentiatedPolynomial(OperandType.PolynomialValue, "Polynomial to be differentiated", "P", "A polynomial with integer coefficients"),
	DifferentiationAmount(OperandType.IntegerValue, "The number of times differentiation will occur", "N", "An integer"),
	IntegratedPolynomial(OperandType.PolynomialValue, "Polynomial to be integrated", "P", "A polynomial with integer coefficients"),
	IntegrationSolution(OperandType.IntegerValue, "The solution to the equation int(P(x))|(x=0) = C,", "C", "An integer");
	
	private OperandType operandType;
	private String operandName;
	private String operandSymbol;
	private String operandHelp;
	
	private Operand(OperandType operandType, String operandName, String operandSymbol, String operandHelp) {
		this.operandType = operandType;
		this.operandName = operandName;
		this.operandSymbol = operandSymbol;
		this.operandHelp = operandHelp;
	}
	
	public String getOperandName() {
		return operandName;
	}
	
	public String getOperandSymbol() {
		return operandSymbol;
	}
	
	public String getOperandHelp() {
		return operandHelp;
	}
	
	public OperandType getOperandType() {
		return operandType;
	}
}
