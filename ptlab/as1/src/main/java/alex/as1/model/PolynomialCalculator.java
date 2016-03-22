package alex.as1.model;

import java.math.BigDecimal;

import alex.as1.model.polynomial.Polynomial;
import alex.as1.model.polynomial.operation.PolynomialDifferentiation;
import alex.as1.model.polynomial.operation.PolynomialDivision;
import alex.as1.model.polynomial.operation.PolynomialIntegration;
import alex.as1.model.polynomial.operation.PolynomialMultiplication;
import alex.as1.model.polynomial.operation.PolynomialScalarMultiplication;
import alex.as1.model.polynomial.operation.PolynomialSubtraction;
import alex.as1.model.polynomial.operation.PolynomialSum;

public class PolynomialCalculator {
	private Object op1;
	private Object op2;

	public PolynomialCalculator() {
		this.op1 = new Polynomial();
		this.op2 = new Polynomial();
	}

	// (Polynomial, Polynomial) operations
	public Polynomial add() {
		PolynomialSum op = new PolynomialSum((Polynomial) op1, (Polynomial) op2);
		return op.execute();
	}
	
	public Polynomial subtract() {
		PolynomialSubtraction op = new PolynomialSubtraction((Polynomial) op1, (Polynomial) op2);
		return op.execute();
	}
	
	public Polynomial multiply() {
		PolynomialMultiplication op = new PolynomialMultiplication((Polynomial) op1, (Polynomial) op2);
		return op.execute();
	}
	
	public Polynomial[] divide() {
		Polynomial opr = new Polynomial((Polynomial) op1);
		PolynomialDivision op = new PolynomialDivision(opr, (Polynomial) op2);
		return new Polynomial[] {op.execute(), opr};
	}
	
	// (Polynomial, Integer) operations
	public Polynomial multiplyScalar() {
		PolynomialScalarMultiplication op = new PolynomialScalarMultiplication((Polynomial) op1, new BigDecimal((Integer) op2));
		return op.execute();
	}
	
	public Polynomial differentiate() {
		PolynomialDifferentiation op = new PolynomialDifferentiation((Polynomial) op1, (Integer) op2);
		return op.execute();
	}
	
	public Polynomial integrate() {
		PolynomialIntegration op = new PolynomialIntegration((Polynomial) op1, new BigDecimal((Integer) op2));
		return op.execute();
	}
	
	public Object getP1() {
		return op1;
	}

	public void setP1(Object p1) {
		this.op1 = p1;
	}

	public Object getP2() {
		return op2;
	}

	public void setP2(Object p2) {
		this.op2 = p2;
	}
}
