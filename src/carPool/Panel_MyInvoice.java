package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Panel_MyInvoice extends JPanel {
	private JTable table;
	private String firstName;
	private String lastName;
	private int memberID;
	DataConnection dbConn = null;
	MainFrame parent = null;

	JLabel lblAmountResult = new JLabel("On pending");

	public Panel_MyInvoice(MainFrame frame, DataConnection connection, int id,
			String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;

		setLayout(new MigLayout("", "[156.00,grow][][][][][][][][]", "[][][][][][][][][][][205.00,grow][][][][]"));

		Set<String> bookingIdSet = new HashSet<String>();
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(date);
		System.out.println("currentDate : " + currentDate);
				
						JLabel lblName = new JLabel("");
						add(lblName, "cell 8 0,alignx right");
						lblName.setText("Name : " + this.firstName + " " + this.lastName);			
		
		
				JLabel lblMemberIdResult = new JLabel("");
				add(lblMemberIdResult, "cell 8 1,alignx right");
				lblMemberIdResult.setText("MemberID : " + memberID);
		
		JLabel InvoiceDate = new JLabel(" Invoice Date");
		add(InvoiceDate, "flowx,cell 3 3,alignx right");
		InvoiceDate.setText("Date : "+currentDate);
		
		String query1 = " SELECT BOOKING.bookingid FROM  BOOKING INNER JOIN ROUTE_TIME  ON ROUTE_TIME.RouteTimeID = BOOKING.RouteTimeID WHERE TimeTo < To_Date(' "
				+ currentDate + "','YYYY/MM/DD HH24:MI:SS')";
		System.out.println("query1 : " + query1);
		ArrayList<String[]> bookingIdList = dbConn
				.executeSelectQueryEnhanced(query1);

		for (int i = 0; i < bookingIdList.size(); i++) {
			String query2 = "  SELECT MemberID FROM MEMBERBOOKING WHERE  isVehicleProvider = 'No' AND BookingID = "
					+ bookingIdList.get(i)[0];
			System.out.println("query2 : " + query2);
			System.out.println("bookingIdList.get(i) : "
					+ bookingIdList.get(i)[0]);
			ArrayList<String[]> joinedMembsList = dbConn
					.executeSelectQueryEnhanced(query2);

			String query4 = "  SELECT MemberID FROM MEMBERBOOKING WHERE  isVehicleProvider = 'Yes' AND BookingID = "
					+ bookingIdList.get(i)[0];
			String offerer = dbConn.executeSelectQueryEnhanced(query4).get(0)[0];
			String checkDupRowQuery = " SELECT * FROM  TAX_INVOICE WHERE  BookingID ="
					+ bookingIdList.get(i)[0]
					+ " AND MemberID ="
					+ offerer
					+ "AND  ChargeID = 'PER-DIS'";
			ArrayList<String[]> aList = dbConn
					.executeSelectQueryEnhanced(checkDupRowQuery);
			if (aList.isEmpty()) {
				String query5 = " INSERT INTO  TAX_INVOICE(TaxInvoiceID,BookingID,MemberID,ChargeID,Date_Time )VALUES(seq_TaxInvoiceID.nextval,"
						+ bookingIdList.get(i)[0]
						+ ","
						+ offerer
						+ " ,'PER-DIS', To_Date('"
						+ currentDate
						+ " ', 'YYYY/MM/DD HH24:MI:SS'))";
				System.out.println("Insert into taxInvoice for offerer: "
						+ dbConn.executeQuery(query5));
			}

			for (int member = 0; member < joinedMembsList.size(); member++) {
				String checkRowQuery = " SELECT * FROM  TAX_INVOICE WHERE  BookingID ="
						+ bookingIdList.get(i)[0]
						+ " AND MemberID ="
						+ joinedMembsList.get(member)[0]
						+ "AND  ChargeID = 'PER-CHA'";
				ArrayList<String[]> aTestList = dbConn
						.executeSelectQueryEnhanced(checkRowQuery);
				if (aTestList.isEmpty()) {
					String query3 = " INSERT INTO  TAX_INVOICE(TaxInvoiceID,BookingID,MemberID,ChargeID,Date_Time )VALUES(seq_TaxInvoiceID.nextval,"
							+ bookingIdList.get(i)[0]
							+ ","
							+ joinedMembsList.get(member)[0]
							+ " ,'PER-CHA', To_Date('"
							+ currentDate
							+ " ', 'YYYY/MM/DD HH24:MI:SS'))";
					System.out.println("query3 : " + query3);
					System.out
							.println("Insert into taxInvoice for passengers: "
									+ dbConn.executeQuery(query3));
				}
			}
		}

		// for(String ids: bookingIdSet){
		// System.out.println(ids);
		// }

		JLabel lblMyCharges = new JLabel(" My Charges");
		lblMyCharges.setFont(new Font("Algerian", Font.PLAIN, 36));
		add(lblMyCharges, "cell 0 2");

		JLabel compName = new JLabel(" Car Pool Reservations");
		add(compName, "cell 0 4");
		
				JLabel invoiceNo = new JLabel(" Invoice ");
				add(invoiceNo, "cell 3 4,alignx right");

		JLabel compRoad = new JLabel(" 121 Beach Road");
		add(compRoad, "cell 0 5");

		JLabel invoiceNoLabel = new JLabel("");
		add(invoiceNoLabel, "cell 8 5");

		JLabel compArea = new JLabel(" Colombo Western 00600 ");
		add(compArea, "cell 0 6");

		JButton btnViewMyCharges = new JButton("My charges");
		btnViewMyCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT TaxInvoiceID,INVOICE_LINE_ITEM.ChargeID, Description, Amount FROM  INVOICE_LINE_ITEM JOIN TAX_INVOICE ON INVOICE_LINE_ITEM.ChargeID = TAX_INVOICE.ChargeID WHERE MemberID = "
						+ memberID;
				table.setModel(DbUtils.resultSetToTableModel(dbConn
						.executeSelectQuery(sql)));
				ArrayList<String[]> amountList = dbConn
						.executeSelectQueryEnhanced("  SELECT Amount FROM  INVOICE_LINE_ITEM JOIN TAX_INVOICE ON INVOICE_LINE_ITEM.ChargeID = TAX_INVOICE.ChargeID WHERE MemberID = "
								+ memberID);
				double chargeTotal = 0;
				for (int i = 0; i < amountList.size(); i++) {
					double charge = Double.parseDouble(amountList.get(i)[0]);
					chargeTotal = chargeTotal + charge;
				}
				lblAmountResult.setText("" + chargeTotal);

			}
		});
		add(btnViewMyCharges, "cell 3 9,alignx right");
		
				JLabel lblTotalAmount = new JLabel("Total Amount : ");
				add(lblTotalAmount, "flowx,cell 3 11,alignx right");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 60));
		// scrollPane.setViewportBorder(new TitledBorder(null, "Charge",
		// TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(scrollPane, "cell 0 10 4 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
				
						lblAmountResult.setFont(new Font("Tahoma", Font.BOLD, 14));
						add(lblAmountResult, "cell 3 11,alignx right");

	}

}
