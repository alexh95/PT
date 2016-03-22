package alex.as1.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Panel containing a text area used to output the result of operations.
 */
public class ResultPanel extends JPanel {
	private static final long serialVersionUID = -8289428771181044117L;

	private static final String RESULT_LABEL_TEXT = "Result";

	private JTextArea resultTextArea;

	public ResultPanel() {
		super(new BorderLayout());

		JLabel resultLabel = new JLabel(RESULT_LABEL_TEXT);
		resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		resultTextArea.setFocusable(false);

		add(resultLabel, BorderLayout.NORTH);
		add(resultTextArea, BorderLayout.CENTER);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}
	
	public void setResult(String text) {
		resultTextArea.setText(text);
	}
}
