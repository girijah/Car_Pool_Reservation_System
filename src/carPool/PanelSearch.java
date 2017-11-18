package carPool;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelSearch extends JPanel {
	MainFrame parent = null;
	DataConnection dbConn = null;
	private int adminID;
	private String firstName;
	private String lastName;
	/**
	 * Create the panel.
	 */
	public PanelSearch(MainFrame frame, DataConnection connection,
			int id, String firstName, String lastName) {
		this.parent = frame;
		this.dbConn = connection;
		this.adminID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel lblSearch = new JLabel(" Search");
		lblSearch.setFont(new Font("Algerian", Font.BOLD, 36));
		add(lblSearch, "cell 0 0");
		

	}

}
