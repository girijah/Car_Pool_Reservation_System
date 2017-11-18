package carPool;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.nio.charset.Charset;
import java.security.Key;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

public class PanelLogIn extends JPanel {

	MainFrame parent = null;
	DataConnection dbConn = null;
	ArrayList<String> array = new ArrayList<String>();
	private int id;
	private String firstName;
	private String secondName;

	/**
	 * Create the panel.
	 */
	public PanelLogIn(MainFrame parent, DataConnection connection) {

		this.parent = parent;
		this.dbConn = connection;

		setLayout(new MigLayout("",
				"[][][][][][][][][][][grow][grow][][][][][][][][][][]",
				"[][][][][][][][][][][][][]"));

		JLabel lblLogIn = new JLabel("");
		lblLogIn.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/image/register1.jpg")));
		
		add(lblLogIn, "cell 21 4,alignx right,aligny top");
		lblLogIn.setPreferredSize(new Dimension(100, 100));

		JButton btnMemberLogin = new JButton("");
		add(btnMemberLogin, "flowx,cell 21 9");
		btnMemberLogin
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/login low.png")));

		JLabel lblAccountLogin = new JLabel(" LOG IN ");
		lblAccountLogin.setFont(new Font("Algerian", Font.BOLD, 36));
		add(lblAccountLogin, "cell 9 4,alignx center,aligny bottom");
		lblAccountLogin.setPreferredSize(new Dimension(100, 50));

		JLabel lblUsername = new JLabel("    Username");
		add(lblUsername, "cell 10 5,alignx center,aligny center");

		JLabel lblPassword = new JLabel("    Password");
		add(lblPassword, "cell 10 6,alignx center,aligny center");

		JTextField txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(150, 20));
		add(txtUsername, "cell 11 5,grow");

		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(150, 20));
		password.setToolTipText("");
		add(password, "cell 11 6,grow");

		btnMemberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtUsername.getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							" Please enter username!");

				} else if (password.getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							" Please enter password!");
				} else {

					String query = "SELECT Password, MemberID, FirstName, LastName, isMember FROM MEMBER WHERE UserName = '"
							+ txtUsername.getText() + "'";

					// ResultSet result = dbConn.executeSelectQuery(query);
					//
					// try {
					//
					// if (result != null && result.next()) {
					// System.out.println("Password="
					// + result.getString(1));
					// if (result.getString(1).equals(password.getText())) {
					//
					// //
					// ((MainFrame)PanelLogIn.this.getParent().getParent()).showChildPanel(new
					// // PanelOfferTransport());
					//
					// parent.showChildPanel(new PanelOfferTransport());
					//
					// }
					// }
					// } catch (SQLException e2) {
					// // TODO Auto-generated catch block
					// e2.printStackTrace();
					// }

					ArrayList<String[]> res = dbConn
							.executeSelectQueryEnhanced(query);

					if (res.isEmpty()) {
						JOptionPane
								.showMessageDialog(null,
										"Please Sign Up for distinguishing yourself as a member of CPR");
					}

					if (res != null && res.size() > 0) {
						System.out.println("Password=" + res.get(0)[0]);

						
						String key = "Bar12345Bar12345"; // 128 bit key
						boolean isCorrect;
						try {
							// Create key and cipher
							Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
							Cipher cipher = Cipher.getInstance("AES");

							// encrypt the text
							cipher.init(Cipher.ENCRYPT_MODE, aesKey);
							byte[] encrypted = cipher.doFinal(password.getText().getBytes());
							String passwordFieldEncrypted = new String(encrypted);
							isCorrect = passwordFieldEncrypted.equals(res.get(0)[0]);
							System.out.println();
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						if (isCorrect=true
								&& res.get(0)[4].equalsIgnoreCase("Yes")) {
							id = Integer.parseInt(res.get(0)[1]);
							firstName = res.get(0)[2];
							secondName = res.get(0)[3];

							parent.setUserId(id);
							parent.setFirstName(firstName);
							parent.setSecondName(secondName);

							parent.showOfferTransport();
							parent.showSeekTransport();
							// parent.showJoinedBooking();
							parent.showAddComment();
							//parent.showMyTaxInvoice();
							// parent.showMyBooking();
							parent.showAddCar();
							parent.showLogOut();
							parent.hideSignUp();
							parent.hideLogIn();

							parent.showChildPanel("Blank");

						} else if (isCorrect=true
								&& res.get(0)[4].equalsIgnoreCase("No")) {
							parent.setUserId(id);
							parent.setFirstName(firstName);
							parent.setSecondName(secondName);

							//parent.showTaxInvoice();
							parent.hideSignUp();
							parent.showPayment();
							parent.showSearch();
							parent.hideLogIn();
							parent.showLogOut();

						} else {
							JOptionPane.showMessageDialog(null,
									" Password didn't match! Try again! ");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								" Username is not in access! ");
					}

				}

			}
		});

	}

}
