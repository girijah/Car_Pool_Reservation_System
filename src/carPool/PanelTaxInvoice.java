package carPool;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

   public class PanelTaxInvoice extends JPanel {
	
	    private JTextField textField;
	    private JTable table;
	
	public PanelTaxInvoice() {
		construct();
	}
	
	public void construct(){
		
		setLayout(new MigLayout("", "[grow][grow][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblMemberEntryVitae = new JLabel(" TAX INVOICE ");
		lblMemberEntryVitae.setFont(new Font("Andalus", Font.BOLD, 32));
		add(lblMemberEntryVitae, "cell 0 0");
		
		JLabel compName = new JLabel(" Car Pool Reservations");
		add(compName,"cell 0 1");
		
		JLabel compRoad = new JLabel(" 121 Beach Road");
		add(compRoad,"cell 0 2");
		
		JLabel date = new JLabel(" Invoice Date");
		add(date,"cell 7 1");
		
		JLabel invoiceNo = new JLabel(" Invoice No");
		add(invoiceNo,"cell 7 2");
		
		JLabel invoiceNoLabel = new JLabel("");
		add(invoiceNoLabel,"cell 8 2");
		
		JLabel dateResult = new JLabel(" *");
		add(dateResult,"cell 8 1");
		
		JLabel compArea = new JLabel(" Colombo Western 00600 ");
		add(compArea,"cell 0 3");
			
			JLabel memberlbl = new JLabel(" Member: ");
			add(memberlbl,"cell 0 5");
			
			JLabel memberIDlbl = new JLabel(" Member num.");
			add(memberIDlbl,"cell 0 6,alignx left");
			
			JComboBox comboBox = new JComboBox();
			add(comboBox, "cell 1 6,growx");
		
		JLabel lblDescription = new JLabel(" Description");
		add(lblDescription, "cell 0 8,alignx left");
		
		JComboBox comboBox_1 = new JComboBox();
		add(comboBox_1, "cell 1 8 6 1,growx");
		
		JButton btnAdd = new JButton("Add");
		add(btnAdd, "cell 12 8");
		
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(table, "cell 0 11 17 6,grow");
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				" No.", " Item Code", " Description", " Amount"
			}
		));
		
		JLabel lblNewLabel_10 = new JLabel(" Amount Due: ");		
		add(lblNewLabel_10,"cell 12 17");
		
		JLabel lblNewLabel_11 = new JLabel("");		
		add(lblNewLabel_11, "cell 6 19");
		
		JLabel lblNewLabel_12 = new JLabel(" Products/ Services");		
		add(lblNewLabel_12, "cell 12 19");
	}

}
