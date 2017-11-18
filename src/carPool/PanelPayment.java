package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class PanelPayment extends JPanel {
	MainFrame parent = null;
	DataConnection dbConn = null;
	private int adminID;
	private String firstName;
	private String lastName;
	private JTextField cashReceivedTxt;
	private JTextField cashPaidTxt;
	private JTextField cardPaidTxt;
	private JTextField creditCardNoTxt;

	/**
	 * Create the panel.
	 */
	public PanelPayment(MainFrame frame, DataConnection connection,
			int id, String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.adminID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		
		setLayout(new MigLayout("", "[][][grow][265.00,grow][grow]", "[][][][][][][][][][][][][][][]"));
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Algerian", Font.PLAIN, 36));
		add(lblPayment, "cell 1 0");
		
		JLabel lblAdminID = new JLabel("");
		add(lblAdminID, "cell 4 0");
		lblAdminID.setText(" "+adminID);
		
		JLabel lblDate = new JLabel("date");
		add(lblDate, "cell 4 1");
		Date date = new Date();
		String strDate = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss").format(date);
		lblDate.setText("Date :"+strDate);
		
		JLabel lblMemberid = new JLabel(" MemberID : ");
		add(lblMemberid, "cell 1 2,alignx trailing");
		
		JComboBox comboMemberID = new JComboBox();
		add(comboMemberID, "cell 2 2,growx");
		String sql = " SELECT MemberID FROM MEMBER ";
		ArrayList<String[]> list = dbConn.executeSelectQueryEnhanced(sql);
		for(int i=0; i<list.size(); i++){
			comboMemberID.addItem(list.get(i)[0]);	
		}

		
		JLabel lblTaxInvoiceid = new JLabel(" Tax InvoiceID :");
		add(lblTaxInvoiceid, "cell 1 3,alignx trailing");
		
		JComboBox comboTaxInvoiceIDs = new JComboBox();
		add(comboTaxInvoiceIDs, "cell 2 3,growx");
		//String sql1 = "SELECT  TaxInvoiceID FROM TAX_INVOICE  WHERE  MemberID = "+comboMemberID.getSelectedItem();
		//ArrayList<String[]> listTaxIDs = dbConn.executeSelectQueryEnhanced(sql1);
		//for(int i=0; i<listTaxIDs.size(); i++){
		//	comboMemberID.addItem(listTaxIDs.get(i));	
		//}
		
		JLabel lblAmount = new JLabel(" Amount : ");
		add(lblAmount, "cell 1 4,alignx right");
		
		
		JLabel lblAmountResult = new JLabel("amount");
		add(lblAmountResult, "cell 2 4");
		//String sql2 = "SELECT Amount FROM INVOICE_LINE_ITEM WHERE ChargeID = (SELECT ChargeID FROM INVOICE_LINE_ITEM WHERE ChargeID = (SELECT ChargeID FROM TAX_INVOICE WHERE TaxInvoiceID = "+comboTaxInvoiceIDs.getSelectedItem()+"))";
		//double amount = Double.parseDouble(dbConn.executeSelectQueryEnhanced(sql2).get(0)[0]);  
		//lblAmountResult.setText("Rs. "+amount);
		
		JLabel lblCash = new JLabel(" Cash");
		add(lblCash, "cell 1 6");
		
		JLabel lblCashReceived = new JLabel(" Cash Received");
		add(lblCashReceived, "cell 1 7,alignx left");
		
		cashReceivedTxt = new JTextField();
		add(cashReceivedTxt, "cell 2 7,growx");
		cashReceivedTxt.setColumns(10);
		
		JLabel lblCashPayment = new JLabel(" Cash Payment");
		add(lblCashPayment, "cell 1 8,alignx left");
		
		cashPaidTxt = new JTextField();
		add(cashPaidTxt, "cell 2 8,growx");
		cashPaidTxt.setColumns(10);
		
		JLabel lblCashBalance = new JLabel(" Cash Balance");
		add(lblCashBalance, "cell 1 9");
		
		JLabel lblBalance = new JLabel("balance");
		add(lblBalance, "cell 2 9");
		
		
		JLabel lblCreditCard = new JLabel(" Credit card");
		add(lblCreditCard, "cell 1 11,alignx left");
		
		JLabel lblNewLabel = new JLabel(" Credit Card Payment");
		add(lblNewLabel, "cell 1 12,alignx left");
		
		
		
		cardPaidTxt = new JTextField();
		add(cardPaidTxt, "cell 2 12,growx");
		cardPaidTxt.setColumns(10);
		if(cashPaidTxt.getText().length()>0){
			
		}
		double cashlyPaidAmt = Double.parseDouble(cashPaidTxt.getText());
		double cardPaidAmt =  Double.parseDouble(cardPaidTxt.getText());
		//double toBepaid = amount - (cashlyPaidAmt+cardPaidAmt);
		double cashBalance = Double.parseDouble(cashReceivedTxt.getText())-cashlyPaidAmt;
		
		//if(toBepaid>0){
		//	JLabel lblYourBill = new JLabel("");
		//	lblYourBill.setForeground(new Color(139, 0, 0));
		//	lblYourBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		//	add(lblYourBill, "cell 3 14");
		//	lblYourBill.setText("Your bill will be fully paid if you pay Rs. "+ toBepaid+" next time!");
		//}
		
		JLabel lblCreditCardNo = new JLabel(" Credit Card No");
		add(lblCreditCardNo, "cell 1 13,alignx left");
		
		creditCardNoTxt = new JTextField();
		add(creditCardNoTxt, "cell 2 13,growx");
		creditCardNoTxt.setColumns(10);
		
		

	}

}
