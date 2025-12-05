package hotel_reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class Main_page extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Database conn = new Database();
	public JPanel MainPage = new JPanel();
	public JPanel SideBar = new JPanel();
	public JLabel LuxeStay = new JLabel("");
	public JTabbedPane MainPanel = new JTabbedPane(JTabbedPane.TOP);
	public JButton guestButton = new JButton("GUEST");
	public JButton homeButtom = new JButton("HOME");
	public JButton paymentButton = new JButton("PAYMENT");
	public JButton logoutButton = new JButton("LOGOUT");
	public JPanel Home = new JPanel();
	public JPanel Guest = new JPanel();
	public JPanel Payment = new JPanel();
	public JPanel reservation = new JPanel();
	public JTextField first_name = new JTextField();
	public JTextField last_name = new JTextField();
	public JTextField age = new JTextField();
	public JTextField gender = new JTextField();
	public JTextField room = new JTextField();
	public JTextField address = new JTextField();
	public JTextField contact_no= new JTextField(); 
	public JTextField payment= new JTextField();
	public JTextField date_reservation= new JTextField();
	public JDateChooser dateChooser = new JDateChooser();
	public List<JButton> roomButtons = new ArrayList<>();
	public String room_name = "";
	public String formatted = "";
	public String formatted_checkout = "";
	public JButton Return = new JButton("Return");
	private final JTextField searchBar = new JTextField();
	private final JButton searchbutton = new JButton("Search");
	private final JTextField firstname_payment = new JTextField();
	private final JTextField lastname_payment = new JTextField();
	private final JTextField age_payment = new JTextField();
	private final JTextField gender_payment = new JTextField();
	private final JTextField room_payment = new JTextField();
	private final JTextField address_payment = new JTextField();
	private final JTextField payment_payment = new JTextField();
	private final JTextField date_payment = new JTextField();
	private final JTextField contact_payment = new JTextField();
	private final JTextField checkout_payment = new JTextField();
	private final JTextField id_payment = new JTextField();
	private final JTextField payment_for_reservation = new JTextField();
	private final JButton btnPay = new JButton("Pay");
	private final JButton EditButton = new JButton("Edit");
	private final JButton deleteButton = new JButton("Delete");
	private final JLabel Firstname_Label_1 = new JLabel("First Name");
	private final JLabel Lastname_Label_1 = new JLabel("Last Name");
	private final JLabel Age_Label_1 = new JLabel("Age");
	private final JLabel Firstname_Label_3_1 = new JLabel("Gender");
	private final JLabel Firstname_Label_4_1 = new JLabel("Room");
	private final JLabel Firstname_Label_5_1 = new JLabel("Address");
	private final JLabel Firstname_Label_6_1 = new JLabel("Contact No.");
	private final JLabel Firstname_Label_7_1 = new JLabel("Payment");
	private final JLabel Firstname_Label_8_1 = new JLabel("Date");
	private final JLabel Firstname_Label_9_1 = new JLabel("Checkout");
	private final JLabel Firstname_Label_9_1_1 = new JLabel("Reservation Id");
	JDateChooser checkout_date = new JDateChooser();
	public JTable tableReservation;
	public int price = 1000;
	
	
	public Main_page() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 900);
		setLocationRelativeTo(null);
		
		
		MainPage.setBackground(new Color(255, 255, 255));
		MainPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPage);
		MainPage.setLayout(null);
		
		
		SideBar.setBackground(new Color(175, 186, 198));
		SideBar.setBounds(0, 0, 337, 900);
		MainPage.add(SideBar);
		SideBar.setLayout(null);
		
		
		LuxeStay.setIcon(new ImageIcon("/home/berting/eclipse-workspaceitj/Hotel_Reservation/luxestaysidebar.png"));
		LuxeStay.setBounds(0, 24, 333, 151);
		SideBar.add(LuxeStay);
		
		
		homeButtom.setForeground(new Color(255, 255, 255));
		homeButtom.setBackground(new Color(24, 0, 173));
		homeButtom.setFont(new Font("Dialog", Font.BOLD, 25));
		homeButtom.setBounds(47, 187, 213, 54);
		SideBar.add(homeButtom);
		
		homeButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeButtom.setForeground(new Color(255, 255, 255));
				homeButtom.setBackground(new Color(24, 0, 173));
				guestButton.setBackground(new Color(255, 255, 255));
				guestButton.setForeground(new Color(51, 51, 51));
				paymentButton.setBackground(new Color(255, 255, 255));
				paymentButton.setForeground(new Color(51, 51, 51));
				MainPanel.setSelectedIndex(0);
				refreshRooms();
				
			}
		});
		guestButton.setBackground(new Color(255, 255, 255));
		guestButton.setForeground(new Color(51, 51, 51));
		guestButton.setFont(new Font("Dialog", Font.BOLD, 25));
		guestButton.setBounds(47, 267, 213, 54);
		SideBar.add(guestButton);
		
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guestButton.setBackground(new Color(24, 0, 173));
				guestButton.setForeground(new Color(255, 255, 255));
				homeButtom.setBackground(new Color(255, 255, 255));
				homeButtom.setForeground(new Color(51, 51, 51));
				paymentButton.setBackground(new Color(255, 255, 255));
				paymentButton.setForeground(new Color(51, 51, 51));
				MainPanel.setSelectedIndex(1);
				List<List<String>> reservationData = conn.getReservationData();
				System.out.println(reservationData);
				
