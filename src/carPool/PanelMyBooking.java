package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PanelMyBooking extends JPanel {

	private JTable table;
	private String firstName;
	private String lastName;
	private int memberID;
	DataConnection dbConn = null;
	MainFrame parent = null;
	private JTable tableMembersDetail;

	/**
	 * Create the panel.
	 */
	public PanelMyBooking(MainFrame frame, DataConnection connection, int id,
			String firstName, String lastName) {

		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;

		setLayout(new MigLayout("", "[][743.00,grow][][][]",
				"[][][][grow][][grow][][][]"));

		JLabel lblBooking = new JLabel(" Booking");
		lblBooking.setFont(new Font("Algerian", Font.BOLD, 36));
		add(lblBooking, "cell 0 0");

		JLabel lblFirstName = new JLabel("");
		add(lblFirstName, "flowx,cell 1 0,alignx right");
		lblFirstName.setText("Name : " + this.firstName);

		JLabel lblMemberIdResult = new JLabel("");
		add(lblMemberIdResult, "cell 1 1,alignx right");
		lblMemberIdResult.setText("MemberID : #" + memberID);

		JButton btnBookingIOffer = new JButton("View My Booking");
		btnBookingIOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql1 = " SELECT BookingID FROM MEMBERBOOKING WHERE isVehicleProvider = 'Yes' AND MemberID = "
						+ memberID;
				ArrayList<String[]> list = dbConn
						.executeSelectQueryEnhanced(sql1);
				System.out.println("list size : " + list.size());

				if (list.isEmpty()) {
					JOptionPane
							.showMessageDialog(null,
									"There is no booking to show! Make a booking by sharing your car!");
				}

				String sql2 = "SELECT mb.BookingID, BookedDate, TravellingFrom, TravellingTo, TimeFrom, TimeTo, Route,CarID FROM MEMBERBOOKING mb, BOOKING b, ROUTE_TIME r WHERE mb.bookingid=b.bookingid AND b.ROUTETIMEID=r.ROUTETIMEID AND mb.isVehicleProvider = 'Yes' AND mb.memberid= "
						+ memberID;
				ResultSet resultSet = dbConn.executeSelectQuery(sql2);
				table.setModel(DbUtils.resultSetToTableModel(resultSet));

				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						if (e.getClickCount() == 1) {

							String bookingId = "";
							bookingId = table.getValueAt(
									table.getSelectedRow(), 0).toString();
							System.out.println("bookingId : " + bookingId);
							String sql3 = " SELECT  Title,FirstName, LastName, TelephoneNo, MobileNo1,email_Address  FROM  MEMBER INNER JOIN MEMBERBOOKING ON MEMBER.MemberID = MEMBERBOOKING.MemberID  WHERE BookingID ="
									+ bookingId;
							ResultSet set = dbConn.executeSelectQuery(sql3);
							tableMembersDetail.setModel(DbUtils
									.resultSetToTableModel(set));
						}
					}
				});

			}
		});
		add(btnBookingIOffer, "flowx,cell 0 2,alignx center");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 5 2 4,grow");

		tableMembersDetail = new JTable();
		scrollPane_1.setViewportView(tableMembersDetail);
		scrollPane_1.setPreferredSize(new Dimension(1100, 100));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 2 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(1100, 100));

		JLabel lblNewLabel = new JLabel(" People On My Ride : ");
		add(lblNewLabel, "cell 0 4 2 1");

		JLabel lblLastName = new JLabel("");
		add(lblLastName, "cell 1 0,alignx right");
		lblLastName.setText("" + this.lastName);

	}

}
