package hotel_reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/luxestay";
    private static final String USER = "root";   
    private static final String PASS = "";       

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean LoginAdmin(String email,String password) {
    	try {
    		Connection conn = Database.connect();
        	
        	String login_query = "SELECT * FROM user WHERE email=? AND password=?";
        	PreparedStatement pst = conn.prepareStatement(login_query);
        	
        	pst.setString(1, email);
        	pst.setString(2,password);
        	
        	ResultSet rs = pst.executeQuery();
        	return rs.next();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
            return false;
    	}
    	
    }
    
    public List<List<String>> GetRoomData(String date) {
    	List<List<String>> reservations = new ArrayList<>();
    	try {
    		
    		Connection conn = Database.connect();

    		String login_query = "SELECT * FROM room_reservation WHERE date=?";
    		PreparedStatement pst = conn.prepareStatement(login_query);

    		pst.setString(1, date);

    		ResultSet rs = pst.executeQuery();
    		while (rs.next()) {
    		    List<String> data = new ArrayList<>();

    		    String room_id = rs.getString("room_id");
    		    String room_name = rs.getString("room_name");
    		    int reserve_id = rs.getInt("reserve_id");
    		    Date date_record = rs.getDate("date");

    		    data.add(String.valueOf(room_id));
    		    data.add(room_name);
    		    data.add(String.valueOf(reserve_id));
    		    data.add(date_record.toString());

    		    reservations.add(data);
    		}

    		
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return reservations;
    }
}
