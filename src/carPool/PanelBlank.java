package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Font;

public class PanelBlank extends JPanel {
	MainFrame parent = null;
	DataConnection dbConn = null;
	private int memberID;
	private String newTel;
	private String newMobile;
	private String newEmail;
	private String firstName;
	private String lastName;
	
	public PanelBlank(MainFrame frame, DataConnection connection, int id, String firstName, String lastName) {
	
		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;		
		this.lastName = lastName;
		
//		parent.showOfferTransport();
//		parent.showSeekTransport();	
//		parent.showAddCar();	
//		parent.showLogOut();
//		parent.hideSignUp();
//		parent.hideLogIn();		
				
		setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblMyProfile = new JLabel(" My Profile ");
		lblMyProfile.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblMyProfile, "cell 20 0");
		
		JButton btnNewButton = new JButton("Bookings I made");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showChildPanel("MyBooking");
			}
		});
		
				
		JLabel lblMemberid = new JLabel("  MemberID : ");
		add(lblMemberid, "flowx,cell 0 2,alignx left,aligny top");
		add(btnNewButton, "flowx,cell 0 3");
		
		JButton btnJoinedBookings = new JButton("Joined Bookings");
		btnJoinedBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showChildPanel("JoinedBooking");
			}
		});
		add(btnJoinedBookings, "cell 0 3");
		
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/user-group-icon.png")));
		//label_11.setIcon(new ImageIcon(PanelBlank.class.getResource("/carPool/user-group-icon.png")));
		add(label_11, "cell 20 3");
		
		String sql1 = "  SELECT Title, NIC_Number, Gender, Age, TelephoneNo, MobileNo1, email_Address, RegisteredDate FROM  MEMBER WHERE MemberID ="+ memberID;
		ArrayList<String[]> res = dbConn.executeSelectQueryEnhanced(sql1);
		
		JLabel resultTitlelbl = new JLabel("");
		resultTitlelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(resultTitlelbl, "flowx,cell 0 1");	
		resultTitlelbl.setText( res.get(0)[0]);
		
		/*JButton btnMyCharges = new JButton("My Charges");
		btnMyCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.showChildPanel("MyTaxInvoice");	
			}
		});
		add(btnMyCharges, "cell 0 4");*/
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label, "flowx,cell 0 5");
		
		JLabel resultRegDate = new JLabel("");
		add(resultRegDate, "cell 2 5");
		resultRegDate.setText(res.get(0)[7]);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label_1, "flowx,cell 0 7");
		
		JLabel resultNIClbl = new JLabel("");
		add(resultNIClbl, "cell 2 7");
		resultNIClbl.setText(res.get(0)[1]);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label_2, "flowx,cell 0 9");
		
		JLabel resultGenderlbl = new JLabel("");
		add(resultGenderlbl, "cell 2 9");
		resultGenderlbl.setText(res.get(0)[2]);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label_3, "flowx,cell 0 11");
		
		JLabel resultAgelbl = new JLabel("");
		add(resultAgelbl, "cell 2 11");
		resultAgelbl.setText(res.get(0)[3]);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label_4, "flowx,cell 0 13");
		
		JLabel resultTelephone = new JLabel("");
		add(resultTelephone, "cell 2 13");
		resultTelephone.setText(res.get(0)[4]);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTel = JOptionPane.showInputDialog("Please Enter New Telephone No?");
				if(newTel.equals("")){
					JOptionPane.showMessageDialog(null, "This should not be left blank!");
				}else{
				modifyTelephoneNo(newTel);
				}	
			}
		});
		btnUpdate.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/rightArrow.GIF")));
		add(btnUpdate, "cell 20 13,alignx left,aligny top");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(lblNewLabel, "flowx,cell 0 15");
		
		JLabel resultMobileNo = new JLabel("");
		add(resultMobileNo, "cell 2 15");
		resultMobileNo.setText(res.get(0)[5]);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMobile = JOptionPane.showInputDialog("Please Enter New Mobile No?");
				if(newMobile.isEmpty() || newMobile.equals("") ){
					JOptionPane.showMessageDialog(null, "This should not be left blank!");
				}else{
					modifyMobileNo(newMobile);
				}	
			}
		});
		btnUpdate_1.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/rightArrow.GIF")));
		add(btnUpdate_1, "cell 20 15,alignx left,aligny top");
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/bullet.GIF")));
		add(label_5, "flowx,cell 0 17");
		
		JLabel resultEmaillbl = new JLabel("");
		add(resultEmaillbl, "cell 2 17");
		resultEmaillbl.setText(res.get(0)[6]);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmail = JOptionPane.showInputDialog("Please Enter New Email Address?");
				
				if(newEmail.equals("")){
					JOptionPane.showMessageDialog(null, "This should not be left blank!");
				}else{
					modifyEmailAddress(newEmail);
				}
			}
		});
		btnUpdate_2.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/rightArrow.GIF")));
		add(btnUpdate_2, "cell 20 17");
		
		JLabel label_10 = new JLabel("");
		add(label_10, "flowx,cell 2 20");
		
		
		JCheckBox chckbxModifyTelephoneNo = new JCheckBox("Modify Telephone No");		
		JCheckBox chckbxModifyMobileNo = new JCheckBox("Modify Mobile No");
		JCheckBox chckbxModifyEmailAddress = new JCheckBox("Modify Email Address");
		
	/*	JButton btnUpdateMyProfile = new JButton(" Update My Profile");
		btnUpdateMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(chckbxModifyTelephoneNo, "cell 6 10");	
				add(chckbxModifyMobileNo, "cell 6 11");
				add(chckbxModifyEmailAddress, "cell 6 12");
				
				if(chckbxModifyTelephoneNo.isSelected()){
					
					
				}else if (chckbxModifyMobileNo.isSelected()){
					
					
				}else if(chckbxModifyEmailAddress.isSelected()){
					
				}
			}
		});
		add(btnUpdateMyProfile, "cell 17 16");*/
		
		JLabel resultFirstNamelbl = new JLabel(firstName);
		resultFirstNamelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(resultFirstNamelbl, "cell 0 1");
		
		JLabel resultLastNamelbl = new JLabel(lastName);
		resultLastNamelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(resultLastNamelbl, "cell 0 1");
		
		JLabel registerDatelbl = new JLabel("Registered Date");
		add(registerDatelbl, "cell 0 5");
		
		JLabel lblNicNo = new JLabel(" NIC No ");
		add(lblNicNo, "cell 0 7");
		
		JLabel lblGender = new JLabel(" Gender");
		add(lblGender, "cell 0 9");
		
		JLabel lblAge = new JLabel(" Age");
		add(lblAge, "cell 0 11");
		
		JLabel lblTelephoneNo = new JLabel(" Telephone No ");
		add(lblTelephoneNo, "cell 0 13");
		
		JLabel lblMobileNo = new JLabel("  Mobile No");
		add(lblMobileNo, "cell 0 15");
		
		JLabel lblEmailAddress = new JLabel(" Email Address");
		add(lblEmailAddress, "cell 0 17");
		
		JLabel idResult = new JLabel(""+memberID);
		add(idResult, "cell 0 2,aligny top");
		
