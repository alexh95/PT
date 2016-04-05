package ptlab.as2.presentation.view.operation.product;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import ptlab.as2.presentation.view.operation.JNotEditableTable;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;

public class OrderManagementAddProductPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 6483178971722550319L;

	private JNotEditableTable productTable;
	
	private JTextField productNameTextField;
	private JButton productConfirmButton;
	
	public OrderManagementAddProductPanel() {
		super(new GridLayout(1, 2));
		
		productTable = new JNotEditableTable();
		add(new JScrollPane(productTable));
		
		JPanel addProductPanel = new JPanel(new GridLayout(3, 1));
		JLabel productNameLabel = new JLabel("product name:");
		addProductPanel.add(productNameLabel);
		productNameTextField = new JTextField();
		addProductPanel.add(productNameTextField);
		productConfirmButton = new JButton("Confirm and add product");
		addProductPanel.add(productConfirmButton);
		add(addProductPanel);
	}
	
	public void setProductTableModel(TableModel tm) {
		productTable.setModel(tm);
	}

	public void resetProductName() {
		productNameTextField.setText("");
	}
	
	public String getProductName() {
		return productNameTextField.getText();
	}
	
	public void addProductConfirmButtonActionListener(ActionListener l) {
		productConfirmButton.addActionListener(l);
	}
}
