package ptlab.as2.presentation.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanelHolder;
import ptlab.as2.presentation.view.operation.selection.OrderManagementOperationSelectionPanel;

public class OrderManagemenFrame extends JFrame {
	private static final long serialVersionUID = 7759814160642011796L;

	private static final String TITLE = "Order Management";

	private OrderManagementOperationSelectionPanel selectionPanel;
	private OrderManagementOperationPanelHolder operationPanel;

	public OrderManagemenFrame() {
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		selectionPanel = new OrderManagementOperationSelectionPanel();
		operationPanel = new OrderManagementOperationPanelHolder(selectionPanel.getEditStockPanel());

		Dimension screenDimension = getToolkit().getScreenSize();
		int frameWidth = screenDimension.width / 2;
		int frameHeight = screenDimension.height / 2;
		int x = (screenDimension.width - frameWidth) / 2;
		int y = (screenDimension.height - frameHeight) / 2;

		setBounds(x, y, frameWidth, frameHeight);

		setLayout(new BorderLayout());

		add(selectionPanel, BorderLayout.NORTH);

		add(operationPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public OrderManagementOperationSelectionPanel getSelectionPanel() {
		return selectionPanel;
	}

	public OrderManagementOperationPanelHolder getOperationPanel() {
		return operationPanel;
	}
}
