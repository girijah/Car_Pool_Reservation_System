package carPool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;

import javax.swing.ImageIcon;


import net.miginfocom.swing.MigLayout;

public class PanelSignUp extends JPanel {

	private JTextField firstNametextField;
	private JTextField nictextField;
	private JTextField emailtextField;
	private JTextField tel$1textField;
	private JTextField tel$2textField;
	private JTextField userNameTextField;
	private JLabel errorIndicationlabel;
	private JLabel genderResultlabel;
	private JLabel ageResultlabel;
	private JTextField lastNametextField;
	private JPasswordField passwordTextField;
	private Pattern pattern;
	private Matcher matcher;
	private JComboBox titlecomboBox;

	MainFrame parent = null;
	DataConnection dbConn = new DataConnection();
	ArrayList<String> array = new ArrayList<String>();
	private final String NAME_PATTERN = "[A-Z][a-zA-Z]*";
	private final String NUMBER_PATTERN = "[0-9]";
	private final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@”+”[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// private final String PHONE_NO_PATTERN = "^\\+?[0-9. ()-]{10,25}$";

	public PanelSignUp(MainFrame frame, DataConnection connection) {
		this.parent = frame;
		this.dbConn = connection;
		parent.showLogIn();

		pattern = Pattern.compile(USERNAME_PATTERN);

		setLayout(new MigLayout("",
				"[152.00][146.00][169.00][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][]"));

		JLabel lblMemberEntryVitae = new JLabel(" SIGN UP ");
		lblMemberEntryVitae.setFont(new Font("Algerian", Font.BOLD, 36));
		lblMemberEntryVitae.setPreferredSize(new Dimension(100, 100));
		add(lblMemberEntryVitae, "cell 1 0");

		JLabel labelHi = new JLabel("");
		add(labelHi, "cell 7 0");

		JLabel label = new JLabel("");
		// label.setIcon(new
		// ImageIcon(PanelSignUp.class.getResource("/CarPoolReservation/src/carPool/registerMan.jpg")));
		label.setIcon(new ImageIcon(getClass().getResource(
				"/carPool/Buttons/registerMan.jpg")));
		add(label, "cell 6 0,alignx right");

		JLabel lblTitle = new JLabel(" Title");
		lblTitle.setPreferredSize(new Dimension(200, 20));
		add(lblTitle, "cell 1 1");

		titlecomboBox = new JComboBox();
		titlecomboBox.setPreferredSize(new Dimension(200, 20));
		add(titlecomboBox, "cell 2 1");
		titlecomboBox.addItem("Mr.");
		titlecomboBox.addItem("Master.");
		titlecomboBox.addItem("Miss.");
		titlecomboBox.addItem("Mrs.");
		titlecomboBox.addItem("Ms.");
		titlecomboBox.addItem("Madam.");
		titlecomboBox.addItem("Sir.");

		JLabel lblName = new JLabel(" First Name*");
		add(lblName, "cell 1 2");

		JLabel lblLastName = new JLabel(" Last Name*");
		lblLastName.setPreferredSize(new Dimension(200, 20));
		add(lblLastName, "cell 1 3");

		lastNametextField = new JTextField();
		lastNametextField.setPreferredSize(new Dimension(200, 20));
		add(lastNametextField, "cell 2 3,growx");
		lastNametextField.setColumns(10);

		JLabel lblTelephoneNo = new JLabel(" Telephone No*\r\n");
		lblTelephoneNo.setPreferredSize(new Dimension(200, 20));
		add(lblTelephoneNo, "cell 1 6");

		firstNametextField = new JTextField();
		firstNametextField.setPreferredSize(new Dimension(200, 20));
		add(firstNametextField, "cell 2 2,grow");
		firstNametextField.setColumns(10);
		lblName.setPreferredSize(new Dimension(200, 20));

		tel$1textField = new JTextField();
		tel$1textField.setPreferredSize(new Dimension(200, 20));
		add(tel$1textField, "cell 2 6,growx");
		tel$1textField.setColumns(10);

		tel$2textField = new JTextField();
		tel$2textField.setPreferredSize(new Dimension(200, 20));
		add(tel$2textField, "cell 2 7,growx");
		tel$2textField.setColumns(10);

		JLabel lblEmail = new JLabel(" e-mail*");
		lblEmail.setPreferredSize(new Dimension(200, 20));
		add(lblEmail, "cell 1 8");

		emailtextField = new JTextField();
		emailtextField.setPreferredSize(new Dimension(200, 20));
		add(emailtextField, "cell 2 8,growx");
		emailtextField.setColumns(10);

		JLabel lblNicNo = new JLabel(" NIC No*");
		lblNicNo.setPreferredSize(new Dimension(200, 20));
		add(lblNicNo, "flowx,cell 1 4");

		nictextField = new JTextField();
		add(nictextField, "flowx,cell 2 4,growx");
		nictextField.setColumns(10);

		JLabel lblMobileNo = new JLabel(" Mobile No-1*");
		lblMobileNo.setPreferredSize(new Dimension(200, 20));
		add(lblMobileNo, "cell 1 7");

		JLabel lblUsername = new JLabel(" Username*");
		lblUsername.setPreferredSize(new Dimension(200, 20));
		add(lblUsername, "cell 1 10");

		userNameTextField = new JTextField();
		userNameTextField.setPreferredSize(new Dimension(200, 20));
		add(userNameTextField, "cell 2 10,growx");
		userNameTextField.setColumns(10);

		JLabel lblPassword = new JLabel(" Password*\r\n");
		add(lblPassword, "cell 1 11");

		passwordTextField = new JPasswordField();
		passwordTextField.setPreferredSize(new Dimension(150, 20));
		add(passwordTextField, "cell 2 11,grow");

		errorIndicationlabel = new JLabel("");
		errorIndicationlabel.setForeground(new Color(255, 0, 0));
		errorIndicationlabel.setBackground(Color.RED);
		errorIndicationlabel.setPreferredSize(new Dimension(200, 20));
		add(errorIndicationlabel, "cell 1 13");

		JButton btnHi = new JButton("");

		JButton submitbutton = new JButton("Create my profile");
		add(submitbutton, "cell 2 16");

		JButton resetbutton = new JButton("  Reset");
		add(resetbutton, "cell 4 16");

		JLabel lblAge = new JLabel("");
		lblAge.setForeground(Color.BLUE);
		add(lblAge, "flowx,cell 1 5");

		ageResultlabel = new JLabel("");
		ageResultlabel.setForeground(Color.BLUE);
		ageResultlabel.setPreferredSize(new Dimension(200, 20));
		add(ageResultlabel, "cell 1 5,alignx right");

		JLabel genderlbl = new JLabel("");
		genderlbl.setForeground(Color.BLUE);
		add(genderlbl, "cell 1 5");

		genderResultlabel = new JLabel("");
		genderResultlabel.setForeground(Color.BLUE);
		genderResultlabel.setPreferredSize(new Dimension(200, 20));
		add(genderResultlabel, "cell 1 5,alignx right");

		submitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date date = new Date();
				String registeredDate = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss").format(date);
				System.out.println(registeredDate);

				String defYear = null;
				String stringYear = null;
				int correctYr = 0;
				int year = 99999;
				int day = 0;

				if (firstNametextField.getText().equals("")
						|| lastNametextField.getText().equals("")
						|| nictextField.getText().equals("")
						|| emailtextField.getText().equals("")
						|| tel$1textField.getText().equals("")
						|| tel$2textField.getText().equals("")
						|| userNameTextField.getText().equals("")
						|| passwordTextField.getText().equals("")) {

					errorIndicationlabel
							.setText(" ERROR NOTE: *No Blank Space Left!");

				} else {
					errorIndicationlabel.setText("");
				}

				if (!nictextField.getText().equals("")
						&& nictextField.getText().trim().length() != 10) {
					JOptionPane
							.showMessageDialog(null,
									"Sorry, Your NIC Number should contain ten values!");
				}

				boolean boolName = nameValidation(firstNametextField.getText());
				boolean booleanName = nameValidation(lastNametextField
						.getText());

				if (boolName == false || booleanName == false) {
					JOptionPane
							.showMessageDialog(null,
									"Name should starts with capitals! Can't call numbers you know!");
				}
				System.out.println("boolName :" + boolName);
				System.out.println("booleanName :" + booleanName);

				boolean boolUserName = userNameValidation(userNameTextField
						.getText());

				if (boolUserName == false) {
					JOptionPane
							.showMessageDialog(
									null,
									"Only small letters, numbers and only underscore, hyphen symbols allowed! Username should be 3-15 in length!");
				}

				boolean boolNumber = false;
				System.out.println("boolUserName :" + boolUserName);

				for (int i = 5; i < 8; i++) {
					boolNumber = numberValidation(nictextField.getText()
							.charAt(i));
				}
				System.out.println("boolNumber :" + boolNumber);

				if (boolNumber == false) {
					JOptionPane.showMessageDialog(null, " Invalid NIC Number!");
				}

				boolean boolPhoneNo = phoneNoValidation(tel$1textField
						.getText());
				boolean boolMobileNo = phoneNoValidation(tel$2textField
						.getText());

				// if (boolPhoneNo == false || boolMobileNo == false) {
				// JOptionPane
				// .showMessageDialog(
				// null,
				// " Contact number is expected without the leading zero and xx-xxx-xxxx !");
				// }

				boolean boolEmail = emailValidation(emailtextField.getText());
				System.out.println("boolPhoneNo :" + boolPhoneNo);
				System.out.println("boolMobileNo :" + boolMobileNo);
				System.out.println("boolEmail :" + boolEmail);

				if (boolUserName == false) {
					JOptionPane.showMessageDialog(null,
							"Invalid email address!");
				}

				if (passwordTextField.getText().length() < 5) {
					JOptionPane.showMessageDialog(null,
							"Password should atleast consist of 4 characters!");
				}

				boolean separator = false;

				try {
					year = Integer.parseInt(nictextField.getText().substring(0,
							2));
					day = Integer.parseInt(nictextField.getText().substring(2,
							5));
				} catch (Exception exc) {
					separator = true;
					JOptionPane
							.showMessageDialog(null,
									"Give a valid National ID Number,you are unique for that!");

					nictextField.setText("");
				}

				if (!(nictextField.getText().charAt(9) == 'v'
						|| nictextField.getText().charAt(9) == 'V'
						|| nictextField.getText().charAt(9) == 'x' || nictextField
						.getText().charAt(9) == 'X')) {
					JOptionPane.showMessageDialog(null,
							"NIC last digit needs to be V or X!");
				}

				if (day > 0 && day < 500) {
					genderResultlabel.setText("male");
				} else if (day >= 500) {
					genderResultlabel.setText("female");
				}

				if (year <= 99 && year >= 16) {
					defYear = "19" + year;
					correctYr = Integer.parseInt(defYear);
					year = 2015 - correctYr;
					stringYear = Integer.toString(year);
					lblAge.setText("*** You are ");
					ageResultlabel.setText(stringYear);
					genderlbl.setText(" years old ");
				} else if (year >= 0 && year < 10) {
					defYear = "200" + year;
					correctYr = Integer.parseInt(defYear);
					year = 2015 - correctYr;
					stringYear = Integer.toString(year);
					lblAge.setText("*** You are ");
					ageResultlabel.setText(stringYear);
					genderlbl.setText(" years old ");
				} else if (year >= 10 && year <= 16) {
					defYear = "20" + year;
					correctYr = Integer.parseInt(defYear);
					year = 2015 - correctYr;
					stringYear = Integer.toString(year);
					lblAge.setText("*** You are ");
					ageResultlabel.setText(stringYear);
					genderlbl.setText(" years old ");
				}

				int age = Integer.parseInt(ageResultlabel.getText());
				System.out.println(age);

				String userNameDupCheck = "SELECT Username FROM MEMBER WHERE Username = '"
						+ userNameTextField.getText() + "'";
				ResultSet resultset = dbConn
						.executeSelectQuery(userNameDupCheck);
				String dbResult = null;
				try {
					if (resultset != null && resultset.next()
							&& !resultset.getString(1).equals("")) {
						dbResult = resultset.getString(1);
						if (dbResult.length() != 0) {
							JOptionPane
									.showMessageDialog(null,
											"Username you've entered is already existing. Try another?");
						}
					}
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null,
							"Username Duplicate check exception!");
					e2.printStackTrace();
				}
				
				String key = "Bar12345Bar12345"; // 128 bit key

				// Create key and cipher
				Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
				Cipher cipher = null;
				try {
					cipher = Cipher.getInstance("AES");
				} catch (NoSuchAlgorithmException e1) {
					
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					
					e1.printStackTrace();
				}

				// encrypt the text
				try {
					cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				} catch (InvalidKeyException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				byte[] encrypted=null;
				try {
					encrypted = cipher.doFinal( passwordTextField.getText().getBytes());
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String encryptedPassword = new String(encrypted);
				System.err.println("encryptedPassword : "+encryptedPassword+"\n");
				//System.out.println("");
				

				String command = "INSERT INTO MEMBER(MemberID,Title, FirstName, LastName, NIC_Number,Gender,Age,TelephoneNo,MobileNo1,email_Address,Username,Password, RegisteredDate, isMember) VALUES (seq_memberID.nextval,'"
						+ titlecomboBox.getSelectedItem()
						+ "', '"
						+ firstNametextField.getText()
						+ "', '"
						+ lastNametextField.getText()
						+ "', '"
						+ nictextField.getText()
						+ "', '"
						+ genderResultlabel.getText()
						+ "',"
						+ age
						+ ",'"
						+ tel$1textField.getText()
						+ "','"
						+ tel$2textField.getText()
						+ "', '"
						+ emailtextField.getText()
						+ "','"
						+ userNameTextField.getText()
						+ "', '"
						+ encryptedPassword
						+ "', to_date('"
						+ registeredDate + "','YYYY/MM/DD HH24:MI:SS'),'Yes')";

				System.out.println("Query: " + command);

				if (dbResult == null
						&& !(firstNametextField.getText().equals("")
								|| lastNametextField.getText().equals("")
								|| nictextField.getText().equals("")
								|| emailtextField.getText().equals("")
								|| tel$1textField.getText().equals("")
								|| tel$2textField.getText().equals("")
								|| userNameTextField.getText().equals("") || passwordTextField
								.getText().equals(""))
						&& nictextField.getText().length() == 10
						&& separator == false && boolName == true
						&& booleanName == true && boolUserName == true) {

					boolean returnedValue = dbConn.executeQuery(command);

					if (returnedValue == true) {
						JOptionPane
								.showMessageDialog(null,
										" Profile created! Thanks for joining, Now enjoy car pooling!");
						String query = "select MemberID from MEMBER where NIC_Number = '"+nictextField.getText() +"'";
						String memberID = dbConn.executeSelectQueryEnhanced(query).get(0)[0];
						String sql = " INSERT INTO  TAX_INVOICE(TaxInvoiceID,BookingID,MemberID,ChargeID,Date_Time )VALUES(seq_TaxInvoiceID.nextval,'',"+memberID+ ",'PER-MEM-01', To_Date('"+registeredDate+ "', 'YYYY/MM/DD HH24:MI:SS'))";
						System.out.println("Tax invoice inserted : "+dbConn.executeQuery(sql));

						labelHi.setIcon(new ImageIcon(getClass().getResource(
								"/carPool/Buttons/FreddieTheFox.png")));
						add(btnHi, "cell 8 1");
						btnHi.setText("Hi");

						btnHi.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								JOptionPane
										.showMessageDialog(null,
												"This will take you to access your New Account!");

								parent.showChildPanel("LogIn");
							}
						});

					} else {
						JOptionPane
								.showMessageDialog(null,
										"Please click button again for the submission!");
					}
				} else if (!dbResult.equals("")) {
					JOptionPane
							.showMessageDialog(
									null,
									"Sorry, the username you entered is currenly in use, please enter a unique username!");
				}

			}

		});

		resetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				firstNametextField.setText("");
				lastNametextField.setText("");
				nictextField.setText("");
				emailtextField.setText("");
				tel$1textField.setText("");
				tel$2textField.setText("");
				userNameTextField.setText("");
				passwordTextField.setText("");
				errorIndicationlabel.setText("");
				ageResultlabel.setText("");
				genderResultlabel.setText("");
				genderlbl.setText("");
				lblAge.setText("");
				labelHi.setIcon(new ImageIcon(""));
			}
		});

	}

	public boolean nameValidation(String name) {

		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(name);

		return (matcher.matches());

	}

	public boolean userNameValidation(String userName) {

		pattern = Pattern.compile(USERNAME_PATTERN);
		matcher = pattern.matcher(userName);

		return (matcher.matches());

	}

	public boolean numberValidation(char digit) {
		String number = "" + digit;
		pattern = Pattern.compile(NUMBER_PATTERN);
		matcher = pattern.matcher(number);

		return (matcher.matches());

	}

	public boolean phoneNoValidation(String phone) {

		// pattern = Pattern.compile(PHONE_NO_PATTERN);
		matcher = pattern.matcher(phone);

		return (matcher.matches());

	}

	public boolean emailValidation(String email) {

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);

		return (matcher.matches());

	}

}
