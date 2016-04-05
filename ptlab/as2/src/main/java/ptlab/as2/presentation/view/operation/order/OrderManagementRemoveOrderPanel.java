package ptlab.as2.presentation.view.operation.order;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import ptlab.as2.presentation.view.operation.JNotEditableTable;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;

public class OrderManagementRemoveOrderPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 8189930080121506919L;

	private JNotEditableTable orderTable;
	private JNotEditableTable orderProductsTable;
	
	private JButton orderConfirmDelete;
	private JTextField orderDeleteResult;
	
	private JButton orderPrintReceipt;

	public OrderManagementRemoveOrderPanel() {
		super(new GridLayout(1, 3));

		// order table
		orderTable = new JNotEditableTable();
		add(new JScrollPane(orderTable));

		// order products table
		orderProductsTable = new JNotEditableTable();
		add(new JScrollPane(orderProductsTable));
		
		// delete panel
		JPanel deleteOrderPanel = new JPanel(new GridLayout(4, 1));
		JLabel deleteOrderPanelLabel = new JLabel("Delete the order, products are restocked");
		deleteOrderPanel.add(deleteOrderPanelLabel);
		orderConfirmDelete = new JButton("Delete order");
		deleteOrderPanel.add(orderConfirmDelete);
		orderDeleteResult = new JTextField();
		orderDeleteResult.setEditable(false);
		deleteOrderPanel.add(orderDeleteResult);
		orderPrintReceipt = new JButton("Print Receipt");
		deleteOrderPanel.add(orderPrintReceipt);
		add(deleteOrderPanel);
	}

	public void addOrderTableListSelectionListener(ListSelectionListener l) {
		orderTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getOrderRowCount() {
		return orderTable.getRowCount();
	}
	
	public long getSelectedOrderId() {
		int selectedRow = orderTable.getSelectedRow();
		return Long.parseLong((String) orderTable.getValueAt(selectedRow, 0));
	}
	
	public long getSelectedOrderCustomerId() {
		int selectedRow = orderTable.getSelectedRow();
		return Long.parseLong((String) orderTable.getValueAt(selectedRow, 1));
	}
	
	public int getOrderTableSelectedRow() {
		return orderTable.getSelectedRow();
	}
	
	public void setProductTableModel(TableModel tm) {
		orderTable.setModel(tm);
	}
	
	public void setOrderProductsTableModel(TableModel tm) {
		orderProductsTable.setModel(tm);
	}
	
	public void addOrderConfirmDeleteActionListener(ActionListener l) {
		orderConfirmDelete.addActionListener(l);
	}
	
	public void setOrderConfirmDeleteButtonEnabled(boolean b) {
		orderConfirmDelete.setEnabled(b);
	}
	
	public void setOrderDeleteResultText(String result) {
		orderDeleteResult.setText(result);
	}
	
	public void addPrintReceiptActionListener(ActionListener l) {
		orderPrintReceipt.addActionListener(l);
	}
	
	public void setPrintReceiptEnabled(boolean b) {
		orderPrintReceipt.setEnabled(b);
	}
}
