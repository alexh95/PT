package alex.as1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alex.as1.model.polynomial.PolynomialElement;
import alex.as1.model.polynomial.operation.Operand;
import alex.as1.model.polynomial.operation.OperandType;

/**
 * Used to display the received input. It holds the 'state' of the input to
 * enable checking its correctness.
 */
public class OperandPanel extends JPanel {
	private static final long serialVersionUID = 9080293821856073295L;

	private static final String VALID_HELP_TEXT = " (CORRECT)";
	private static final String INVALID_HELP_TEXT = " (INCORRECT)";
	private static final Color VALID_HELP_COLOR = Color.GREEN;
	private static final Color INVALID_HELP_COLOR = Color.RED;

	// Each operand panel corresponds to an operand
	private Operand operand;

	private JLabel operandName;
	private JLabel operandSymbol;
	private JLabel operandHelp;
	private JTextField operandText;

	private boolean selected;
	private boolean valid;

	// The state holder
	private ArrayList<PolynomialElement> polyElements;
	private String value;

	public OperandPanel(Operand operand) {
		super(new BorderLayout());

		polyElements = new ArrayList<PolynomialElement>();
		polyElements.add(PolynomialElement.Empty);

		value = new String();

		operandName = new JLabel();
		operandSymbol = new JLabel();
		operandHelp = new JLabel();
		operandText = new JTextField(value);
		operandText.setEditable(false);
		operandText.setFocusable(false);

		setSelected(false);

		setOperand(operand);

		add(operandName, BorderLayout.NORTH);
		add(operandSymbol, BorderLayout.WEST);
		add(operandText, BorderLayout.CENTER);
		add(operandHelp, BorderLayout.SOUTH);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
		applyOperand();
	}

	private void applyOperand() {
		operandName.setText(operand.getOperandName());
		operandSymbol.setText(operand.getOperandSymbol() + " = ");
		applyHelp();
	}

	// Some nice visual feedback
	private void applyHelp() {

		String helpText = INVALID_HELP_TEXT;
		Color helpColor = INVALID_HELP_COLOR;

		if (valid) {
			helpText = VALID_HELP_TEXT;
			helpColor = VALID_HELP_COLOR;
		}

		operandHelp.setText(operand.getOperandHelp() + helpText);
		operandHelp.setForeground(helpColor);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected) {
			this.operandText.setBackground(Color.YELLOW);
		} else {
			this.operandText.setBackground(Color.WHITE);
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
		applyHelp();
	}

	public void resetValue() {
		polyElements.clear();
		polyElements.add(PolynomialElement.Empty);
		this.value = "";
		operandText.setText(value);
	}

	public void receiveInput(PolynomialElement pe, String input) {
		if (selected) {
			polyElements.add(pe);
			value = value + input;
			operandText.setText(value);
		}
	}

	public void backspace() {
		if (selected) {
			PolynomialElement pe = polyElements.get(polyElements.size() - 1);
			if (pe != PolynomialElement.Empty) {
				value = value.substring(0, value.length() - pe.getLength());
				operandText.setText(value);
				polyElements.remove(polyElements.size() - 1);
			}
		}
	}

	// Used to check if the format's valid
	public PolynomialElement getLastPolyElement() {
		return polyElements.get(polyElements.size() - 1);
	}

	public ArrayList<PolynomialElement> getPolyElements() {
		return polyElements;
	}

	public String getValue() {
		return value;
	}

	public void addTextFieldMouseListener(MouseListener l) {
		operandText.addMouseListener(l);
	}

	// Used to determine the source of an mouse event (it gets selected if it is
	// clicked)
	public boolean compareTextField(JTextField tf) {
		return operandText.equals(tf);
	}

	// Used to check if the format's valid
	public OperandType getOperandType() {
		return operand.getOperandType();
	}
}
