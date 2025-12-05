package hotel_reservation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel LoginPage;
	private JPanel Background;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		Database cursor = new Database();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 900);
		setLocationRelativeTo(null);
		
		LoginPage = new JPanel();
		LoginPage.setBackground(Color.GRAY);
		LoginPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPage);
		LoginPage.setLayout(null);
		
		Background = new JPanel();
		Background.setForeground(new Color(0, 0, 0));
		Background.setBackground(new Color(241, 241, 241));
		Background.setBounds(62, 65, 1151, 723);
		Background.setLayout(null);
		
		
		
		JLabel lblLuxestay = new JLabel("");
		JLabel continuetologin = new JLabel("");
		txtEmail = new JTextField();
		txtPassword = new JPasswordField();
		JButton LoginButton = new JButton("Login");
		
		
		continuetologin.setIcon(new ImageIcon("/home/berting/eclipse-workspaceitj/Hotel_Reservation/continuetologin.png"));
		continuetologin.setFont(new Font("Dialog", Font.BOLD, 20));
		continuetologin.setSize(new Dimension(20, 0));
		continuetologin.setBounds(353, 257, 341, 114);
		
		
		lblLuxestay.setIcon(new ImageIcon("/home/berting/eclipse-workspaceitj/Hotel_Reservation/luxestay.png"));
		lblLuxestay.setBounds(203, 23, 711, 320);
		
		
		txtEmail.setFont(new Font("Dialog", Font.BOLD, 22));
		txtEmail.setBorder(new LineBorder(new Color(24, 0, 173), 5, true));
		txtEmail.setBounds(353, 372, 449, 46);
		txtEmail.setColumns(10);
		
		txtEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();
				boolean LoginAdminExist = cursor.LoginAdmin(email,password);
				if(LoginAdminExist) {
					JOptionPane.showMessageDialog(getParent(), "You Enter correct inputs");
					Main_page frame = new Main_page();
					frame.setVisible(true);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(getParent(),"Please check your inputs and try again");
				}
			}
		});
		
		txtPassword.setFont(new Font("Dialog", Font.BOLD, 22));
		txtPassword.setBorder(new LineBorder(new Color(24, 0, 173), 5, true));
		txtPassword.setBounds(353, 457, 449, 46);
		
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();
				boolean LoginAdminExist = cursor.LoginAdmin(email,password);
				if(LoginAdminExist) {
					JOptionPane.showMessageDialog(getParent(), "Login Success");
					Main_page frame = new Main_page();
					frame.setVisible(true);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(getParent(),"Please check your inputs and try again");
				}
			}
		});
		
		LoginButton.setFont(new Font("Dialog", Font.BOLD, 25));
		LoginButton.setForeground(new Color(255, 255, 255));
		LoginButton.setBackground(new Color(24, 0, 173));
		LoginButton.setBorder(new LineBorder(new Color(24, 0, 173), 1, true));
		LoginButton.setBounds(520, 548, 117, 46);
		
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();
				boolean LoginAdminExist = cursor.LoginAdmin(email,password);
				if(LoginAdminExist) {
					JOptionPane.showMessageDialog(getParent(), "You Enter correct inputs");
					Main_page frame = new Main_page();
					frame.setVisible(true);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(getParent(),"Please check your inputs and try again");
				}
			}
		});
		
		
		
		
		
		
		
		LoginPage.add(Background);
		Background.add(continuetologin);
		Background.add(lblLuxestay);
		Background.add(txtEmail);
		Background.add(txtPassword);
		Background.add(LoginButton);
	}
}
