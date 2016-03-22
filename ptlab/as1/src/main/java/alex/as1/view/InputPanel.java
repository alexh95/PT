package alex.as1.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import alex.as1.model.polynomial.PolynomialElement;

/**
 * This is the view item responsible for the input, used to send data to the
 * operand fields. It is linked to key events and PolynomialElement checking.
 */
public class InputPanel extends JPanel {
	private static final long serialVersionUID = 150409170624163539L;

	private InputButton b0;
	private InputButton b1;
	private InputButton b2;
	private InputButton b3;
	private InputButton b4;
	private InputButton b5;
	private InputButton b6;
	private InputButton b7;
	private InputButton b8;
	private InputButton b9;
	private InputButton bp;
	private InputButton bm;
	private InputButton bx;
	private InputButton bc;

	private InputButton bBackspace;
	private InputButton bClearOp1;
	private InputButton bClearOp2;

	private ArrayList<InputButton> inputButtonList;

	public InputPanel() {
		super(new GridLayout(4, 5));

		b0 = new InputButton("0", KeyEvent.VK_0, KeyEvent.VK_NUMPAD0, "0", PolynomialElement.ZeroCoefficientDigit);
		b1 = new InputButton("1", KeyEvent.VK_1, KeyEvent.VK_NUMPAD1, "1", PolynomialElement.NonZeroCoefficientDigit);
		b2 = new InputButton("2", KeyEvent.VK_2, KeyEvent.VK_NUMPAD2, "2", PolynomialElement.NonZeroCoefficientDigit);
		b3 = new InputButton("3", KeyEvent.VK_3, KeyEvent.VK_NUMPAD3, "3", PolynomialElement.NonZeroCoefficientDigit);
		b4 = new InputButton("4", KeyEvent.VK_4, KeyEvent.VK_NUMPAD4, "4", PolynomialElement.NonZeroCoefficientDigit);
		b5 = new InputButton("5", KeyEvent.VK_5, KeyEvent.VK_NUMPAD5, "5", PolynomialElement.NonZeroCoefficientDigit);
		b6 = new InputButton("6", KeyEvent.VK_6, KeyEvent.VK_NUMPAD6, "6", PolynomialElement.NonZeroCoefficientDigit);
		b7 = new InputButton("7", KeyEvent.VK_7, KeyEvent.VK_NUMPAD7, "7", PolynomialElement.NonZeroCoefficientDigit);
		b8 = new InputButton("8", KeyEvent.VK_8, KeyEvent.VK_NUMPAD8, "8", PolynomialElement.NonZeroCoefficientDigit);
		b9 = new InputButton("9", KeyEvent.VK_9, KeyEvent.VK_NUMPAD9, "9", PolynomialElement.NonZeroCoefficientDigit);
		bp = new InputButton("+", 61, 107, " +", PolynomialElement.Plus);
		bm = new InputButton("-", KeyEvent.VK_MINUS, 109, " -", PolynomialElement.Minus);
		bx = new InputButton("X", KeyEvent.VK_X, KeyEvent.VK_X, "x", PolynomialElement.Variable);
		bc = new InputButton("^ (C,*)", KeyEvent.VK_C, 106, "^", PolynomialElement.Caret);

		bBackspace = new InputButton("Bcksp", KeyEvent.VK_BACK_SPACE, 8, "", null);
		bClearOp1 = new InputButton("Clr1", KeyEvent.VK_Q, KeyEvent.VK_Q, "", null);
		bClearOp2 = new InputButton("Clr2", KeyEvent.VK_W, KeyEvent.VK_W, "", null);

		inputButtonList = new ArrayList<InputButton>();
		inputButtonList.add(b0);
		inputButtonList.add(b1);
		inputButtonList.add(b2);
		inputButtonList.add(b3);
		inputButtonList.add(b4);
		inputButtonList.add(b5);
		inputButtonList.add(b6);
		inputButtonList.add(b7);
		inputButtonList.add(b8);
		inputButtonList.add(b9);
		inputButtonList.add(bp);
		inputButtonList.add(bm);
		inputButtonList.add(bx);
		inputButtonList.add(bc);

		inputButtonList.add(bBackspace);
		inputButtonList.add(bClearOp1);
		inputButtonList.add(bClearOp2);

		for (InputButton b : inputButtonList) {
			add(b);
		}

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public void addInputButtonActionListener(ActionListener a) {
		for (InputButton b : inputButtonList) {
			b.addActionListener(a);
		}
	}

	/**
	 * This is used to redirect Keyboard input to InputButons
	 * so there is only one way to alter the operand fields
	 */
	public void keyToButton(int id) {
		for (InputButton b : inputButtonList) {
			if (b.getId1() == id || b.getId2() == id) {
				for (ActionListener l : b.getActionListeners()) {
					l.actionPerformed(new ActionEvent(b, ActionEvent.ACTION_PERFORMED, null));
				}
			}
		}
	}

	public void addBackSpaceActionListener(ActionListener l) {
		bBackspace.addActionListener(l);
	}

	public void addClear1ActionListener(ActionListener l) {
		bClearOp1.addActionListener(l);
	}

	public void addClear2ActionListener(ActionListener l) {
		bClearOp2.addActionListener(l);
	}
}
