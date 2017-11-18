package carPool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	private JPanel parentPane;
	private JPanel contentPanel;
	private JButton btnLogIn;
	private JButton btnLogOut;
	private JButton btnPayment;
	private JButton btnSearch;
	private JButton btnAddCar;
	private JButton btnAddComment;
	private JButton btnOfferRide;
	private JButton btnMyBooking;
	private JButton btnJoinedBooking;
	private JButton btnTaxInvoice;
	private JButton btnCharge;
	private JButton btnSeekRide;
	private JButton btnSignUp;
	private JToolBar toolBar;
	private int userId = 0;
	private String firstName;
	private String secondName;

	DataConnection databaseConnection = new DataConnection();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainFrame frame = new MainFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public MainFrame() {

		setTitle("Car Pool Reservation of Western Province, Sri Lanka");

		// Show login UI and get username and password

		showMainUI();

		/*
		 * while (true) {
		 * 
		 * if (showLoginUI()) {
		 * 
		 * showMainUI(); break;
		 * 
		 * } else {
		 * 
		 * int result = JOptionPane .showConfirmDialog( null,
		 * "Incorrect login credentials... ! Want to try once again?", "",
		 * JOptionPane.YES_NO_OPTION);
		 * 
		 * if (result == 0) { // Do nothing // Show login UI again on the next
		 * loop } else { System.exit(0); } } }
		 */

	}

	/*
	 * public boolean showLoginUI() { // TODO // Show login UI and validate
	 * username and password return new JOptionPaneMultiInput().show(); }
	 */

	public void showMainUI() {

		// new PanelCarPoolImage(MainFrame.this, databaseConnection);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		setMinimumSize(new Dimension(1024, 512));
		setExtendedState(MAXIMIZED_BOTH);

		// setBounds(100, 100, 1320, 700);
		parentPane = new JPanel();
		parentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		parentPane.setLayout(new BorderLayout(0, 0));
		parentPane.setBackground(new Color(75, 0, 130));
		setContentPane(parentPane);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		parentPane.add(toolBar, BorderLayout.NORTH);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane jScrollPane = new JScrollPane(contentPanel);
		parentPane.add(jScrollPane, BorderLayout.CENTER);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(50);

		contentPanel.add(new HomePanel(MainFrame.this,
				databaseConnection));

		JButton homeBtn = new JButton("");
		homeBtn.setIcon(new ImageIcon(getClass().getResource(
				"/carPool/Buttons/home.png")));
		toolBar.add(homeBtn);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (userId > 0) {
					showChildPanel("Blank");
				} else {
					showLogIn();
					hideOfferTransport();
					hideSeekTransport();
					hideAddCar();
					showChildPanel("HomePanel");
				}

			}
		});

		btnLogIn = new JButton("");
		btnLogIn.setIcon(new ImageIcon(getClass().getResource(
				"/carPool/Buttons/login low.png")));
		toolBar.add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("LogIn");

			}
		});

		btnSignUp = new JButton("");
		btnSignUp
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/signup.png")));
		toolBar.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("SignUp");

			}
		});

		JButton serviceBtn = new JButton("");
		serviceBtn
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/service lowi.png")));
		toolBar.add(serviceBtn);
		serviceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				showChildPanel("Service");

			}
		});

		btnAddCar = new JButton("");
		btnAddCar
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/addCar.png")));
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				showChildPanel("AddCar");

			}
		});
		
		
		btnAddComment = new JButton("");
		btnAddComment.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/comment.png")));
		btnAddComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				showChildPanel("AddComment");

			}
		});
		

		btnOfferRide = new JButton("");
		btnOfferRide
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/offerRide.png")));
		btnOfferRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("OfferRide");

			}
		});

		btnSeekRide = new JButton("");
		btnSeekRide
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/seekRide.png")));
		btnSeekRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("SeekRide");

			}
		});
		
		/*btnTaxInvoice = new JButton("TaxInvoice");
		//btnTaxInvoice.setIcon(new ImageIcon(""));
		btnTaxInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("TaxInvoice");

			}
		});*/
		
		btnPayment = new JButton("");
		btnPayment.setIcon(new ImageIcon(getClass().getResource("/carPool/Buttons/payment.png")));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("Payment");

			}
		});
		
		btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon("src/carPool/Buttons/search.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("Search");

			}
		});
		
