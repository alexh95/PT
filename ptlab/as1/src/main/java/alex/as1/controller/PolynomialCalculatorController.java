package alex.as1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import alex.as1.model.PolynomialCalculator;
import alex.as1.model.polynomial.Polynomial;
import alex.as1.model.polynomial.PolynomialElement;
import alex.as1.model.polynomial.operation.OperandType;
import alex.as1.model.polynomial.operation.Operation;
import alex.as1.view.InputButton;
import alex.as1.view.OperationButton;
import alex.as1.view.PolynomialCalculatorJFrame;

public class PolynomialCalculatorController {

	private PolynomialCalculatorJFrame frame;
	private PolynomialCalculator polyCalc;

	public PolynomialCalculatorController(PolynomialCalculatorJFrame frame) {
		this.frame = frame;
		// Add all the listeners
		this.frame.addMainPaneKeyListener(new OperandPanelKeyListener());

		this.frame.getOperandPanel1().setSelected(true);
		this.frame.getOperandPanel1().addTextFieldMouseListener(new OperandPanelMouseListener());
		this.frame.getOperandPanel2().addTextFieldMouseListener(new OperandPanelMouseListener());

		this.frame.getInputPanel().addInputButtonActionListener(new InputButtonActionListener());
		this.frame.getInputPanel().addBackSpaceActionListener(new BackspaceActionListener());
		this.frame.getInputPanel().addClear1ActionListener(new Clear1ActionListener());
		this.frame.getInputPanel().addClear2ActionListener(new Clear2ActionListener());

		this.frame.getOperationPanel().addOperationButtonActionListener(new OperationButtonActionListener());
		this.frame.getOperationPanel()
				.addPerformOperationButtonActionListener(new PerformOperationButtonActionListener());

		// Initialization
		this.polyCalc = new PolynomialCalculator();

		checkValidity();
	}

	private class OperandPanelKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// Redirects the input to the button panel
			// which will fire an event to the appropriate button
			frame.getInputPanel().keyToButton(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	// Operand text fields are selected when clicked
	private class OperandPanelMouseListener implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			frame.getOperandPanel1().setSelected(false);
			frame.getOperandPanel2().setSelected(false);

			JTextField clickedTextField = (JTextField) e.getSource();

			if (frame.getOperandPanel1().compareTextField(clickedTextField)) {
				frame.getOperandPanel1().setSelected(true);
			} else if (frame.getOperandPanel2().compareTextField(clickedTextField)) {
				frame.getOperandPanel2().setSelected(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	// Argument validity checking
	private boolean validInteger(String value) {
		boolean valid = false;

		if (value.length() != 0) {
			if (value.charAt(0) == ' ') {
				value = value.substring(1);
			}

			try {
				Integer.parseInt(value);
				valid = true;
			} catch (NumberFormatException e) {
				valid = false;
			}
		}

		return valid;
	}

	// Operand validity checking
	private void checkValidity() {
		// Checks the validity based on a look up table that contains all types of elements
		// It checks if the last monomial is properly formatted in this case
		boolean valid1 = false;
		if (frame.getOperandPanel1().getOperandType() == OperandType.PolynomialValue) {
			valid1 = PolynomialElement.elementPermitted(frame.getOperandPanel1().getLastPolyElement(),
					PolynomialElement.End);
		} else if (frame.getOperandPanel1().getOperandType() == OperandType.IntegerValue) {
			valid1 = validInteger(frame.getOperandPanel1().getValue());
		}
		frame.getOperandPanel1().setValid(valid1);

		boolean valid2 = false;
		if (frame.getOperandPanel2().getOperandType() == OperandType.PolynomialValue) {
			valid2 = PolynomialElement.elementPermitted(frame.getOperandPanel2().getLastPolyElement(),
					PolynomialElement.End);
		} else if (frame.getOperandPanel2().getOperandType() == OperandType.IntegerValue) {
			valid2 = validInteger(frame.getOperandPanel2().getValue());
		}
		frame.getOperandPanel2().setValid(valid2);

		// Disable or enable the Perform Operation button
		frame.getOperationPanel().setPerformOperationButtonEnabled(valid1 && valid2);
	}

	private class InputButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get input form the event
			InputButton pressedInputButton = (InputButton) e.getSource();
			String value = pressedInputButton.getValue();

			// Get the previously added element
			PolynomialElement prevPe = PolynomialElement.Empty;
			if (frame.getOperandPanel1().isSelected()) {
				prevPe = frame.getOperandPanel1().getLastPolyElement();
			} else if (frame.getOperandPanel2().isSelected()) {
				prevPe = frame.getOperandPanel2().getLastPolyElement();
			}

			// Establish the type of element to be added
			PolynomialElement currentPe = pressedInputButton.getPolynomialElement();
			currentPe = PolynomialElement.convertToApplicableElement(prevPe, currentPe);

			// Send input to panels if it enables future correctness of the
			// expression
			if (PolynomialElement.elementPermitted(prevPe, currentPe)) {
				frame.getOperandPanel1().receiveInput(currentPe, value);
				frame.getOperandPanel2().receiveInput(currentPe, value);
			}

			checkValidity();
		}
	}

	private class BackspaceActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getOperandPanel1().backspace();
			frame.getOperandPanel2().backspace();

			checkValidity();
		}
	}

	private class Clear1ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getOperandPanel1().resetValue();

			checkValidity();
		}
	}

	private class Clear2ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getOperandPanel2().resetValue();

			checkValidity();
		}
	}

	// Changes the operation, also alters view items to display the current
	// operation
	private class OperationButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			OperationButton pressedOperationButton = (OperationButton) e.getSource();
			Operation slectedOperation = pressedOperationButton.getOperation();
			frame.getOperationPanel().setSelectedOperation(slectedOperation);

			frame.getOperandPanel1().setOperand(slectedOperation.getOperand1());
			frame.getOperandPanel2().setOperand(slectedOperation.getOperand2());

			checkValidity();
		}
	}

	// Pressing perform operation loads up the PolynomialCalculator and performs
	// the operation
	// Displays the result
	private class PerformOperationButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String op1 = frame.getOperandPanel1().getValue();
			Object operand1 = null;
			operand1 = frame.getOperandPanel1().getOperandType().parseValue(op1);

			String op2 = frame.getOperandPanel2().getValue();
			Object operand2 = null;
			operand2 = frame.getOperandPanel2().getOperandType().parseValue(op2);

			// The correctness of the operands is ensured by calling
			// checkValidity()
			// every time the fields are changed
			polyCalc.setP1(operand1);
			polyCalc.setP2(operand2);

			switch (frame.getOperationPanel().getSelectedOperation()) {
			case Addition:
				frame.getResultPanel().setResult(polyCalc.add().toString());
				break;
			case Subtraction:
				frame.getResultPanel().setResult(polyCalc.subtract().toString());
				break;
			case Multiplication:
				frame.getResultPanel().setResult(polyCalc.multiply().toString());
				break;
			case Division:
				Polynomial[] result = polyCalc.divide();
				frame.getResultPanel().setResult("q = " + result[0].toString() + "\nr = " + result[1].toString());
				break;
			case ScalarMultiplication:
				frame.getResultPanel().setResult(polyCalc.multiplyScalar().toString());
				break;
			case Differentiation:
				frame.getResultPanel().setResult(polyCalc.differentiate().toString());
				break;
			case Integration:
				frame.getResultPanel().setResult(polyCalc.integrate().toString());
				break;
			}
		}
	}
}
