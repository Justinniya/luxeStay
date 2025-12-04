package hotel_reservation;

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
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Main_page extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel MainPage = new JPanel();
	public Database conn = new Database();
	public JPanel SideBar = new JPanel();
	public JLabel LuxeStay = new JLabel("");
	public JTabbedPane MainPanel = new JTabbedPane(JTabbedPane.TOP);
	public JButton guestButton = new JButton("GUEST");
	public JButton homeButtom = new JButton("HOME");
	public JButton paymentButton = new JButton("PAYMENT");
	public JButton logoutButton = new JButton("LOGOUT");
	public JPanel Home = new JPanel();
	public JPanel Guest = new JPanel();
	public JLabel lblGuest = new JLabel("Guest");
	public JPanel Payment = new JPanel();
	public JLabel lblPayment = new JLabel("Payment");
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
	public String room_name = "";
	public String formatted = "";
	public String formatted_checkout = "";
	public JButton Return = new JButton("Return");

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
		
		
		LuxeStay.setIcon(new ImageIcon("/home/berting/Pictures/Screenshots/Screenshot from 2025-12-03 20-32-04.png"));
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
		List<JButton> roomButtons = new ArrayList<>();
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

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBackground(new Color(24, 0, 173));
		dateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		dateChooser.setBounds(649, 122, 246, 43);
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setFont(new Font("Dialog", Font.BOLD, 20));
		dateChooser.setDate(new java.util.Date());
		Home.add(dateChooser);

		Runnable updateRooms = () -> {
		    java.util.Date date = dateChooser.getDate();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    formatted = sdf.format(date);
		    System.out.println(formatted);
		    
		    List<List<String>> reservations = conn.GetRoomData(formatted);

		    for (JButton btn : roomButtons) {
		        btn.setEnabled(true);
		        btn.setBackground(Color.WHITE);
		        btn.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
//		        		JOptionPane.showMessageDialog(MainPanel,"clicking "+ btn.getLabel().toLowerCase().toString() + formatted);
		        		MainPanel.setSelectedIndex(3);
		        		room.setText(btn.getLabel().toLowerCase().toString());
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
		};

		updateRooms.run();
		dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> updateRooms.run());

		
		
		MainPanel.addTab("Guest tab", null, Guest, null);
		Guest.setLayout(null);
		
		lblGuest.setBounds(93, 85, 60, 17);
		Guest.add(lblGuest);
		
		MainPanel.addTab("Payment tab", null, Payment, null);
		Payment.setLayout(null);
		
		lblPayment.setBounds(305, 246, 60, 17);
		Payment.add(lblPayment);
		
		
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
		
		JDateChooser checkout_date = new JDateChooser();
		checkout_date.setBounds(131, 718, 194, 35);
		reservation.add(checkout_date);
		
		
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
				String reservation_id_value = "213124512412";
				String first_name_value = first_name.getText().toString();
				String last_name_value = last_name.getText().toString();
				String age_name_value = age.getText().toString();
				String gender_name_value = gender.getText().toString();
				String room_name_value = room.getText().toString();
				String address_name_value = address.getText().toString();
				String contact_name_value = contact_no.getText().toString();
				String payment_name_value = payment.getText().toString();
				String date_name_value = date_reservation.getText().toString();
				java.util.Date date = checkout_date.getDate();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    formatted_checkout = sdf.format(date);
				
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
}