//				List<List<String>> reservationData = conn.getReservationData();

		        DefaultTableModel model = (DefaultTableModel) tableReservation.getModel();
		        model.setRowCount(0); // clear old rows

		        for (List<String> row : reservationData) {
		            model.addRow(row.toArray());
		        }
			}
		});
		
		paymentButton.setBackground(new Color(255, 255, 255));
		paymentButton.setForeground(new Color(51, 51, 51));
		paymentButton.setFont(new Font("Dialog", Font.BOLD, 25));
		paymentButton.setBounds(47, 351, 213, 54);
		SideBar.add(paymentButton);
		
		paymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentButton.setBackground(new Color(24, 0, 173));
				paymentButton.setForeground(new Color(255, 255, 255));
				homeButtom.setBackground(new Color(255, 255, 255));
				homeButtom.setForeground(new Color(51, 51, 51));
				guestButton.setBackground(new Color(255, 255, 255));
				guestButton.setForeground(new Color(51, 51, 51));
				MainPanel.setSelectedIndex(2);
				
			}
		});
		
		
		logoutButton.setFont(new Font("Dialog", Font.BOLD, 25));
		logoutButton.setBounds(47, 763, 213, 54);
		SideBar.add(logoutButton);
		
		
		MainPanel.setBounds(349, -25, 931, 888);
		MainPage.add(MainPanel);
		
		
		MainPanel.addTab("Home tab", null, Home, null);
		Home.setLayout(null);
		
		int startX = 26;
		int startY = 201;
		int width = 149;
		int height = 58;
		int gapX = 182;
		int gapY = 96;
		int columns = 5;
		
		for (int i = 1; i <= 30; i++) {
		    JButton room = new JButton("Room " + i);
		    room.setBackground(Color.WHITE);

		    int row = (i - 1) / columns;
		    int col = (i - 1) % columns;

		    int x = startX + col * gapX;
		    int y = startY + row * gapY;
		    room.setBounds(x, y, width, height);

		    Home.add(room);
		    roomButtons.add(room);
		}

		
		dateChooser.getCalendarButton().setBackground(new Color(24, 0, 173));
		dateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		dateChooser.setBounds(649, 122, 246, 43);
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setFont(new Font("Dialog", Font.BOLD, 20));
		dateChooser.setDate(new java.util.Date());
		Home.add(dateChooser);

		Runnable updateRooms = () -> {
		    Date date = dateChooser.getDate();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    formatted = sdf.format(date);
		    List<List<String>> reservations = conn.GetRoomData(formatted);

		    for (JButton btn : roomButtons) {
		        for (ActionListener al : btn.getActionListeners()) {
		            btn.removeActionListener(al);
		        }
		        btn.setEnabled(true);
		        btn.setBackground(Color.WHITE);
		    }

		    for (List<String> row : reservations) {
		        String roomName = row.get(1);
		        for (JButton btn : roomButtons) {
		            if (btn.getText().equalsIgnoreCase(roomName)) {
		                btn.setEnabled(false);
		                btn.setBackground(Color.GRAY);
		            }
		        }
		    }

		    for (JButton btn : roomButtons) {
		        if (btn.isEnabled()) {
		            btn.addActionListener(e -> {
		                int option = JOptionPane.showConfirmDialog(MainPanel, "Are you sure to continue?","Confirmation", JOptionPane.YES_NO_OPTION);
		                if (option == JOptionPane.YES_OPTION) {
		                    MainPanel.setSelectedIndex(3);
		                    room.setText(btn.getText().toLowerCase());
		                    date_reservation.setText(formatted);
		                }
		            });
		        }
		    }
		};

		updateRooms.run();
		dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> updateRooms.run());
		
		

		
		
		MainPanel.addTab("Guest tab", null, Guest, null);
		Guest.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBounds(12, 155, 902, 692);
		Guest.add(panel);
		panel.setLayout(new BorderLayout());  // correct layout

		String[] columnNames = {
		    "Reservation ID", "First Name", "Last Name", "Age", "Gender",
		    "Room", "Address", "Contact No", "Payment", "Date", "Checkout"
		};

		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		tableReservation = new JTable(model);
		tableReservation.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(tableReservation);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

		panel.add(scrollPane, BorderLayout.CENTER);
		
		MainPanel.addTab("Payment tab", null, Payment, null);
		Payment.setLayout(null);
		searchBar.setBounds(475, 94, 281, 38);
		Payment.add(searchBar);
		searchBar.setColumns(10);
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search_data = searchBar.getText().toString();
				List<String> reserve_data = conn.getSearchData(search_data);
				System.out.println(reserve_data);
				
				if(reserve_data.isEmpty()) {
					JOptionPane.showMessageDialog(MainPanel,"No data Found");
				}
				
				firstname_payment.setText(reserve_data.get(1));
				lastname_payment.setText(reserve_data.get(2));
				age_payment.setText(reserve_data.get(3));
				gender_payment.setText(reserve_data.get(4));
				room_payment.setText(reserve_data.get(5));
				address_payment.setText(reserve_data.get(6));
				payment_payment.setText(reserve_data.get(8));
				date_payment.setText(reserve_data.get(9));
				contact_payment.setText(reserve_data.get(7));
				checkout_payment.setText(reserve_data.get(10));
				id_payment.setText(reserve_data.get(0));
				
				
				
			}
		});
		searchbutton.setBounds(786, 97, 105, 27);
		Payment.add(searchbutton);
		firstname_payment.setEditable(false);
		firstname_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		firstname_payment.setBounds(109, 291, 199, 38);
		Payment.add(firstname_payment);
		firstname_payment.setColumns(10);
		lastname_payment.setEditable(false);
		lastname_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		lastname_payment.setColumns(10);
		lastname_payment.setBounds(403, 291, 199, 38);
		
		Payment.add(lastname_payment);
		age_payment.setEditable(false);
		age_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		age_payment.setColumns(10);
		age_payment.setBounds(715, 291, 199, 38);
		
		Payment.add(age_payment);
		gender_payment.setEditable(false);
		gender_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		gender_payment.setColumns(10);
		gender_payment.setBounds(109, 408, 199, 38);
		
		Payment.add(gender_payment);
		room_payment.setEditable(false);
		room_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		room_payment.setColumns(10);
		room_payment.setBounds(403, 408, 199, 38);
		
		Payment.add(room_payment);
		address_payment.setEditable(false);
		address_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		address_payment.setColumns(10);
		address_payment.setBounds(715, 408, 199, 38);
		
		Payment.add(address_payment);
		payment_payment.setEditable(false);
		payment_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		payment_payment.setColumns(10);
		payment_payment.setBounds(109, 521, 199, 38);
		
		Payment.add(payment_payment);
		date_payment.setEditable(false);
		date_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		date_payment.setColumns(10);
		date_payment.setBounds(403, 521, 199, 38);
		
		Payment.add(date_payment);
		contact_payment.setEditable(false);
		contact_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		contact_payment.setColumns(10);
		contact_payment.setBounds(715, 521, 199, 38);
		
		Payment.add(contact_payment);
		checkout_payment.setEditable(false);
		checkout_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		checkout_payment.setColumns(10);
		checkout_payment.setBounds(238, 619, 199, 38);
		
		Payment.add(checkout_payment);
		id_payment.setEditable(false);
		id_payment.setFont(new Font("Dialog", Font.BOLD, 20));
		id_payment.setColumns(10);
		id_payment.setBounds(577, 619, 199, 38);
		
		Payment.add(id_payment);
		
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EditButton.getText() == "Edit") {
					EditButton.setText("Save");
					firstname_payment.setEditable(true);
					lastname_payment.setEditable(true);
					age_payment.setEditable(true);
					gender_payment.setEditable(true);
					address_payment.setEditable(true);
					contact_payment.setEditable(true);
				}else {
					EditButton.setText("Edit");
					firstname_payment.setEditable(false);
					lastname_payment.setEditable(false);
					age_payment.setEditable(false);
					gender_payment.setEditable(false);
					room_payment.setEditable(false);
					address_payment.setEditable(false);
					payment_payment.setEditable(false);
					date_payment.setEditable(false);
					contact_payment.setEditable(false);
					checkout_payment.setEditable(false);
					id_payment.setEditable(false);
//					JOptionPane.showMessageDialog(MainPanel,"Savingg.....");
					
					boolean success = conn.editReservationData(
			                id_payment.getText(),
			                firstname_payment.getText(),
			                lastname_payment.getText(),
			                age_payment.getText(),
			                gender_payment.getText(),
			                room_payment.getText(),
			                address_payment.getText(),
			                contact_payment.getText(),
			                payment_payment.getText(),
			                date_payment.getText(),
			                checkout_payment.getText()
			            );

			            if (success) {
			                JOptionPane.showMessageDialog(MainPanel, "Reservation updated successfully!");
			            } else {
			                JOptionPane.showMessageDialog(MainPanel, "Failed to update reservation.");
			            }
				}
			}
		});
		EditButton.setBounds(96, 185, 105, 27);
		
		Payment.add(EditButton);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option_result = JOptionPane.showConfirmDialog(MainPanel, "Are you sure to delete this data?","Confirmation", JOptionPane.YES_NO_OPTION);
				if (option_result == JOptionPane.YES_OPTION) {
					String existing = id_payment.getText().toString();
					if (existing.isEmpty()) {
						JOptionPane.showMessageDialog(MainPanel,"no data seletect");
					}
					else {
						conn.deleteReservationDate(id_payment.getText().toString());
						JOptionPane.showMessageDialog(MainPanel,"Delete data Success");
						firstname_payment.setText("");
						lastname_payment.setText("");
						age_payment.setText("");
						gender_payment.setText("");
						room_payment.setText("");
						address_payment.setText("");
						payment_payment.setText("");
						date_payment.setText("");
						contact_payment.setText("");
						checkout_payment.setText("");
						id_payment.setText("");
					}
	        		
        		}
				
			}
		});
		deleteButton.setBounds(96, 224, 105, 27);
		
		Payment.add(deleteButton);
		
		payment_for_reservation.setFont(new Font("Dialog", Font.BOLD, 20));
		payment_for_reservation.setColumns(10);
		payment_for_reservation.setBounds(403, 713, 199, 38);
		
		Payment.add(payment_for_reservation);
		btnPay.setBounds(456, 797, 105, 27);
		btnPay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            long expectedPayment = Long.parseLong(payment_payment.getText());
		            long enteredPayment = Long.parseLong(payment_for_reservation.getText());
		            System.out.println(expectedPayment+""+enteredPayment);

		            if (enteredPayment < expectedPayment) {
		                JOptionPane.showMessageDialog(MainPanel, "Payment is insufficient! Please pay the full amount.");
		                return;
		            }
		            boolean success = conn.processPayment(
		                id_payment.getText(),  
		                enteredPayment         
		            );

		            if (success) {
		                JOptionPane.showMessageDialog(MainPanel, "Payment successful!");
		                payment_for_reservation.setText("");
		                firstname_payment.setText("");
						lastname_payment.setText("");
						age_payment.setText("");
						gender_payment.setText("");
						room_payment.setText("");
						address_payment.setText("");
						payment_payment.setText("");
						date_payment.setText("");
						contact_payment.setText("");
						checkout_payment.setText("");
						id_payment.setText("");
//		                payment_payment.setText(String.valueOf(enteredPayment));
		            } else {
		                JOptionPane.showMessageDialog(MainPanel, "Failed to process payment. Try again.");
		            }

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(MainPanel, "Invalid payment amount. Please enter a number.");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(MainPanel, "An error occurred while processing payment.");
		        }
		    }
		});
		
		Payment.add(btnPay);
		
		
		Firstname_Label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_1.setBounds(12, 291, 101, 35);
		
		Payment.add(Firstname_Label_1);
		Lastname_Label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Lastname_Label_1.setBounds(314, 291, 101, 35);
		
		Payment.add(Lastname_Label_1);
		Age_Label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Age_Label_1.setBounds(675, 291, 101, 35);
		
		Payment.add(Age_Label_1);
		Firstname_Label_3_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_3_1.setBounds(25, 408, 101, 35);
		
		Payment.add(Firstname_Label_3_1);
		Firstname_Label_4_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_4_1.setBounds(350, 408, 101, 35);
		
		Payment.add(Firstname_Label_4_1);
		Firstname_Label_5_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_5_1.setBounds(644, 412, 101, 35);
		
		Payment.add(Firstname_Label_5_1);
		Firstname_Label_6_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_6_1.setBounds(617, 525, 101, 35);
		
		Payment.add(Firstname_Label_6_1);
		Firstname_Label_7_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_7_1.setBounds(25, 521, 101, 35);
		
		Payment.add(Firstname_Label_7_1);
		Firstname_Label_8_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_8_1.setBounds(336, 525, 101, 35);
		
		Payment.add(Firstname_Label_8_1);
		Firstname_Label_9_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_9_1.setBounds(154, 619, 101, 35);
		
		Payment.add(Firstname_Label_9_1);
		Firstname_Label_9_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_9_1_1.setBounds(455, 623, 116, 35);
		
		Payment.add(Firstname_Label_9_1_1);
		
		
		reservation.setLayout(null);
		MainPanel.addTab("New tab", null, reservation, null);
		
		JLabel Firstname_Label = new JLabel("First Name");
		Firstname_Label.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label.setBounds(23, 183, 101, 35);
		reservation.add(Firstname_Label);
		
		JLabel Lastname_Label = new JLabel("Last Name");
		Lastname_Label.setFont(new Font("Dialog", Font.BOLD, 15));
		Lastname_Label.setBounds(23, 241, 101, 35);
		reservation.add(Lastname_Label);
		
		JLabel Age_Label = new JLabel("Age");
		Age_Label.setFont(new Font("Dialog", Font.BOLD, 15));
		Age_Label.setBounds(23, 299, 101, 35);
		reservation.add(Age_Label);
		
		JLabel Firstname_Label_3 = new JLabel("Gender");
		Firstname_Label_3.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_3.setBounds(23, 359, 101, 35);
		reservation.add(Firstname_Label_3);
		
		JLabel Firstname_Label_4 = new JLabel("Room");
		Firstname_Label_4.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_4.setBounds(23, 423, 101, 35);
		reservation.add(Firstname_Label_4);
		
		JLabel Firstname_Label_5 = new JLabel("Address");
		Firstname_Label_5.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_5.setBounds(23, 485, 101, 35);
		reservation.add(Firstname_Label_5);
		
		JLabel Firstname_Label_6 = new JLabel("Contact No.");
		Firstname_Label_6.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_6.setBounds(23, 544, 101, 35);
		reservation.add(Firstname_Label_6);
		
		JLabel Firstname_Label_7 = new JLabel("Payment");
		Firstname_Label_7.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_7.setBounds(23, 603, 101, 35);
		reservation.add(Firstname_Label_7);
		
		JLabel Firstname_Label_8 = new JLabel("Date");
		Firstname_Label_8.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_8.setBounds(23, 660, 101, 35);
		reservation.add(Firstname_Label_8);
		
		JLabel Firstname_Label_9 = new JLabel("Checkout");
		Firstname_Label_9.setFont(new Font("Dialog", Font.BOLD, 15));
		Firstname_Label_9.setBounds(23, 718, 101, 35);
		reservation.add(Firstname_Label_9);
		
		
		first_name.setFont(new Font("Dialog", Font.BOLD, 22));
		first_name.setBounds(131, 183, 194, 35);
		reservation.add(first_name);
		first_name.setColumns(10);
		
		last_name.setFont(new Font("Dialog", Font.BOLD, 22));
		last_name.setColumns(10);
		last_name.setBounds(131, 241, 194, 35);
		reservation.add(last_name);
		
		age.setFont(new Font("Dialog", Font.BOLD, 22));
		age.setColumns(10);
		age.setBounds(131, 299, 194, 35);
		reservation.add(age);
		
		gender.setFont(new Font("Dialog", Font.BOLD, 22));
		gender.setColumns(10);
		gender.setBounds(131, 359, 194, 35);
		reservation.add(gender);
		
		room.setEditable(false);
		room.setFont(new Font("Dialog", Font.BOLD, 22));
		room.setColumns(10);
		room.setBounds(131, 423, 194, 35);
		reservation.add(room);
		
		address.setFont(new Font("Dialog", Font.BOLD, 22));
		address.setColumns(10);
		address.setBounds(131, 485, 194, 35);
		reservation.add(address);
		
		
		contact_no.setFont(new Font("Dialog", Font.BOLD, 22));
		contact_no.setColumns(10);
		contact_no.setBounds(131, 544, 194, 35);
		reservation.add(contact_no);
		
		payment.setFont(new Font("Dialog", Font.BOLD, 22));
		payment.setColumns(10);
		payment.setBounds(131, 603, 194, 35);
		reservation.add(payment);
		
		date_reservation.setEditable(false);
		date_reservation.setFont(new Font("Dialog", Font.BOLD, 22));
		date_reservation.setColumns(10);
		date_reservation.setBounds(131, 660, 194, 35);
		reservation.add(date_reservation);
		
		
		checkout_date.setBounds(131, 718, 194, 35);
		reservation.add(checkout_date);
		checkout_date.addPropertyChangeListener("date", evt -> {
		    if (checkout_date.getDate() != null && date_reservation.getText() != null && !date_reservation.getText().isEmpty()) {
		        calculatePayment();
		    }
		});
		
		
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.setSelectedIndex(0);
			}
		});
		Return.setBounds(809, 26, 105, 27);
		reservation.add(Return);
		
		
		
		
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.setBounds(755, 794, 132, 27);
		reservation.add(btnAddReservation);
		
		btnAddReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Random rand = new Random();
				    int num = rand.nextInt(900000000) + 100000000;
					String reservation_id_value = String.valueOf(num);
					String first_name_value = first_name.getText().toString();
					String last_name_value = last_name.getText().toString();
					String age_name_value = age.getText().toString();
					String gender_name_value = gender.getText().toString();
					String room_name_value = room.getText().toString();
					String address_name_value = address.getText().toString();
					String contact_name_value = contact_no.getText().toString();
					String payment_name_value = payment.getText().toString();
					String date_name_value = date_reservation.getText().toString();
					try {
						java.util.Date date = checkout_date.getDate();
					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    formatted_checkout = sdf.format(date);
					}
					catch(Exception eee) {
						JOptionPane.showMessageDialog(MainPanel,"check your date format");
					}
					
					
					System.out.println(reservation_id_value);
					System.out.println(first_name_value);
					System.out.println(last_name_value);
					System.out.println(age_name_value);
					System.out.println(gender_name_value);
					System.out.println(room_name_value);
					System.out.println(address_name_value);
					System.out.println(contact_name_value);
					System.out.println(payment_name_value);
					System.out.println(date_name_value);
					System.out.println(formatted_checkout);
					
					
					boolean added = conn.AddReservation(reservation_id_value, first_name_value, last_name_value, age_name_value, gender_name_value, room_name_value, address_name_value, contact_name_value, payment_name_value, date_name_value, formatted_checkout);
					System.out.println(added);
					if(added) {
						JOptionPane.showMessageDialog(MainPanel,"Added Reservation Success");
						refreshRooms();
						MainPanel.setSelectedIndex(0);
						first_name.setText("");
						last_name.setText("");
						age.setText("");
						gender.setText("");
						room.setText("");
						address.setText("");
						contact_no.setText("");
						payment.setText("");
						date_reservation.setText("");
					}else JOptionPane.showMessageDialog(MainPanel,"Error in adding Reservation");
					
					
					
				}
				catch(Exception ee){
					JOptionPane.showMessageDialog(MainPanel,"Check your inputs and try again");
				}
			}
		});
		
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login login = new Login();
				login.setVisible(true);
			}
		});

	}
	
	private void refreshRooms() {
	    java.util.Date date = dateChooser.getDate();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    formatted = sdf.format(date);

	    List<List<String>> reservations = conn.GetRoomData(formatted);

	    for (JButton btn : roomButtons) {
	        btn.setEnabled(true);
	        btn.setBackground(Color.WHITE);

	        for (ActionListener al : btn.getActionListeners()) {
	            btn.removeActionListener(al);
	        }

	        btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                MainPanel.setSelectedIndex(3);
	                room.setText(btn.getText().toLowerCase());
	                date_reservation.setText(formatted);
	            }
	        });
	    }

	    for (List<String> row : reservations) {
	        String roomName = row.get(1);
	        for (JButton btn : roomButtons) {
	            if (btn.getText().equalsIgnoreCase(roomName)) {
	                btn.setEnabled(false);
	                btn.setBackground(Color.GRAY);
	            }
	        }
	    }
	}
	private void calculatePayment() {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        Date checkInDate = sdf.parse(date_reservation.getText());
	        Date checkOutDate = checkout_date.getDate();

	        long diff = checkOutDate.getTime() - checkInDate.getTime();
	        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

	        if (days <= 0) {
	            days = 1;
	        }

	        long pricePerDay = 1000;
	        long total = days * pricePerDay;

	        payment.setText(String.valueOf(total));

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
