package carPool;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PanelService extends JPanel {	
	MainFrame parent;

	public PanelService(MainFrame frame) {
		this.parent = frame;
		//parent.logInSetVisible();

		setLayout(new MigLayout("", "[][][][][grow][][grow]", "[][grow][]"));

		JLabel lblANoteOn = new JLabel("A Note On Our Service");
		add(lblANoteOn, "cell 4 0,aligny top");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A note on our service",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, "flowx,cell 0 1 7 1");
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, "cell 4 1 7 1,grow");

		Scanner input = null;

		/*try {

			//input = new Scanner(new File("C:\\Users\\girij_000\\OneDrive\\Documents\\CPR_TermsAndConditions.txt"));
			input = new Scanner(
					new File("CPR_TermsAndConditions.txt"));

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "showing file related error!");
			e.printStackTrace();
		}*/

		/*while (input.hasNextLine()) {
			String line = input.nextLine();
			textArea.append(line + "\n");
		}

		input.close();*/
		
		 InputStream is = getClass().getResourceAsStream("/res/CPR_TermsAndConditions.txt");
		  InputStreamReader isr = new InputStreamReader(is);
		  BufferedReader br = new BufferedReader(isr);
		  String line;
		  try {
			while ((line = br.readLine()) != null) 
			  {
					textArea.append(line + "\n");
			  }
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File error!");
			e.printStackTrace();
		}
		  try {
			br.close();
			  isr.close();
			  is.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