//		btnCharge = new JButton("");
//		btnCharge.setIcon(new ImageIcon("src/carPool/Buttons/charges.png"));
//		btnCharge.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				showChildPanel("MyTaxInvoice");
//
//			}
//		});
		 
		 /*btnMyBooking = new JButton("MyBooking");
		// btnMyBooking.setIcon(new ImageIcon(""));
		 btnMyBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("MyBooking");

			}
		});*/
		
		/* btnJoinedBooking = new JButton("JoinedBooking");
		// btnJoinedBooking.setIcon(new ImageIcon(""));
		 btnJoinedBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showChildPanel("JoinedBooking");

			}
		});*/
		

		btnLogOut = new JButton("");
		btnLogOut
				.setIcon(new ImageIcon(getClass().getResource(
						"/carPool/Buttons/logout.png")));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure, you want to log out?",
						"Logout Confirmation", JOptionPane.YES_NO_OPTION);

				// 0 means yes
				if (result == 0) {

					userId = 0;
					firstName = "";
					secondName = "";

					hideLogOut();
					hideOfferTransport();
					hideSeekTransport();
					//hideJoinedBooking();
					//hideMyBooking();					
					hideAddComment();
					hideAddCar();
					//hideTaxInvoice();
					//hideMyTaxInvoice();
					hideSearch();
					hidePayment();
					//hideCharge();
					showSignUp();
					showChildPanel("HomePanel");
				}

			}
		});

		showChildPanel("HomePanel");

	}

	/*
	 * public JPanel createChildPanel(String panel) {
	 * 
	 * if (panel.equalsIgnoreCase("Naai")) { return new
	 * PanelOfferTransport(MainFrame.this, databaseConnection,userId);
	 * 
	 * } else if (panel.equalsIgnoreCase("Blank")) { return new
	 * PanelBlank(MainFrame.this, databaseConnection, userId); } else if
	 * (panel.equalsIgnoreCase("AddCar")) { return new
	 * PanelAddCar(MainFrame.this, databaseConnection, userId); }
	 * 
	 * return null; }
	 */

	public void setUserId(int id) {
		this.userId = id;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public void setSecondName(String sName) {
		this.secondName = sName;
	}

	public void showChildPanel(String strPanel) {

		JPanel panel = null;

		if (strPanel.equalsIgnoreCase("OfferRide")) {
			panel = new PanelOfferTransport(MainFrame.this, databaseConnection,
					userId, firstName, secondName);
		} else if (strPanel.equalsIgnoreCase("HomePanel")) {
			panel = new HomePanel(MainFrame.this, databaseConnection);
		} else if (strPanel.equalsIgnoreCase("LogIn")) {
			panel = new PanelLogIn(MainFrame.this, databaseConnection);
		} else if (strPanel.equalsIgnoreCase("SignUp")) {
			panel = new PanelSignUp(MainFrame.this, databaseConnection);
		} else if (strPanel.equalsIgnoreCase("Service")) {
			panel = new PanelService(MainFrame.this);
		} else if (strPanel.equalsIgnoreCase("AddCar")) {
			panel = new Panel_AddCar(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		} else if (strPanel.equalsIgnoreCase("SeekRide")) {
			panel = new PanelSeekTransport(MainFrame.this, databaseConnection,
					userId, firstName, secondName);
		} else if (strPanel.equalsIgnoreCase("TaxInvoice")) {
			panel = new PanelTaxInvoice();
		} else if (strPanel.equalsIgnoreCase("Blank")) {
			panel = new PanelBlank(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("AddComment")){
			panel = new Panel_AddComment(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("MyBooking")){
			panel = new PanelMyBooking(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("JoinedBooking")){
			panel = new Panel_JoinedBooking(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("MyTaxInvoice")){
			panel = new Panel_MyInvoice(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("Search")){
			panel = new PanelSearch(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}else if (strPanel.equalsIgnoreCase("Payment")){
			panel = new PanelPayment(MainFrame.this, databaseConnection, userId,
					firstName, secondName);
		}

		contentPanel.removeAll();
		contentPanel.add(panel);
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	public void hideLogIn() {
		btnLogIn.setVisible(false);
	}

	public void showLogOut() {
		toolBar.add(btnLogOut);
		btnLogOut.setVisible(true);
	}

	public void hideLogOut() {
		btnLogOut.setVisible(false);
		btnLogIn.setVisible(true);

	}

	public void loginlogout() {
		btnLogOut.setVisible(true);
		btnLogIn.setVisible(false);
	}

	public void showLogIn() {
		btnLogIn.setVisible(true);
	}

	public void showAddCar() {
		toolBar.add(btnAddCar);
		btnAddCar.setVisible(true);
	}

	public void hideAddCar() {
		btnAddCar.setVisible(false);
	}
	
	public void showAddComment() {
		toolBar.add(btnAddComment);
		btnAddComment.setVisible(true);
	}
	
	public void hideAddComment() {
		btnAddComment.setVisible(false);
	}

	
	public void showOfferTransport() {
		toolBar.add(btnOfferRide);
		btnOfferRide.setVisible(true);
	}

	public void hideOfferTransport() {
		btnOfferRide.setVisible(false);
	}

	public void showSeekTransport() {
		toolBar.add(btnSeekRide);
		btnSeekRide.setVisible(true);
	}

	public void hideSeekTransport() {
		btnSeekRide.setVisible(false);
	}
	
	public void showMyBooking() {
		toolBar.add(btnMyBooking);
		btnMyBooking.setVisible(true);
	}
	
	public void hideMyBooking() {
		btnMyBooking.setVisible(false);
	}
	
	public void showPayment() {
		toolBar.add(btnPayment);
		btnPayment.setVisible(true);
	}
	
	public void hidePayment() {
		btnPayment.setVisible(false);
	}
	
	public void showSearch() {
		toolBar.add(btnSearch);
		btnSearch.setVisible(true);
	}
	
	public void hideSearch() {
		btnSearch.setVisible(false);
	}
	
	public void showJoinedBooking() {
		toolBar.add(btnJoinedBooking);
		btnJoinedBooking.setVisible(true);
	}
	
	public void hideJoinedBooking() {
		btnJoinedBooking.setVisible(false);
	}
	
//	public void showMyTaxInvoice() {
//		toolBar.add(btnCharge);
//		btnCharge.setVisible(true);
//	}
	
//	public void hideMyTaxInvoice() {
//		btnCharge.setVisible(false);
//	}
	
	public void showTaxInvoice() {
		toolBar.add(btnTaxInvoice);
		btnTaxInvoice.setVisible(true);
	}	

	public void showSignUp() {
		btnSignUp.setVisible(true);
	}
	
	public void hideSignUp() {
		btnSignUp.setVisible(false);
	}
	
//	public void hideCharge(){
//	btnCharge.setVisible(false);	
//	}

}
