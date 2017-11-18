package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PanelSeekTransport extends JPanel implements ActionListener {

	MainFrame parent = null;
	DataConnection dbConn = null;
	private int memberID;
	private String firstName;
	private String lastName;
	private JTextField travelFromTextField;
	private JTextField travelToTextField;
	// DefaultTableModel model = new DefaultTableModel();
	private JTable table_1;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemAdd;
	private JMenuItem menuItemRemove;
	JLabel idResult = new JLabel("");

	JTextArea commentArea = new JTextArea();
	private JTable tableBooking;
	private JTable table;
	private JTable table_2;

	public PanelSeekTransport(MainFrame frame, DataConnection connection,
			int id, String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		setLayout(new MigLayout("", "[838.00,grow][237.00,grow]",
				"[][][][][][grow][grow][grow][][][][][]"));

		JLabel lblName = new JLabel(" Name : ");
		add(lblName, "flowx,cell 1 0");

		JLabel lblSeekTransport = new JLabel("Seek Transport");
		lblSeekTransport.setFont(new Font("Algerian", Font.BOLD, 32));
		add(lblSeekTransport, "cell 0 1");

		Date date = new Date();
		String strDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(date);

		JButton btnViewAllBookings = new JButton("View All Bookings");
		btnViewAllBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String query = " SELECT MemberID, RouteTimeID, BookedDate,TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,AvailableSeats FROM ROUTE_TIME WHERE RouteTimeID IN (SELECT RouteTimeID FROM BOOKING  WHERE BookingID IN (SELECT BOOKING.bookingid FROM  BOOKING INNER JOIN ROUTE_TIME  ON ROUTE_TIME.RouteTimeID = BOOKING.RouteTimeID WHERE TimeFrom > To_Date('"
						+ strDate + "','YYYY/MM/DD HH24:MI:SS')))";
				ResultSet set = dbConn.executeSelectQuery(query);
				table_1.setModel(DbUtils.resultSetToTableModel(set));
			}
		});

		// tableBooking = new JTable();
		// add(tableBooking, "cell 0 1,grow");
		// JScrollPane scroll = new JScrollPane(tableBooking);

		JLabel lblYourId = new JLabel(" Your ID: ");
		add(lblYourId, "flowx,cell 1 1");

		JLabel lblTravelFrom = new JLabel(" Travel From  ");
		add(lblTravelFrom, "flowx,cell 0 2,alignx left");

		JLabel lblTravellTo = new JLabel(" Travell To     ");
		add(lblTravellTo, "flowx,cell 0 3,alignx left");

		JButton btnSearchByPlaces = new JButton("Search by Places");
		btnSearchByPlaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (travelFromTextField.getText().equals("")
						|| travelToTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Enter TravelFrom and TravelTo fields");
				} else {
					// String query =
					// " SELECT MemberID, RouteTimeID, BookedDate,TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route, AvailableSeats FROM ROUTE_TIME WHERE Route IN (SELECT Route FROM ROUTE_TIME WHERE RouteTimeID IN (SELECT RouteTimeID FROM BOOKING  WHERE BookingID IN (SELECT BOOKING.bookingid FROM  BOOKING INNER JOIN ROUTE_TIME  ON ROUTE_TIME.RouteTimeID = BOOKING.RouteTimeID WHERE TimeFrom > To_Date('"+strDate+"','YYYY/MM/DD HH24:MI:SS')))AND (Route LIKE ('%"+travelFromTextField.getText()+"%')OR Route LIKE ('%"+travelToTextField.getText()+"%')))";
					String quer = "SELECT MemberID, RouteTimeID, BookedDate,TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,AvailableSeats FROM ROUTE_TIME WHERE Route IN (SELECT Route FROM ROUTE_TIME WHERE RouteTimeID IN (SELECT RouteTimeID FROM BOOKING  WHERE BookingID IN (SELECT BOOKING.bookingid FROM  BOOKING INNER JOIN ROUTE_TIME  ON ROUTE_TIME.RouteTimeID = BOOKING.RouteTimeID WHERE TimeFrom > To_Date('2015/08/22 11:00:00','YYYY/MM/DD HH24:MI:SS')))AND Route LIKE ('%"
							+ travelFromTextField.getText()
							+ "%')) UNION  SELECT MemberID, RouteTimeID, BookedDate,TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,AvailableSeats FROM ROUTE_TIME WHERE Route IN (SELECT Route FROM ROUTE_TIME WHERE RouteTimeID IN (SELECT RouteTimeID FROM BOOKING  WHERE BookingID IN (SELECT BOOKING.bookingid FROM  BOOKING INNER JOIN ROUTE_TIME  ON ROUTE_TIME.RouteTimeID = BOOKING.RouteTimeID WHERE TimeFrom > To_Date('2015/08/22 11:00:00','YYYY/MM/DD HH24:MI:SS')))AND Route LIKE ('%"
							+ travelToTextField.getText() + "%'))";

					ResultSet dataRow = dbConn.executeSelectQuery(quer);
					table_1.setModel(DbUtils.resultSetToTableModel(dataRow));

					// ArrayList<String[]> list = dbConn
					// .executeSelectQueryEnhanced(query);
					// ArrayList<String> routes = new ArrayList<String>();
					// for (String[] array : list) {
					// System.out.println("row : " + array[0]);
					// String[] places = array[0].split(",");
					// for (int i = 0; i < places.length; i++) {
					// if
					// (places[i].equalsIgnoreCase(travelFromTextField.getText())||
					// places[i].equalsIgnoreCase(travelToTextField.getText()))
					// {
					// //routes.add(array[0]);
					// ResultSet dataRow = dbConn
					// .executeSelectQuery(""
					// + array[0] + "' ");
					// table_1.setModel(DbUtils
					// .resultSetToTableModel(dataRow));
					// }
					// }
					// }
					// ArrayList<String[]> listing =new ArrayList<String[]>();
					// for(int i = 0; i<routes.size(); i++){
					//
					// ArrayList<String[]> arrList =
					// dbConn.executeSelectQueryEnhanced("SELECT MemberID, RouteTimeID, BookedDate,TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,AvailableSeats FROM ROUTE_TIME WHERE Route = '"+routes.get(i)+"'");
					// listing.addAll(arrList);
					// }

					// for(int y=0; y<listing.size();y++){
					// model.insertRow(model.getRowCount(), new Object[]
					// {listing.get(y)});
					// System.out.println(listing.get(y).toString());
					// }

				}
			}
		});
		add(btnSearchByPlaces, "cell 0 4");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 6 2 1,growx,aligny top");
		scrollPane.setPreferredSize(new Dimension(800, 100));

		table = new JTable();
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 7 2 1,grow");

		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		scrollPane_1.setPreferredSize(new Dimension(800, 100));
		add(btnViewAllBookings, "flowx,cell 0 8");

		table_1 = new JTable() {

			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};

		/*
		 * JScrollPane scrollPane = new JScrollPane(table_1,
		 * JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 * 
		 * table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); add(scrollPane,
		 * "cell 0 6 2 1,growx,aligny top");
		 */

		table_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String offererID = null;

				if (e.getClickCount() == 1) {

					// JTable target = (JTable)e.getSource();
					// int row = target.getSelectedRow();
					// int column = target.getSelectedColumn();
					// System.out.println(table.getValueAt(row,column).toString());

					offererID = table_1.getValueAt(table_1.getSelectedRow(), 0)
							.toString();
					System.out.println("Table OffererID: " + offererID);

					String query = "SELECT Description FROM ADDCOMMENTS WHERE CommentToMemberID = "
							+ offererID;
					System.out.println(query);

					ArrayList<String[]> commentsList = dbConn
							.executeSelectQueryEnhanced(query);

					// System.out.println("*" + commentsList);

					StringBuilder strBuilder = new StringBuilder();

					for (String[] array : commentsList) {
						String line = array[0];
						System.out.println("line :" + line);
						strBuilder.append(line + "\n");

					}

					// for (int i = 0; i < commentsList.size(); i++) {
					// String line = commentsList.get(i)[0];
					// System.out.println("line :" + line);
					// strBuilder.append(line);
					// }

					String comments = strBuilder.toString().trim();
					System.out.println("StrBuilder Comment: " + comments);
					commentArea.setText("" + comments);
				}

				String sql1 = "SELECT carID FROM BOOKING WHERE RouteTimeID ="
						+ table_1.getValueAt(table_1.getSelectedRow(), 1)
								.toString();
				String carID = null;
				try {

					ResultSet result = dbConn.executeSelectQuery(sql1);
					if (result != null && result.next()) {
						carID = result.getString(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql2 = " SELECT carID, brand, capacitySeat, colour, engineSize, AC FROM CAR WHERE carID = '"
						+ carID + "'";
				ResultSet set1 = dbConn.executeSelectQuery(sql2);
				table.setModel(DbUtils.resultSetToTableModel(set1));

				String sql3 = "  SELECT Title, FirstName, LastName, Gender, Age, TelephoneNo, MobileNo1, email_Address FROM MEMBER WHERE MemberID = "
						+ offererID;

				ResultSet setResult = dbConn.executeSelectQuery(sql3);
				table_2.setModel(DbUtils.resultSetToTableModel(setResult));
			}
		});

		// constructs the popup menu
		popupMenu = new JPopupMenu();
		menuItemAdd = new JMenuItem("Add Me To This Booking");
		menuItemRemove = new JMenuItem("Remove From This Booking");
		// menuItemRemove = new JMenuItem("Remove Current Row");
		// menuItemRemoveAll = new JMenuItem("Remove All Rows");

		menuItemAdd.addActionListener(this);
		menuItemRemove.addActionListener(this);
		// menuItemRemoveAll.addActionListener(this);

		popupMenu.add(menuItemAdd);
		popupMenu.add(menuItemRemove);
		// popupMenu.add(menuItemRemoveAll);

		// sets the popup menu for the table
		table_1.setComponentPopupMenu(popupMenu);

		table_1.addMouseListener(new TableMouseListener(table_1));

		// adds the table to the frame
		JScrollPane topTblScroll = new JScrollPane(table_1);
		add(topTblScroll, "cell 0 5 2 1,growx");
		topTblScroll.setPreferredSize(new Dimension(800, 100));

		JLabel lblFirstName = new JLabel("");
		add(lblFirstName, "cell 1 0");
		lblFirstName.setText(firstName);

		JLabel lblLastName = new JLabel("");
		add(lblLastName, "cell 1 0");
		lblLastName.setText(lastName);

		idResult.setText(" # " + id);

		add(idResult, "cell 1 1");

		travelFromTextField = new JTextField();
		add(travelFromTextField, "cell 0 2,alignx leading");
		travelFromTextField.setColumns(10);

		travelToTextField = new JTextField();
		add(travelToTextField, "cell 0 3,alignx leading");
		travelToTextField.setColumns(10);

		JLabel lblBlogDescription = new JLabel(" Comments given by members:");
		add(lblBlogDescription, "flowx,cell 0 9");

		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 0 10 1 3,grow");
		scrollPane_2.setViewportView(commentArea);
		commentArea.setPreferredSize(new Dimension(2000, 100));
		scrollPane_2.getVerticalScrollBar().setUnitIncrement(50);

	}

	String bookingID = null;
	String seat = null;
	int confirm = 99;

	private void addNewRow() {

		String offererID = table_1.getValueAt(table_1.getSelectedRow(), 0)
				.toString();
		System.out.println("Table OffererID: " + offererID);
		String routIDOnTable = table_1.getValueAt(table_1.getSelectedRow(), 1)
				.toString();
		System.out.println("routIDOnTable : " + routIDOnTable);
		String seatCount = table_1.getValueAt(table_1.getSelectedRow(), 8)
				.toString();
		int count = Integer.parseInt(seatCount);

		String sql4 = "SELECT BookingID FROM BOOKING WHERE RouteTimeID = "
				+ routIDOnTable;
		ResultSet result = dbConn.executeSelectQuery(sql4);

		try {
			if (result != null && result.next()) {
				bookingID = result.getString(1);
				System.out.println("bookingID : " + bookingID);
				String command = " SELECT Count(*) FROM  MEMBERBOOKING WHERE BookingID ="
						+ bookingID;
				seat = dbConn.executeSelectQueryEnhanced(command).get(0)[0];
			}
		} catch (SQLException e) {
			System.out.println("addNewRow method sql4  resultset exception!");
			e.printStackTrace();
		}

	
		boolean isSpace = false;
		String sql5 = "INSERT INTO MEMBERBOOKING(MemberID,BookingID,isVehicleProvider )VALUES("
				+ memberID + "," + bookingID + "," + " 'No')";
		String sql0 = " SELECT isVehicleProvider FROM  MEMBERBOOKING WHERE BookingID ="
				+ bookingID + " AND MemberID =" + memberID;

		try {
			if (!(dbConn.executeSelectQueryEnhanced(sql0).isEmpty())) {

				JOptionPane.showMessageDialog(null,
						"You Are Going This Ride! You Have Already Booked!",
						"", JOptionPane.OK_OPTION);

			} else if ((dbConn.executeSelectQueryEnhanced(sql0).isEmpty())) {

				if (count > Integer.parseInt(seat)) {
					isSpace = true;
					confirm = JOptionPane.showConfirmDialog(null,
							"Do you want to join in this booking?",
							"Booking Confirmation", JOptionPane.YES_NO_OPTION);
					System.out
							.println("confirm inside addNewRow(): " + confirm);

				} else if (count <= Integer.parseInt(seat)) {
					JOptionPane.showMessageDialog(null, "Seats are filled !");
				}
			}
		} catch (HeadlessException e) {

			e.printStackTrace();
		}

		if (confirm == 0 && isSpace) {
			if (dbConn.executeQuery(sql5)) {
				JOptionPane
						.showMessageDialog(null,
								"You've successfully pooled in this Booking! Have a great ride!");
			} else {
				JOptionPane.showMessageDialog(null, "Click again for Booking!");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menu = (JMenuItem) e.getSource();
		if (menu == menuItemAdd) {
			addNewRow();
		} else if (menu == menuItemRemove) {
			removeBooking();
		}
		// else if (menu == menuItemRemoveAll) {
		// removeAllRows();
		// }
	}

	private void removeBooking() {
		long difference = -1;
		
		String bookinID = null;
		String routIDOnTable = table_1.getValueAt(table_1.getSelectedRow(), 1)
				.toString();
		String sql7 = "SELECT  BookingID FROM BOOKING WHERE RouteTimeID = "
				+ routIDOnTable;

		String timeFrom = table_1.getValueAt(table_1.getSelectedRow(), 5)
				.toString();
		System.out.println("timeFrom : " + timeFrom);

		Date cur_date = new Date();
		System.out.println("cur_date : " + cur_date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date futureDate = dateFormat.parse(timeFrom);
			System.out.println("futureDate : " + futureDate);
			long days = futureDate.getTime() - cur_date.getTime();
			difference = days / (1000 * 60 * 60 * 24);
			System.out.println("difference : " + difference);

		} catch (ParseException e1) {
			System.out.println("Date parse exception encountered!");
			e1.printStackTrace();
		}

		ResultSet result = dbConn.executeSelectQuery(sql7);

		try {
			if (result != null && result.next()) {
				bookinID = result.getString(1);
				System.out.println("Booking ID : " + bookinID);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String sql8 = "SELECT isVehicleProvider FROM MEMBERBOOKING where MemberID = "
				+ memberID + " AND BookingID = " + bookinID;
		String sql6 = "DELETE FROM  MEMBERBOOKING WHERE  MemberID = "
				+ memberID + " AND BookingID =" + bookinID;
		

		try {
			if (dbConn.executeSelectQueryEnhanced(sql8).isEmpty()) {

				JOptionPane
						.showMessageDialog(
								null,
								"Sorry, you've not made this booking yet! No record remain to delete!",
								"", JOptionPane.OK_OPTION);

			} else if (!(dbConn.executeSelectQueryEnhanced(sql8).isEmpty())) {
				confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to remove this booking?",
						"Remove Booking Confirmation",
						JOptionPane.YES_NO_OPTION);
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		
		boolean delPassengers=false;
		boolean delbook=false;
		boolean delRoute=false;
		
		if (confirm == 0 && difference >= 1) {
			
			String yesOrNo = dbConn.executeSelectQueryEnhanced(sql8).get(0)[0];
			if(yesOrNo.equalsIgnoreCase("Yes")){
				delPassengers = dbConn.executeQuery("DELETE FROM  MEMBERBOOKING WHERE BookingID =" + bookinID );
				delbook = dbConn.executeQuery("DELETE FROM  BOOKING WHERE BookingID =" + bookinID );
				delRoute = dbConn.executeQuery("DELETE FROM ROUTE_TIME WHERE RouteTimeID =" + routIDOnTable );
			}
			
			if (dbConn.executeQuery(sql6) && delPassengers && delbook && delRoute) {
				JOptionPane.showMessageDialog(null,
						"Booking is removed!");
			}else if (dbConn.executeQuery(sql6)){
				JOptionPane.showMessageDialog(null,
						"You have been removed from this booking!");
			}

		} else if (difference < 1) {
			JOptionPane
					.showMessageDialog(
							null,
							"This ride is just within a day, you cannot be removed! Please inform car offerer! Charges apply the same!");
		}

	}
}
