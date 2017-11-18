package carPool;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class HomePanel extends JPanel {
	MainFrame parent = null;
	DataConnection dbConn = null;
	
	public HomePanel(MainFrame parent, DataConnection dbConn) {		
		
			this.parent = parent;
			this.dbConn = dbConn;
//			parent.logInSetVisible();
//			parent.unshowOfferTransport();
//			parent.unshowSeekTransport();
//			parent.unshowAddCar();
					
			  setLayout(new MigLayout("", "[][][][][]", "[][][][][][][][][]"));
			  
			  JLabel lblNewLabel_1 = new JLabel("Car Pool Reservation");
			  add(lblNewLabel_1, "cell 4 0,alignx right,aligny baseline");
			  lblNewLabel_1.setFont(new Font("Algerian", Font.BOLD, 34));
			  
			  JLabel lblNewLabel = new JLabel("");
			  lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/CarFront.jpg")));
			  add(lblNewLabel, "cell 0 1 5 8,alignx right,aligny bottom");


	}

}
