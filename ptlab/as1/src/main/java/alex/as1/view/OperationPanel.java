package alex.as1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import alex.as1.model.polynomial.operation.Operation;

/**
 * OperationPanel holds the buttons used to change the operations. Also contains
 * the button to perform said operation.
 */
public class OperationPanel extends JPanel {
	private static final long serialVersionUID = 8613202163604230894L;

	private Operation selectedOperation;

	private OperationButton bAddition;
	private OperationButton bSubtraction;
	private OperationButton bMultiplication;
	private OperationButton bDivision;
	private OperationButton bScalarMultiplication;
	private OperationButton bDifferentiation;
	private OperationButton bIntegration;

	private static final String PERFORM_OPERATION_BUTTON_TEXT = "Perform Operation";
	private static final String PERFORM_OPERATION_BUTTON_INCORRECT_STATUS = " (Correct input must be inserted)";
	private JButton performOperationButton;

	public OperationPanel() {
		super(new BorderLayout());

		JLabel panelLabel = new JLabel("Operations");
		add(panelLabel, BorderLayout.BEFORE_FIRST_LINE);

		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		// These are toggle buttons belonging to the same group
		// So the selected operation remains pressed down
		ButtonGroup bGroup = new ButtonGroup();

		bAddition = new OperationButton("P1 + P2", Operation.Addition);
		bSubtraction = new OperationButton("P1 - P2", Operation.Subtraction);
		bMultiplication = new OperationButton("P1 * P2", Operation.Multiplication);
		bDivision = new OperationButton("P1 / P2", Operation.Division);
		bScalarMultiplication = new OperationButton("P * S", Operation.ScalarMultiplication);
		bDifferentiation = new OperationButton("d^N P / d x^N", Operation.Differentiation);
		bIntegration = new OperationButton("integrate(P)", Operation.Integration);

		bGroup.add(bAddition);
		bGroup.add(bSubtraction);
		bGroup.add(bMultiplication);
		bGroup.add(bDivision);
		bGroup.add(bScalarMultiplication);
		bGroup.add(bDifferentiation);
		bGroup.add(bIntegration);

		JPanel buttonBinPolyOpPanel = new JPanel(new GridLayout(2, 2));

		buttonBinPolyOpPanel.add(bAddition);
		buttonBinPolyOpPanel.add(bSubtraction);
		buttonBinPolyOpPanel.add(bMultiplication);
		buttonBinPolyOpPanel.add(bDivision);

		JPanel buttonUniPolyOpPanel = new JPanel(new GridLayout(1, 3));

		buttonUniPolyOpPanel.add(bScalarMultiplication);
		buttonUniPolyOpPanel.add(bDifferentiation);
		buttonUniPolyOpPanel.add(bIntegration);

		buttonPanel.add(buttonBinPolyOpPanel);
		buttonPanel.add(buttonUniPolyOpPanel);
		add(buttonPanel, BorderLayout.CENTER);

		bAddition.setSelected(true);

		performOperationButton = new JButton("Perform Operation");
		performOperationButton.setFocusable(false);
		setPerformOperationButtonEnabled(false);
		add(performOperationButton, BorderLayout.SOUTH);

		setSelectedOperation(Operation.Addition);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public Operation getSelectedOperation() {
		return selectedOperation;
	}

	public void setSelectedOperation(Operation selectedOperation) {
		this.selectedOperation = selectedOperation;
	}

	public void setPerformOperationButtonEnabled(Boolean b) {
		if (b != performOperationButton.isEnabled()) {
			String newText = PERFORM_OPERATION_BUTTON_TEXT;

			if (!b) {
				newText += PERFORM_OPERATION_BUTTON_INCORRECT_STATUS;
			}

			performOperationButton.setText(newText);
			performOperationButton.setEnabled(b);
		}
	}

	public void addOperationButtonActionListener(ActionListener a) {
		bAddition.addActionListener(a);
		bSubtraction.addActionListener(a);
		bMultiplication.addActionListener(a);
		bScalarMultiplication.addActionListener(a);
		bDivision.addActionListener(a);
		bDifferentiation.addActionListener(a);
		bIntegration.addActionListener(a);
	}

	public void addPerformOperationButtonActionListener(ActionListener a) {
		performOperationButton.addActionListener(a);
	}
}
