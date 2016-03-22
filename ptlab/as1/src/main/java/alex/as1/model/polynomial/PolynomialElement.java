package alex.as1.model.polynomial;

/**
 * This enum provides the ability to check the validity of the input by only using 
 * the element last added. It uses a 'look up' table to see if the element added
 * previously is relevant and maintains correctness after a new one is added.
 */
public enum PolynomialElement {
	
	// These are all the types of elements a polynomial can have
	// Empty and End are special cases acting as sentinels
	Empty(0, 0),
	NonZeroCoefficientDigit(1, 1), 
	ZeroCoefficientDigit(2, 1), 
	Minus(3, 2), 
	Plus(4, 2), 
	Variable(5, 1), 
	Caret(6, 1),
	NonZeroExponentDigit(7, 1), 
	ZeroExponentDigit(8, 1),
	End(9, 0);
	
	// The id is for looking in the lut
	private int id;
	// Length represents the size of the string the element has
	private int length;
	
	private PolynomialElement(int id, int length) {
		this.id = id;
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
	
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_EMPTY = 
		{NonZeroCoefficientDigit, Minus, Variable};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_NON_ZERO_COEFFICIENT_DIGIT = 
		{NonZeroCoefficientDigit, ZeroCoefficientDigit, Minus, Plus, Variable, End};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_ZERO_COEFFICIENT_DIGIT = 
		{NonZeroCoefficientDigit, ZeroCoefficientDigit, Minus, Plus, Variable, End};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_MINUS = 
		{NonZeroCoefficientDigit, Variable};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_PLUS = 
		{NonZeroCoefficientDigit, Variable};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_VARIABLE = 
		{Caret, Minus, Plus, End};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_CARRET = 
		{NonZeroExponentDigit};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_NON_ZERO_EXPONENT_DIGIT = 
		{NonZeroExponentDigit, ZeroExponentDigit, Minus, Plus, End};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_ZERO_EXPONENT_DIGIT = 
		{NonZeroExponentDigit, ZeroExponentDigit, Minus, Plus, End};
	private static final PolynomialElement[] NEXT_PERMITTED_ELEMENT_END = 
		{};
	
	// This is the look up table.
	// Every line corresponds to an element that can succeed it.
	private static final PolynomialElement[][] NEXT_PERMITTED_ELEMENT = 
		{NEXT_PERMITTED_ELEMENT_EMPTY, 
			NEXT_PERMITTED_ELEMENT_NON_ZERO_COEFFICIENT_DIGIT, 
			NEXT_PERMITTED_ELEMENT_ZERO_COEFFICIENT_DIGIT, 
			NEXT_PERMITTED_ELEMENT_MINUS, 
			NEXT_PERMITTED_ELEMENT_PLUS, 
			NEXT_PERMITTED_ELEMENT_VARIABLE,
			NEXT_PERMITTED_ELEMENT_CARRET, 
			NEXT_PERMITTED_ELEMENT_NON_ZERO_EXPONENT_DIGIT, 
			NEXT_PERMITTED_ELEMENT_ZERO_EXPONENT_DIGIT,
			NEXT_PERMITTED_ELEMENT_END};
	
	// This checks if the previous element admits the new one
	public static boolean elementPermitted(PolynomialElement previous, PolynomialElement current) {
		boolean permitted = false;
		
		for (PolynomialElement element : NEXT_PERMITTED_ELEMENT[previous.id]) {
			if (element.equals(current)) {
				permitted = true;
			}
		}
		
		return permitted;
	}
	
	// Unfortunately the elements are not disjoint: coefficients and exponents
	// use the same characters
	// However one can easily distinguish between the two if the previous element
	// was a caret or part of an exponent
	public static PolynomialElement convertToApplicableElement(PolynomialElement previous, PolynomialElement current) {
		PolynomialElement result = current;
		
		if (previous == Caret || previous == NonZeroExponentDigit || previous == ZeroExponentDigit) {
			if (current == NonZeroCoefficientDigit) {
				result = NonZeroExponentDigit;
			} else if (current == ZeroCoefficientDigit) {
				result = ZeroExponentDigit;
			}
		}
		return result;
	}
}
