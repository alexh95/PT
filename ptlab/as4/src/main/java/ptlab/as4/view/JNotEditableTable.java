package ptlab.as4.view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JNotEditableTable extends JTable {
	private static final long serialVersionUID = 4248375695547960545L;

	public JNotEditableTable(TableModel tm) {
		super(tm);
	}
	
	public JNotEditableTable() {
		super();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
