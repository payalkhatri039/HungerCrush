package edu.neu.csye6220.pdf;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import edu.neu.csye6220.model.Order;
import edu.neu.csye6220.model.OrderItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GeneratePDF extends AbstractPdfView
	{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfDocument, PdfWriter pdfWriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PdfPTable pdfTable = new PdfPTable(5);
		Paragraph p1 = new Paragraph("Past Orders Are As Follows");
		Paragraph p2 = new Paragraph(" ");
		pdfTable.setWidths(new int[]{40, 40, 40, 40, 40});
		pdfTable.addCell("Order ID");
		pdfTable.addCell("Customer First Name");
		pdfTable.addCell("Customer Last Name");
		pdfTable.addCell("Order Date");
		pdfTable.addCell("Total Order Cost");

		List<Order> allOrders = (List<Order>) model.get("allOrders");

		if(allOrders != null)
		{
		 for (Order order : allOrders)
		 {
			 pdfTable.addCell(String.valueOf(order.getOrderId()));
			 pdfTable.addCell(order.getCustomer().getUser().getFname());
			 pdfTable.addCell(order.getCustomer().getUser().getLname());
			 pdfTable.addCell(String.valueOf(order.getDate()));
//			 for(OrderItem orderitem:order.getOrderitems())
//			 {
//				 pdfTable.addCell(orderitem.getFoodName());
//				 pdfTable.addCell(String.valueOf(orderitem.getQuantity()));
//				 pdfTable.addCell(String.valueOf(orderitem.getCost()));
//
//			 }
			 pdfTable.addCell(String.valueOf(order.getTotalCost()));
		 }
		}

		pdfDocument.add(p1);
		pdfDocument.add(p2);
		pdfDocument.add(pdfTable);
	}

}
