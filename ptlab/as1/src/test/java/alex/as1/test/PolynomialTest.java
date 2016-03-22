package alex.as1.test;

import java.math.BigDecimal;

import org.junit.Test;

import alex.as1.model.polynomial.Polynomial;
import alex.as1.model.polynomial.operation.PolynomialDifferentiation;
import alex.as1.model.polynomial.operation.PolynomialDivision;
import alex.as1.model.polynomial.operation.PolynomialIntegration;
import alex.as1.model.polynomial.operation.PolynomialMultiplication;
import alex.as1.model.polynomial.operation.PolynomialNegate;
import alex.as1.model.polynomial.operation.PolynomialScalarMultiplication;
import alex.as1.model.polynomial.operation.PolynomialSubtraction;
import alex.as1.model.polynomial.operation.PolynomialSum;
import junit.framework.TestCase;

public class PolynomialTest extends TestCase {

	private Polynomial pParse1;
	private Polynomial pParse2;
	private Polynomial pParse3;

	private Polynomial pAdd1;
	private Polynomial pAdd2;
	private PolynomialSum pA;

	private Polynomial pNeg;
	private PolynomialNegate pN;

	private Polynomial pSub1;
	private Polynomial pSub2;
	private PolynomialSubtraction pS;

	private Polynomial pMul1;
	private Polynomial pMul2;
	private PolynomialMultiplication pM;

	private Polynomial pDiv1;
	private Polynomial pDiv2;
	private PolynomialDivision pD;

	private Polynomial pScal;
	private BigDecimal sScal;
	private PolynomialScalarMultiplication pSM;

	private Polynomial pDiff;
	private int nDiff;
	private PolynomialDifferentiation pDF;

	private Polynomial pInt;
	private BigDecimal cInt;
	private PolynomialIntegration pIN;

	public PolynomialTest() {
		// Parse initialization
		pParse1 = Polynomial.parsePolynomial("-x^3 +2x^2 -33x -1");
		pParse2 = Polynomial.parsePolynomial("-2x^2 -3x^3 +32x +2");
		pParse3 = Polynomial.parsePolynomial("3 -2 3x -x x -2x x^2 -x^2 3x^2 -2x^2");

		// Addition initialization
		pAdd1 = new Polynomial(pParse1);
		pAdd2 = new Polynomial(pParse2);
		pA = new PolynomialSum(pAdd1, pAdd2);

		// Negation initialization
		pNeg = new Polynomial(pParse3);
		pN = new PolynomialNegate(pNeg);

		// Subtraction initialization
		pSub1 = new Polynomial(pParse1);
		pSub2 = new Polynomial(pParse3);
		pS = new PolynomialSubtraction(pSub1, pSub2);

		// Multiplication initialization
		pMul1 = new Polynomial(pParse1);
		pMul2 = new Polynomial(pParse3);
		pM = new PolynomialMultiplication(pMul1, pMul2);

		// Division initialization
		pDiv1 = new Polynomial(pParse1);
		pDiv2 = new Polynomial(pParse3);
		pD = new PolynomialDivision(pDiv1, pDiv2);

		// Scalar Multiplication initialization
		pScal = new Polynomial(pParse1);
		sScal = new BigDecimal(-3);
		pSM = new PolynomialScalarMultiplication(pScal, sScal);

		// Differentiation initialization
		pDiff = new Polynomial(pParse1);
		nDiff = 1;
		pDF = new PolynomialDifferentiation(pDiff, nDiff);

		// Integration initialization
		pInt = Polynomial.parsePolynomial("4x^3 -24x^2 +12x -4");
		cInt = new BigDecimal(36);
		pIN = new PolynomialIntegration(pInt, cInt);
	}

	@Test
	public void testParse() {
		// Parse testing
		assertTrue("Parse test 1", pParse1.toString().equals("-x^3+2x^2-33x-1"));
		assertTrue("Parse test 2", pParse2.toString().equals("-3x^3-2x^2+32x+2"));
		assertTrue("Parse test 3", pParse3.toString().equals("x^2+x+1"));
	}

	@Test
	public void testAddition() {
		// Addition testing
		assertTrue("Addition test", pA.execute().toString().equals("-4x^3-x+1"));
	}

	@Test
	public void testNegation() {
		// Negation testing
		assertTrue("Negation test", pN.execute().toString().equals("-x^2-x-1"));
	}

	@Test
	public void testSubtraction() {
		// Subtraction testing
		assertTrue("Subtraction test", pS.execute().toString().equals("-x^3+x^2-34x-2"));
	}

	@Test
	public void testMultiplication() {
		// Multiplication testing
		assertTrue("Multiplication test", pM.execute().toString().equals("-x^5+x^4-32x^3-32x^2-34x-1"));
	}

	@Test
	public void testDivision() {
		// Division testing
		assertTrue("Division quotient test", pD.execute().toString().equals("-x+3"));
		assertTrue("Division remainder test", pDiv1.toString().equals("-35x-4"));
	}

	@Test
	public void testScalarMultiplication() {
		// Scalar Multiplication testing
		assertTrue("Scalar Multiplication test", pSM.execute().toString().equals("3x^3-6x^2+99x+3"));
	}

	@Test
	public void testDifferentiation() {
		// Differentiation testing
		assertTrue("Differentiation test", pDF.execute().toString().equals("-3x^2+4x-33"));
	}

	@Test
	public void testIntegration() {
		// Integration testing
		assertTrue("Integration test", pIN.execute().toString().equals("x^4-8x^3+6x^2-4x+36"));
	}
}
