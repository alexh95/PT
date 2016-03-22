package alex.as1.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import alex.as1.model.polynomial.operation.Operand;

/**
 * Main Window containing all the view elements.
 */
public class PolynomialCalculatorJFrame extends JFrame {
	private static final long serialVersionUID = 1787893541052635311L;

	private static final String JFRAME_NAME = "PolyCalc";

	private static int width = 600;
	private static int height = 600;

	private JPanel mainPane;
	
	// Input operand area
	private OperandPanel op1;
	private OperandPanel op2;
	
	// Result area
	private ResultPanel resultPanel;
	
	// Input button area
	private InputPanel inputPanel;
	
	// Operations area
	private OperationPanel operationPanel;
	
	public PolynomialCalculatorJFrame() {
		super(JFRAME_NAME);
		// Frame initialization
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		// Set it in the center of the screen
		Dimension screenDim = getToolkit().getScreenSize();
		width = (int) (screenDim.getWidth() / 4);
		height = (int) (3 * screenDim.getHeight() / 4);
		int xStartPos = (int) ((screenDim.getWidth() - width) / 2);
		int yStartPos = (int) ((screenDim.getHeight() - height) / 2);
		setBounds(xStartPos, yStartPos, width, height);

		/* The main panel will contain 4 sections:
		 * 1. Input operand fields (with details and feedback)
		 * 2. Result text area (detailed display)
		 * 3. Input button area (to access the Input fields, linked to key events)
		 * 4. Operations area
		 */
		mainPane = new JPanel(new GridLayout(4, 1));

		// Input operand area
		JPanel inputOperandPanel = new JPanel(new GridLayout(2, 1));
		op1 = new OperandPanel(Operand.AugendPolynomial);
		op2 = new OperandPanel(Operand.AdeendPolynomial);
		inputOperandPanel.add(op1);
		inputOperandPanel.add(op2);
		inputOperandPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		mainPane.add(inputOperandPanel);
		
		// Result area
		resultPanel = new ResultPanel();
		mainPane.add(resultPanel);
		
		// Input button area
		inputPanel = new InputPanel();
		mainPane.add(inputPanel);
		
		// Operations Button Panel
		operationPanel = new OperationPanel();
		mainPane.setFocusable(true);
		mainPane.add(operationPanel);
		
		// Set visible
		setContentPane(mainPane);
		setVisible(true);
	}
	
	public void addMainPaneKeyListener(KeyListener l) {
		mainPane.addKeyListener(l);
		mainPane.requestFocus();
	}
	
	public OperandPanel getOperandPanel1() {
		return op1;
	}
	
	public OperandPanel getOperandPanel2() {
		return op2;
	}
	
	public ResultPanel getResultPanel() {
		return resultPanel;
	}
	
	public InputPanel getInputPanel() {
		return inputPanel;
	}
	
	public OperationPanel getOperationPanel() {
		return operationPanel;
	}
}
