package alex.as1.controller;

import alex.as1.view.PolynomialCalculatorJFrame;

/**
 * Main class containing main method.
 */
public class PolyCalc {
	public static void main(String[] args) {
		new PolynomialCalculatorController(new PolynomialCalculatorJFrame());
	}
}
