package alex.as1.view;

import javax.swing.JButton;

import alex.as1.model.polynomial.PolynomialElement;

/**
 * InputButton is meant to represent the only way to access the operand fields
 * it contains fields to be linked with key events (this is how it achieves it's
 * exclusive control)
 */
public class InputButton extends JButton {
	private static final long serialVersionUID = -9208635151120970660L;

	// The id's will be KeyEvents id's
	private int id1;
	private int id2;
	private String value;
	// This will be used to establish the correctness of the input
	private PolynomialElement polynomialElement;

	public InputButton(String name, int id1, int id2, String value, PolynomialElement pe) {
		super(name);
		setFocusable(false);

		this.id1 = id1;
		this.id2 = id2;
		this.value = value;
		this.polynomialElement = pe;
	}

	public int getId1() {
		return id1;
	}

	public int getId2() {
		return id2;
	}

	public String getValue() {
		return value;
	}

	public PolynomialElement getPolynomialElement() {
		return polynomialElement;
	}
}