//		JButton btnViewJoinedBookings = new JButton("  Joined Bookings  ");
//		btnViewJoinedBookings.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		add(btnViewJoinedBookings, "cell 0 2,alignx center");		
		
	}
	
	
	public void modifyTelephoneNo(String newTel){
		String query1 = " UPDATE MEMBER SET TelephoneNo = '"+ newTel +"' WHERE  MemberID = "+memberID ;
	
		if(dbConn.executeQuery(query1)){
			JOptionPane.showMessageDialog(null, "Telephone no has been updated!");
			parent.showChildPanel("Blank");//just to refresh to see updated value
		}else{
			JOptionPane.showMessageDialog(null, "Try update again!");
		}
		
	}
	
	
	public void modifyMobileNo(String newTel){
		String query2 =  " UPDATE MEMBER SET MobileNo1 = '"+ newMobile +"' WHERE  MemberID = "+memberID ;
		
		
		
		if(dbConn.executeUpdate(query2)){
			JOptionPane.showMessageDialog(null, "Mobile no has been updated!");
			parent.showChildPanel("Blank");//just to refresh to see updated value
		}else{
			JOptionPane.showMessageDialog(null, "Try update again!");
		}
	}
	
	
	public void modifyEmailAddress(String newEmail){
		String query3 =  " UPDATE MEMBER SET email_Address = '"+ newEmail +"' WHERE  MemberID = "+memberID ;
		
		if(dbConn.executeUpdate(query3)){
			JOptionPane.showMessageDialog(null, "Email Address has been updated!");
			parent.showChildPanel("Blank");//just to refresh to see updated value
		}else{
			JOptionPane.showMessageDialog(null, "Try update again!");
		}
	}

}
