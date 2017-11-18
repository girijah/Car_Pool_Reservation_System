package carPool;

import javax.swing.JPanel;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CheckboxGroup;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Panel_AddCar extends JPanel {
	private JFrame frame;
	private JTextField idtextField;
	private JTextField brandtextField;
	private JTextField engineSizetextField;
	private JLabel errorIndicationlabel;
//	private JCheckBox chckbxAvailable;
//	private JCheckBox chckbxUnavailable;
	private JTextField manufactDateTextField;	
	private int memberID;
	private String firstName;
	private String lastName;
	private String ac;
	MainFrame parent = null;
	DataConnection dbConn = null;
	
	CheckboxGroup chkBoxGroup = new CheckboxGroup();
	Checkbox checkbox_1 = new Checkbox("Available", chkBoxGroup, true);
	Checkbox checkbox = new Checkbox("Unavailable", chkBoxGroup, true);
	JLabel idResultLabel = new JLabel("#");
	
	JComboBox colourComboBox = new JComboBox();
	JComboBox SeatComboBox = new JComboBox();
	
	public Panel_AddCar(MainFrame frame, DataConnection connection, int id,
			String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		idResultLabel.setText("# " + memberID);
		construct();
	}

	

	public void construct() {

		setLayout(new MigLayout("",
				"[90.00][198.00][178.00,grow][155.00][140.00][][][][]",
				"[][][][][][][][][][][][][][][][]"));

		JLabel lblName = new JLabel(" Name : ");
		add(lblName, "flowx,cell 4 0");

		JLabel lblMemberID = new JLabel(" Member ID : \r\n");
		add(lblMemberID, "flowx,cell 4 1");
		
		add(checkbox_1, "cell 2 9");							
		add(checkbox, "cell 2 10");
		
				JLabel lblAddCar = new JLabel(" Add Car \r\n");
				lblAddCar.setFont(new Font("Algerian", Font.BOLD, 36));
				add(lblAddCar, "cell 0 2");
		
				JButton btnSubmit = new JButton("REGISTER MY CAR");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (idtextField.getText().equals("")
								|| brandtextField.getText().equals("")
								|| engineSizetextField.getText().equals("")
								|| manufactDateTextField.getText().equals("")) {
							errorIndicationlabel
									.setText(" *Note : Should not be left any empty field!*");

						} else {
							errorIndicationlabel.setText("");
							
							
							

							if (chkBoxGroup.getSelectedCheckbox().getName().equals("checkbox_1")) {
								ac = "Available";
							} else if (chkBoxGroup.getSelectedCheckbox().getName().equals("checkbox")) {
								ac = "Unavailable";
							}

							String sql = "INSERT INTO CAR(MemberID, CarID, Brand, CapacitySeat, Colour, EngineSize, AC, ManufacturedDate) VALUES("
									+ memberID
									+ " ,'"
									+ idtextField.getText()
									+ "','"
									+ brandtextField.getText()
									+ "',"
									+ SeatComboBox.getSelectedItem()
									+ ", '"
									+ colourComboBox.getSelectedItem()
									+ "', '"
									+ engineSizetextField.getText()
									+ "', '"
									+ ac
									+ "', To_Date('"
									+ manufactDateTextField.getText()
									+ "', 'YYYY/MM/DD'))";
							if (dbConn.executeQuery(sql)) {
								JOptionPane
										.showMessageDialog(
												null,
												"Your car has been successfully registered! Thank you! You can enjoy offering rides now!");
							} else {
								JOptionPane.showMessageDialog(null,
										"Please Click the Register button again!");
							}
						}
					}

				});
				btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 11));
				add(btnSubmit, "flowx,cell 0 3");

		JLabel lblCarId = new JLabel(" Car ID ");
		add(lblCarId, "flowx,cell 1 5");

		idtextField = new JTextField();
		add(idtextField, "cell 2 5,growx");

		for (int seatCount = 0; seatCount < 11; seatCount++) {
			SeatComboBox.addItem(seatCount);
		}

		JLabel lblCapacityseat = new JLabel(" Capacity/Seat");
		add(lblCapacityseat, "cell 3 5");

		
		add(SeatComboBox, "cell 4 5,growx");

		JLabel lblBrand = new JLabel(" Brand ");
		add(lblBrand, "cell 1 6");

		brandtextField = new JTextField();
		add(brandtextField, "cell 2 6,growx");

		JLabel lblColour = new JLabel(" Colour ");
		add(lblColour, "cell 3 6,alignx left");

		
		add(colourComboBox, "cell 4 6,growx");
		colourComboBox.addItem("Red");
		colourComboBox.addItem("Gold");
		colourComboBox.addItem("Silver");
		colourComboBox.addItem("Black");
		colourComboBox.addItem("Indigo");
		colourComboBox.addItem("Violet");
		colourComboBox.addItem("Maroon");
		colourComboBox.addItem("Blue");
		colourComboBox.addItem("Dark Blue");
		colourComboBox.addItem("Light Blue");
		colourComboBox.addItem("Green");
		colourComboBox.addItem("Pink");
		colourComboBox.addItem("Dark Purple");
		colourComboBox.addItem("Purple");
		colourComboBox.addItem("Yellow");
		colourComboBox.addItem("Orange");
		colourComboBox.addItem("Brown");
		colourComboBox.addItem("Other");

		JLabel lblManufacturedDate = new JLabel(
				" Manufactured Date   [YYYY/MM/DD]");
		add(lblManufacturedDate, "cell 1 7");

		manufactDateTextField = new JTextField();
		add(manufactDateTextField, "cell 2 7,growx");

		JLabel lblEngineSize = new JLabel(" Engine Size");
		add(lblEngineSize, "cell 3 7");

		engineSizetextField = new JTextField();
		add(engineSizetextField, "cell 4 7,growx");

		JLabel lblAirCondition = new JLabel(" Air Condition ");
		add(lblAirCondition, "cell 1 9");

		// chckbxAvailable = new JCheckBox("AVAILABLE");
		// add(chckbxAvailable, "cell 2 9");
		//
		// chckbxUnavailable = new JCheckBox("UNAVAILABLE");
		// add(chckbxUnavailable, "cell 2 10");

		

		errorIndicationlabel = new JLabel("");
		errorIndicationlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		errorIndicationlabel.setForeground(new Color(204, 51, 102));
		add(errorIndicationlabel, "cell 2 15");

		add(idResultLabel, "cell 4 1");

		JLabel lblFirstName = new JLabel("");
		add(lblFirstName, "cell 4 0");
		lblFirstName.setText(firstName);

		JLabel lblLastName = new JLabel("");
		add(lblLastName, "cell 4 0");
		lblLastName.setText(lastName);

		JLabel lblcarNumberPlate = new JLabel(" [car number plate no]");
		add(lblcarNumberPlate, "cell 1 5");
		
				JButton btnReset = new JButton(" RESET");
				btnReset.setFont(new Font("Tahoma", Font.PLAIN, 11));
				add(btnReset, "cell 0 3,grow");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idtextField.setText("");
				brandtextField.setText("");
				engineSizetextField.setText("");
				errorIndicationlabel.setText("");
				manufactDateTextField.setText("");
			}
		});

	}
	
	
}
