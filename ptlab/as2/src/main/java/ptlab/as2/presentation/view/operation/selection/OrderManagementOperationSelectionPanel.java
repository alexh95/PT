package ptlab.as2.presentation.view.operation.selection;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import ptlab.as2.presentation.view.operation.Operation;
import ptlab.as2.presentation.view.operation.customer.OrderManagementAddCustomerPanel;
import ptlab.as2.presentation.view.operation.customer.OrderManagementEditCustomerPanel;
import ptlab.as2.presentation.view.operation.customer.OrderManagementRemoveCustomerPanel;
import ptlab.as2.presentation.view.operation.order.OrderManagementAddOrderPanel;
import ptlab.as2.presentation.view.operation.order.OrderManagementEditOrderPanel;
import ptlab.as2.presentation.view.operation.order.OrderManagementRemoveOrderPanel;
import ptlab.as2.presentation.view.operation.product.OrderManagementAddProductPanel;
import ptlab.as2.presentation.view.operation.product.OrderManagementEditProductPanel;
import ptlab.as2.presentation.view.operation.product.OrderManagementRemoveProductPanel;
import ptlab.as2.presentation.view.operation.stock.OrderManagementEditStockPanel;

public class OrderManagementOperationSelectionPanel extends JPanel {
	private static final long serialVersionUID = -7504335749499941019L;

	// This is also a holder for the operation holders
	private OrderManagementAddCustomerPanel addCustomerPanel;
	private OrderManagementEditCustomerPanel editCustomerPanel;
	private OrderManagementRemoveCustomerPanel removeCustomerPanel;

	private OrderManagementAddProductPanel addProductPanel;
	private OrderManagementEditProductPanel editProductPanel;
	private OrderManagementRemoveProductPanel removeProductPanel;

	private OrderManagementAddOrderPanel addOrderPanel;
	private OrderManagementEditOrderPanel editOrderPanel;
	private OrderManagementRemoveOrderPanel removeOrderPanel;

	private OrderManagementEditStockPanel editStockPanel;

	private OperationSelectionToggleButton addCustomer;
	private OperationSelectionToggleButton editCustomer;
	private OperationSelectionToggleButton removeCustomer;

	private OperationSelectionToggleButton addProduct;
	private OperationSelectionToggleButton editProduct;
	private OperationSelectionToggleButton removeProduct;

	private OperationSelectionToggleButton addOrder;
	private OperationSelectionToggleButton editOrder;
	private OperationSelectionToggleButton removeOrder;

	private OperationSelectionToggleButton editStock;

	private ArrayList<OperationSelectionToggleButton> selectionButtons;

	public OrderManagementOperationSelectionPanel() {
		super(new GridLayout(1, 4));

		// Operation panels
		addCustomerPanel = new OrderManagementAddCustomerPanel();
		editCustomerPanel = new OrderManagementEditCustomerPanel();
		removeCustomerPanel = new OrderManagementRemoveCustomerPanel();

		addProductPanel = new OrderManagementAddProductPanel();
		editProductPanel = new OrderManagementEditProductPanel();
		removeProductPanel = new OrderManagementRemoveProductPanel();

		addOrderPanel = new OrderManagementAddOrderPanel();
		editOrderPanel = new OrderManagementEditOrderPanel();
		removeOrderPanel = new OrderManagementRemoveOrderPanel();

		editStockPanel = new OrderManagementEditStockPanel();

		// Operation selection buttons
		addCustomer = new OperationSelectionToggleButton(Operation.AddCustomer, addCustomerPanel);
		editCustomer = new OperationSelectionToggleButton(Operation.EditCustomer, editCustomerPanel);
		removeCustomer = new OperationSelectionToggleButton(Operation.RemoveCustomer, removeCustomerPanel);

		addProduct = new OperationSelectionToggleButton(Operation.AddProduct, addProductPanel);
		editProduct = new OperationSelectionToggleButton(Operation.EditProduct, editProductPanel);
		removeProduct = new OperationSelectionToggleButton(Operation.RemoveProduct, removeProductPanel);

		addOrder = new OperationSelectionToggleButton(Operation.AddOrder, addOrderPanel);
		editOrder = new OperationSelectionToggleButton(Operation.EditOrder, editOrderPanel);
		removeOrder = new OperationSelectionToggleButton(Operation.RemoveOrder, removeOrderPanel);

		editStock = new OperationSelectionToggleButton(Operation.EditStock, editStockPanel);
		editStock.setSelected(true);

		// Add the buttons to a list - will be used for the listener
		selectionButtons = new ArrayList<OperationSelectionToggleButton>();
		
		selectionButtons.add(addCustomer);
		selectionButtons.add(editCustomer);
		selectionButtons.add(removeCustomer);

		selectionButtons.add(addProduct);
		selectionButtons.add(editProduct);
		selectionButtons.add(removeProduct);

		selectionButtons.add(addOrder);
		selectionButtons.add(editOrder);
		selectionButtons.add(removeOrder);

		selectionButtons.add(editStock);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(addCustomer);
		buttonGroup.add(editCustomer);
		buttonGroup.add(removeCustomer);

		buttonGroup.add(addProduct);
		buttonGroup.add(editProduct);
		buttonGroup.add(removeProduct);

		buttonGroup.add(addOrder);
		buttonGroup.add(editOrder);
		buttonGroup.add(removeOrder);

		buttonGroup.add(editStock);

		JPanel customerButtonPanel = new JPanel(new GridLayout(3, 1));
		customerButtonPanel.add(addCustomer);
		customerButtonPanel.add(editCustomer);
		customerButtonPanel.add(removeCustomer);
		customerButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));

		JPanel productButtonPanel = new JPanel(new GridLayout(3, 1));
		productButtonPanel.add(addProduct);
		productButtonPanel.add(editProduct);
		productButtonPanel.add(removeProduct);
		productButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));

		JPanel orderButtonPanel = new JPanel(new GridLayout(2, 1));
		orderButtonPanel.add(addOrder);
		//orderButtonPanel.add(editOrder);
		orderButtonPanel.add(removeOrder);
		orderButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));

		JPanel stockButtonPanel = new JPanel(new GridLayout(1, 1));
		stockButtonPanel.add(editStock);
		stockButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));

		add(customerButtonPanel);
		add(productButtonPanel);
		add(orderButtonPanel);
		add(stockButtonPanel);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public void addSelectionButtonsActionListener(ActionListener l) {
		for (OperationSelectionToggleButton b : selectionButtons) {
			b.addActionListener(l);
		}
	}

	public OrderManagementAddCustomerPanel getAddCustomerPanel() {
		return addCustomerPanel;
	}

	public OrderManagementEditCustomerPanel getEditCustomerPanel() {
		return editCustomerPanel;
	}

	public OrderManagementRemoveCustomerPanel getRemoveCustomerPanel() {
		return removeCustomerPanel;
	}

	public OrderManagementAddProductPanel getAddProductPanel() {
		return addProductPanel;
	}

	public OrderManagementEditProductPanel getEditProductPanel() {
		return editProductPanel;
	}

	public OrderManagementRemoveProductPanel getRemoveProductPanel() {
		return removeProductPanel;
	}

	public OrderManagementAddOrderPanel getAddOrderPanel() {
		return addOrderPanel;
	}

	public OrderManagementEditOrderPanel getEditOrderPanel() {
		return editOrderPanel;
	}

	public OrderManagementRemoveOrderPanel getRemoveOrderPanel() {
		return removeOrderPanel;
	}

	public OrderManagementEditStockPanel getEditStockPanel() {
		return editStockPanel;
	}
}
