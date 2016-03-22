package alex.as1.view;

import javax.swing.JToggleButton;

import alex.as1.model.polynomial.operation.Operation;

/**
 * OperationButton is used to change the selected operation. It modifies the
 * controller state and the operand panels.
 */
public class OperationButton extends JToggleButton {
	private static final long serialVersionUID = 801869684395872162L;

	private Operation operation;

	public OperationButton(String name, Operation operation) {
		super(name);
		setFocusable(false);

		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}
}
