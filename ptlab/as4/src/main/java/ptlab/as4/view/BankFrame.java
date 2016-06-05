package ptlab.as4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class BankFrame extends JFrame {
	private static final long serialVersionUID = -7959053277521413754L;

	private static final String TITLE = "Bank";
	private static final double WIDTH_RATIO = 0.75;
	private static final double HEIGHT_RATIO = 0.75;
	
	// Person panel
	private JTextField personNameTextField;
	private JButton personAddButton;
	private JButton personRemoveButton;
	private JTable personTable;
	
	// Account panel
	private JComboBox<String> accountTypeComboBox;
	private JButton accountAddButton;
	private JButton accountRemoveButton;
	private JTable accountTable;
	
	// Transaction panel
	private JTextField transactionDepositTextField;
	private JButton transactionDepositButton;
	private JTextField transactionWithdrawTextField;
	private JButton transactionWithdrawButton;
	
	public BankFrame() {
		super(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d = getToolkit().getScreenSize();
		int w = (int) (d.getWidth() * WIDTH_RATIO);
		int h = (int) (d.getHeight() * HEIGHT_RATIO);
		int x = (int) ((d.width - w) / 2);
		int y = (int) ((d.height - h) / 2);
		setBounds(x, y, w, h);
		setResizable(false);
	
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 3));
		// Person panel
		JPanel personPanel = new JPanel(new GridLayout(2, 1));
		JPanel personControlsPanel = new JPanel(new GridLayout(2, 2));
		JLabel personNameLabel = new JLabel("Person name:");
		personControlsPanel.add(personNameLabel);
		personNameTextField = new JTextField(12);
		personControlsPanel.add(personNameTextField);
		personAddButton = new JButton("Add person");
		personControlsPanel.add(personAddButton);
		personRemoveButton = new JButton("Remove selected person from table");
		personControlsPanel.add(personRemoveButton);
		personPanel.add(personControlsPanel);
		personTable = new JNotEditableTable();
		personPanel.add(new JScrollPane(personTable));
		personPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		mainPanel.add(personPanel);
		
		// Account panel
		JPanel accountPanel = new JPanel(new GridLayout(2, 1));
		JPanel accountControlsPanel = new JPanel(new GridLayout(2, 2));
		JLabel accountTypeLabel = new JLabel("Select the type of the account");
		accountControlsPanel.add(accountTypeLabel);
		accountTypeComboBox = new JComboBox<>(new String[]{"Saving Account", "Spending Account"});
		accountControlsPanel.add(accountTypeComboBox);
		accountAddButton = new JButton("Add account");
		accountControlsPanel.add(accountAddButton);
		accountRemoveButton = new JButton("Remove account");
		accountControlsPanel.add(accountRemoveButton);
		accountPanel.add(accountControlsPanel);
		accountTable = new JNotEditableTable();
		accountPanel.add(new JScrollPane(accountTable));
		accountPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		mainPanel.add(accountPanel);
		
		// Transaction panel
		JPanel transactionPanel = new JPanel(new GridLayout(2, 3));
		JLabel transactionDepositLabel = new JLabel("Sum to deposit");
		transactionPanel.add(transactionDepositLabel);
		transactionDepositTextField = new JTextField(12);
		transactionPanel.add(transactionDepositTextField);
		transactionDepositButton = new JButton("Deposit");
		transactionPanel.add(transactionDepositButton);
		JLabel transactionWithdrawLabel = new JLabel("Sum to withdraw");
		transactionPanel.add(transactionWithdrawLabel);
		transactionWithdrawTextField = new JTextField(12);
		transactionPanel.add(transactionWithdrawTextField);
		transactionWithdrawButton = new JButton("Withdraw");
		transactionPanel.add(transactionWithdrawButton);
		transactionPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		mainPanel.add(transactionPanel);
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	// Person Panel
	public String getPersonName() {
		return personNameTextField.getText();
	}
	
	public void resetPersonName() {
		personNameTextField.setText("");
	}
	
	public void addPersonAddButtonActionListener(ActionListener l) {
		personAddButton.addActionListener(l);
	}
	
	public void addPersonRemoveButtonActionListener(ActionListener l) {
		personRemoveButton.addActionListener(l);
	}
	
	public void setPersonRemoveButtonEnabled(boolean b) {
		personRemoveButton.setEnabled(b);
	}
	
	public void addPersonTableListSelectionListener(ListSelectionListener l) {
		personTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getSelectedPersonTableRow() {
		return personTable.getSelectedRow();
	}
	
	public void setPersonPanelTableModel(TableModel tm) {
		personTable.setModel(tm);
	}
	
	// Account Panel
	public int getAccountTypeSelection() {
		return accountTypeComboBox.getSelectedIndex();
	}
	
	public void addAccountAddButtonActionListener(ActionListener l) {
		accountAddButton.addActionListener(l);
	}
	
	public void addAccountRemoveButtonActionListener(ActionListener l) {
		accountRemoveButton.addActionListener(l);
	}
	
	public void setAccountAddButtonEnabled(boolean b) {
		accountAddButton.setEnabled(b);
	}
	
	public void setAccountRemoveButtonEnabled(boolean b) {
		accountRemoveButton.setEnabled(b);
	}
	
	public void addAccountTableListSelectionListener(ListSelectionListener l) {
		accountTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getSelectedAccountTableRow() {
		return accountTable.getSelectedRow();
	}
	
	public void setAccountPanelTableModel(TableModel tm) {
		accountTable.setModel(tm);
	}
	
	// Transaction Panel
	public void addTransactionDepositButtonActionListener(ActionListener l) {
		transactionDepositButton.addActionListener(l);
	}
	
	public void addTransactionWithdrawButtonActionListener(ActionListener l) {
		transactionWithdrawButton.addActionListener(l);
	}
	
	public void setTransactionDepositButtonEnabled(boolean b) {
		transactionDepositButton.setEnabled(b);
	}
	
	public void setTransactionWithdrawButtonEnabled(boolean b) {
		transactionWithdrawButton.setEnabled(b);
	}
	
	public String getTransactionDepositSum() {
		return transactionDepositTextField.getText();
	}
	
	public String getTransactionWithdrawSum() {
		return transactionWithdrawTextField.getText();
	}
	
	public void resetTransactionDepositSum() {
		transactionDepositTextField.setText("");
	}
	
	public void resetTransactionWithdrawSum() {
		transactionWithdrawTextField.setText("");
	}
}
