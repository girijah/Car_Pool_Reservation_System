package carPool;

import javax.swing.JPanel;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JToolBar;

public class PanelOfferTransport extends JPanel {

	private JRadioButton radioOneTime;
	private JRadioButton radioRecurring;
	private JTextField textField_2;
	private JTextField timeFromtxt;
	private JTextField timeTotxt;
	private JTextField travelFromtxt;
	private JTextField travelTotxt;	
	private String firstName;
	private String secondName;
	private int memberID;
	DataConnection dbConn = null;
	MainFrame parent = null;

	JComboBox availableSeatcomboBox = new JComboBox();	
	CheckboxGroup chckBoxGroup = new CheckboxGroup();
	JLabel errorMessage = new JLabel("");
	JTextArea route = new JTextArea();

	public PanelOfferTransport(MainFrame frame, DataConnection connection,
			int id, String firstName, String secondName) {

		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.secondName = secondName;

		setLayout(new MigLayout("", "[136.00][287.00][183.00,grow,left][192.00,grow,left][207.00][][-41.00][][][][-94.00][-5.00][]", "[][][][][][][][][][][][][][][][][][][][][]"));

		route.setPreferredSize(new Dimension(1000, 90));

		JLabel lblName = new JLabel(" Name : ");
		add(lblName, "flowx,cell 3 0");

		JLabel lblOfferTransport = new JLabel(" Offer Transport");
		lblOfferTransport.setFont(new Font("Algerian", Font.BOLD, 36));
		add(lblOfferTransport, "flowx,cell 1 1,alignx left");

		
		JLabel label = new JLabel("");
		add(label, "flowx,cell 2 1 2 1");

		String sql = " SELECT CarID FROM CAR WHERE  MemberID =" + memberID;
		ArrayList<String[]> list = dbConn.executeSelectQueryEnhanced(sql);
		
		if(list.isEmpty()){
			errorMessage.setText("Add cars to offer rides!");	
		}
		
		
		
		JComboBox comboBoxTransportType = new JComboBox();
		add(comboBoxTransportType, "cell 2 4,growx");
		comboBoxTransportType.addItem("One -time trip");
		comboBoxTransportType.addItem("Recurring trip");
		
		JComboBox carIDCombo = new JComboBox();		
		add(carIDCombo, "cell 2 7,growx");

		for (int i = 0; i < list.size(); i++) {
			carIDCombo.addItem(list.get(i)[0]);
		}

		JButton btnReset = new JButton("Reset");
		btnReset.setPreferredSize(new Dimension(120, 25));

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				travelFromtxt.setText("");
				travelTotxt.setText("");
				route.setText("");
				timeFromtxt.setText("");
				timeTotxt.setText("");
			}
		});

		JButton btnBook = new JButton("Book");
		btnBook.setPreferredSize(new Dimension(120, 25));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (travelFromtxt.getText().equals("")
						|| travelTotxt.getText().equals("")
						|| route.getText().equals("")
						|| timeFromtxt.getText().equals("")
						|| timeTotxt.getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							"No fields should be left blank!");
				} else {

					Date date = new Date();
					String bookedDate = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss").format(date);
					System.out.println("Date :"+bookedDate);					
					

					String query = "INSERT INTO ROUTE_TIME(RouteTimeID, MemberID, BookedDate,TripType, TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,  AvailableSeats) VALUES (seq_RTimeID.nextval,"
							+ memberID
							+ ", to_date('"
							+ bookedDate
							+ "','YYYY/MM/DD HH24:MI:SS'),'"
							+ comboBoxTransportType.getSelectedItem()
							+ " ', '"
							+ travelFromtxt.getText()
							+ "','"
							+ travelTotxt.getText()
							+ "', To_Date('"
							+ timeFromtxt.getText()
							+ "', 'YYYY/MM/DD HH24:MI:SS'), To_Date('"
							+ timeTotxt.getText()
							+ "', 'YYYY/MM/DD HH24:MI:SS'),'"
							+ route.getText()
							+ "', "
							+ availableSeatcomboBox.getSelectedItem() + ")";

					boolean bool = dbConn.executeQuery(query);
					if (bool == true) {
						JOptionPane.showMessageDialog(null,
								"Information Recording In.....");
						System.out.println(" Booked date : " + bookedDate);

						String sql = "SELECT RouteTimeID FROM  ROUTE_TIME  WHERE  MemberID = "
								+ memberID
								+ " AND BookedDate = To_Date('"
								+ bookedDate + "', 'YYYY/MM/DD HH24:MI:SS')";

						String routeTimeID = dbConn.executeSelectQueryEnhanced(
								sql).get(0)[0];
						System.out.println(routeTimeID);

						String command = "  INSERT INTO BOOKING(BookingID,MemberID,CarID,RouteTimeID)VALUES(seq_BookingID.nextval,"
								+ memberID
								+ ", '"
								+ carIDCombo.getSelectedItem().toString()
								+ "',"
								+ routeTimeID + " )";
						dbConn.executeQuery(command);

						String sqlQuery = "SELECT  BookingID FROM BOOKING WHERE  MemberID = "
								+ memberID
								+ " AND  CarID = '"
								+ carIDCombo.getSelectedItem()
								+ "' AND  RouteTimeID = " + routeTimeID;
						String bookId = dbConn.executeSelectQueryEnhanced(sqlQuery)
								.get(0)[0];
						System.out.println("bookId : "+bookId);
						String commQuery = "   INSERT INTO MEMBERBOOKING(MemberID,BookingID,isVehicleProvider )VALUES("
								+ memberID + "," + bookId + ", 'Yes')";

						if (dbConn.executeQuery(command)
								&& dbConn.executeQuery(commQuery)) {
							JOptionPane
									.showMessageDialog(null,
											"Booking has been made successfully! Await friends call you soon!");
						}

					} else {
						JOptionPane
								.showMessageDialog(null, "Click Book again!");
					}

				}
			}
		});

		add(btnBook, "flowx,cell 1 2,alignx left,growy");
		add(btnReset, "cell 1 2,alignx right");

		JLabel lblTransportStatus = new JLabel(" Transport Type");
		add(lblTransportStatus, "cell 1 4");

		

		JLabel lblNewLabel_1 = new JLabel(
				" Car ID [ Your Car number plate no expected ]");
		add(lblNewLabel_1, "cell 1 7");
		
		
		errorMessage.setForeground(new Color(219, 112, 147));
		add(errorMessage, "cell 3 7");

		JLabel travelFromlbl = new JLabel(" Travelling from ");
		add(travelFromlbl, "cell 1 8");

		travelFromtxt = new JTextField();
		add(travelFromtxt, "cell 2 8,growx");
		travelFromtxt.setColumns(10);

		JLabel travelTolbl = new JLabel(" Travelling To ");
		add(travelTolbl, "cell 1 9");

		travelTotxt = new JTextField();
		add(travelTotxt, "cell 2 9,growx");
		travelTotxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, "flowx,cell 1 10,alignx left");

		timeFromtxt = new JTextField();
		add(timeFromtxt, "cell 2 10,growx");
		timeFromtxt.setColumns(10);

		JLabel lblThisIsKey = new JLabel("");
		add(lblThisIsKey, "flowx,cell 1 11");
		for (int seats = 0; seats < 20; seats++) {
			availableSeatcomboBox.addItem(seats);
		}

		timeTotxt = new JTextField();
		add(timeTotxt, "cell 2 11,growx");
		timeTotxt.setColumns(10);

		this.setAutoscrolls(true);

		JLabel lblNewLabel_6 = new JLabel(
				" Route  [ all the stopovers or places you pass ]");
		add(lblNewLabel_6, "cell 1 13");

		add(route, "cell 2 13 1 3,grow");
				
				JLabel lblPleaseSeperateEach = new JLabel(" Please seperate each cities/ places by comma , ");
				lblPleaseSeperateEach.setForeground(new Color(178, 34, 34));
				add(lblPleaseSeperateEach, "cell 1 14");
		
				JLabel lbladdMoreCities = new JLabel("+add more cities on your route");
				lbladdMoreCities.setForeground(new Color(30, 144, 255));
				add(lbladdMoreCities, "cell 1 15");

		JLabel lblAvailableSeats = new JLabel(" Available seats");
		add(lblAvailableSeats, "cell 1 17,alignx left");

		add(availableSeatcomboBox, "cell 2 17,growx");

		JLabel lblNewLabel_3 = new JLabel(
				"Time to :   'YYYY/MM/DD  HH24:MI:SS'");
		add(lblNewLabel_3, "cell 1 11,alignx left");

		JLabel lblNewLabel_2 = new JLabel(
				"Time from :    'YYYY/MM/DD  HH24:MI:SS' ");
		add(lblNewLabel_2, "cell 1 10,alignx left");

		JLabel loggedIDLabel = new JLabel("");
		add(loggedIDLabel, "cell 3 1");
		loggedIDLabel.setText(" MemberID : ");

		JLabel memberIn = new JLabel();
		memberIn.setText(" # " + memberID);
		add(memberIn, "cell 3 1");

		JLabel lblFirstName = new JLabel("");
		add(lblFirstName, "cell 3 0");
		lblFirstName.setText("" + firstName);

		JLabel lblLastName = new JLabel("");
		add(lblLastName, "cell 3 0");
		lblLastName.setText("" + secondName);
	}

}
