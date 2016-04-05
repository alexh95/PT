package ptlab.as2.bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import ptlab.as2.dal.ReceiptDataAccess;
import ptlab.as2.model.Order;
import ptlab.as2.model.OrderProducts;
import ptlab.as2.model.table.CustomerTableElement;
import ptlab.as2.model.table.OrderProductsTableElement;
import ptlab.as2.model.table.OrderTableElement;
import ptlab.as2.model.table.ProductTableElement;

public class ReceiptBusinessLogic {

	private static ReceiptBusinessLogic instance = null;

	public static ReceiptBusinessLogic getInstance() {
		if (instance == null) {
			instance = new ReceiptBusinessLogic();
		}
		return instance;
	}

	private ReceiptBusinessLogic() {
	}

	public void printReceipt(Order order, List<OrderProducts> orderProducts) {
		try {
			PrintWriter pw = new PrintWriter(new File("receipt.txt"));

			OrderTableElement ote = new OrderTableElement(order.getId(), order.getCustomerId());
			CustomerTableElement cte = ReceiptDataAccess.getInstance().getCustomer(ote);
			// too bad '\n' does not work
			pw.println("Customer:");
			pw.printf("id = %d", cte.getId());
			pw.println("");
			pw.printf("name = %s", cte.getName());
			pw.println("");
			pw.println("");

			pw.printf("Number of products: %d", orderProducts.size());
			pw.println("");
			pw.println("");

			int index = 1;
			for (OrderProducts op : orderProducts) {
				OrderProductsTableElement opte = new OrderProductsTableElement(op.getIdOrder(), op.getIdProduct(),
						op.getQuantity());
				ProductTableElement pte = ReceiptDataAccess.getInstance().getProduct(opte);
				pw.printf("Product %d:", index);
				pw.println("");
				pw.printf("id = %d", pte.getId());
				pw.println("");
				pw.printf("name = %s", pte.getName());
				pw.println("");
				pw.printf("quantity = %d", pte.getQuantity());
				pw.println("");
				pw.println("");
				index++;
			}

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
