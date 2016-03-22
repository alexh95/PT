package alex.as1.model.polynomial.operation;

/**
 * Stores information about the Operation, will be used as a state in the controller.
 */
public enum Operation {
	Addition(Operand.AugendPolynomial, Operand.AdeendPolynomial),
	Subtraction(Operand.MinuendPolynomial, Operand.SubtrahendPolynomial),
	Multiplication(Operand.MultiplicandPolynomial, Operand.MultiplierPolynomial),
	Division(Operand.DividendPolynomial, Operand.DivisorPolynomial),
	ScalarMultiplication(Operand.MultiplicandScalarPolynomial, Operand.MultiplierScalar),
	Differentiation(Operand.DifferentiatedPolynomial, Operand.DifferentiationAmount),
	Integration(Operand.IntegratedPolynomial, Operand.IntegrationSolution);
	
	private Operand operand1;
	private Operand operand2;
	
	private Operation(Operand operand1, Operand operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	public Operand getOperand1() {
		return operand1;
	}

	public Operand getOperand2() {
		return operand2;
	}
}
