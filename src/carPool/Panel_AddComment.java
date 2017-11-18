package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Panel_AddComment extends JPanel {
	private String firstName;
	private String lastName;
	private int memberID;
	DataConnection dbConn = null;
	MainFrame parent = null;

	public Panel_AddComment(MainFrame frame, DataConnection connection, int id,
			String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;

		setLayout(new MigLayout("", "[][][][][][][101.00,grow][grow][92.00][][][][99.00,grow]", "[][][62.00][40.00][70.00][][146.00,grow][][][grow][]"));

		JLabel lblAddComments = new JLabel(" ADD COMMENTS");
		lblAddComments.setFont(new Font("Algerian", Font.BOLD, 32));
		add(lblAddComments, "cell 2 0");
		
		JLabel lblName = new JLabel("");
		add(lblName, "cell 12 0");
		lblName.setText("Name : " +firstName+" "+lastName );
		
		JLabel lblMemberIdResult_1 = new JLabel("");
		add(lblMemberIdResult_1, "cell 12 1");
		lblMemberIdResult_1.setText("Member ID : "+ memberID);

		JLabel lblMemberIdResult = new JLabel("");
		add(lblMemberIdResult, "cell 2 3");
	

		JLabel lblMember = new JLabel(" Member");
		lblMember.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblMember, "cell 2 2,alignx left");

		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(new Color(220, 20, 60));
		add(lblErrorMessage, "cell 3 5 6 1,alignx center,aligny top");

		JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 3 2 6 1,growx");
		String query = " SELECT MemberID,Title,FirstName,LastName FROM MEMBER";
		ArrayList<String[]> list = dbConn.executeSelectQueryEnhanced(query);

		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i)[0] + ": " + list.get(i)[1] + " "
					+ list.get(i)[2] + " " + list.get(i)[3];
			comboBox.addItem(line);
		}

		JTextArea memberComment = new JTextArea();
		add(memberComment, "cell 3 3 6 2,grow");

		JButton btnMemberComment = new JButton("SUBMIT");
		btnMemberComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] strArray = comboBox.getSelectedItem().toString()
						.split(":");
				String number = strArray[0];
				lblMemberIdResult.setText("  Member ID : " + number);

				if (memberComment.getText().equals("")) {
					lblErrorMessage
							.setText("Comment is blank! Please enter comment on a member when you experienced travelling together!");
				} else {

					String sql1 = " INSERT INTO ADDCOMMENTS(commentID, CommentFromMemberID, CommentToMemberID,Description) VALUES (seq_commentID.NEXTVAL,"
							+ memberID
							+ ","
							+ number
							+ ",'"
							+ memberComment.getText() + "')";
					System.out.println(sql1);
					if (dbConn.executeQuery(sql1)) {
						lblErrorMessage
						.setText("");
						JOptionPane
								.showMessageDialog(
										null,
										"Thank you for your valuable Comment! This will be useful for other members as well!");
					} else {
						JOptionPane
								.showMessageDialog(null,
										"The comment isn't yet updated properly! Please submit again!");
					}
				}

			}
		});

		JLabel lblAnySuggestionsQueries = new JLabel(
				" Any Suggestions/ Queries : How you feel working with CPR?");
		lblAnySuggestionsQueries.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblAnySuggestionsQueries, "cell 3 6 6 1,aligny bottom");
		add(btnMemberComment, "cell 9 6,alignx right,aligny top");

		JTextArea suggestiontxt = new JTextArea();
		suggestiontxt.setPreferredSize(new Dimension(400, 110));
		add(suggestiontxt, "cell 3 7 6 3,grow");

		JLabel lblErrorIndication = new JLabel("");
		lblErrorIndication.setForeground(new Color(220, 20, 60));
		add(lblErrorIndication, "cell 3 10 6 1,alignx center");

		JButton btnSuggestSubmit = new JButton("SUBMIT");
		btnSuggestSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (suggestiontxt.getText().equals("")) {
					lblErrorIndication
							.setText(" Comment is blank. Please enter if there is any suggestion or query!");
				} else {
					String sql2 = "INSERT INTO SUGGESTION (SuggestionID ,MemberID, Suggestion ) VALUES (seq_suggestionID.NEXTVAL,"
							+ memberID + ",'" + suggestiontxt.getText() + "')";
					if (dbConn.executeQuery(sql2)) {
						lblErrorIndication
						.setText("");
						JOptionPane
								.showMessageDialog(null,
										"Suggestion has been sent successfully! Thank you!");
					} else {
						JOptionPane
								.showMessageDialog(null,
										"The message didn't send properly! Submit Again!");
					}
				}
			}
		});

		add(btnSuggestSubmit, "cell 9 10,aligny bottom");
	}

}
