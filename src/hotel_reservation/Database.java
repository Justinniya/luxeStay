package hotel_reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

    		String query = "SELECT * FROM room_reservation WHERE ? BETWEEN date AND checkout";
    		PreparedStatement pst = conn.prepareStatement(query);

    		pst.setString(1, date);

    		ResultSet rs = pst.executeQuery();
    		while (rs.next()) {
    		    List<String> data = new ArrayList<>();

    		    String room_id = rs.getString("room_id");
    		    String room_name = rs.getString("room_name");
    		    int reserve_id = rs.getInt("reserve_id");
    		    Date date_record = rs.getDate("date");
    		    Date date_checkout = rs.getDate("checkout");

    		    data.add(String.valueOf(room_id));
    		    data.add(room_name);
    		    data.add(String.valueOf(reserve_id));
    		    data.add(date_record.toString());
    		    data.add(date_checkout.toString());

    		    reservations.add(data);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return reservations;
    }
    
    public boolean AddReservation(String reservation_id,String first_name,String last_name,String age,String gender	,String room,String address	,String contact_no,String payment,String date,String checkout  ) {
    	Connection conn = Database.connect();

        try {
            conn.setAutoCommit(false);

            String add_date = "INSERT INTO reservation_data VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(add_date);
            pst.setString(1, reservation_id);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, age);
            pst.setString(5, gender);
            pst.setString(6, room);
            pst.setString(7, address);
            pst.setString(8, contact_no);
            pst.setString(9, payment);
            pst.setString(10, date);
            pst.setString(11, checkout);
            pst.executeUpdate();

            String add_room = "INSERT INTO room_reservation VALUES (?,?,?,?,?,?)";
            PreparedStatement pst1 = conn.prepareStatement(add_room);

            String[] split_room = room.split(" ");
            String room_id = split_room[1]+"-"+date;

            pst1.setString(1, room_id);
            pst1.setString(2, room);
            pst1.setString(3, reservation_id);
            pst1.setString(4, date);
            pst1.setString(5, checkout);
            pst1.setInt(6, 0);
            pst1.executeUpdate();

            conn.commit();

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null,
                    "Error. Transaction Rolled Back",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);

        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
		return true;
    }
    
    public List<String> getSearchData(String reservationId) {
        List<String> reservationData = new ArrayList<>();
        try {
            Connection conn = Database.connect();
            String query = "SELECT * FROM reservation_data WHERE reservation_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, reservationId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                reservationData.add(rs.getString("reservation_id"));
                reservationData.add(rs.getString("first_name"));
                reservationData.add(rs.getString("last_name"));
                reservationData.add(rs.getString("age"));
                reservationData.add(rs.getString("gender"));
                reservationData.add(rs.getString("room"));
                reservationData.add(rs.getString("address"));
                reservationData.add(rs.getString("contact_no"));
                reservationData.add(String.valueOf(rs.getInt("payment")));
                reservationData.add(rs.getDate("date").toString());
                reservationData.add(rs.getDate("checkout").toString());
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservationData;
    }
    
    public boolean deleteReservationDate(String reservationId) {

        Connection conn = Database.connect();
        boolean success = false;

        try {
            conn.setAutoCommit(false);

            String deleteReservation = "DELETE FROM reservation_data WHERE reservation_id = ?";
            PreparedStatement pst = conn.prepareStatement(deleteReservation);
            pst.setString(1, reservationId);
            int res1 = pst.executeUpdate();

            String deleteRoomRecord = "DELETE FROM room_reservation WHERE reserve_id = ?";
            PreparedStatement pst1 = conn.prepareStatement(deleteRoomRecord);
            pst1.setString(1, reservationId);
            int res2 = pst1.executeUpdate();

            if (res1 > 0 && res2 > 0) {
                conn.commit();
                success = true;
            } else {
                conn.rollback();
            }

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null,
                    "Error. Transaction Rolled Back",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);

        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
//                ex.printStackTrace();
            }
        }

        return success;
    }
    
    public boolean editReservationData(String reservation_id,String first_name,String last_name,String age,String gender	,String room,String address	,String contact_no,String payment,String date,String checkout) {
    	boolean success = false;
        String query = "UPDATE reservation_data SET first_name=?, last_name=?, age=?, gender=?, room=?, address=?, contact_no=?, payment=?, date=?, checkout=? WHERE reservation_id=?";

        try (Connection conn = Database.connect();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, first_name);
            pst.setString(2, last_name);
            pst.setString(3, age);
            pst.setString(4, gender);
            pst.setString(5, room);
            pst.setString(6, address);
            pst.setString(7, contact_no);
            pst.setString(8, payment);
            pst.setString(9, date);
            pst.setString(10, checkout);
            pst.setString(11, reservation_id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
    
    public List<List<String>> getReservationData() {
        List<List<String>> reservationData = new ArrayList<>();

        try (Connection conn = Database.connect();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM reservation_data");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                List<String> data = new ArrayList<>();

                data.add(rs.getString("reservation_id"));
                data.add(rs.getString("first_name"));
                data.add(rs.getString("last_name"));
                data.add(rs.getString("age"));
                data.add(rs.getString("gender"));
                data.add(rs.getString("room"));
                data.add(rs.getString("address"));
                data.add(rs.getString("contact_no"));
                data.add(String.valueOf(rs.getInt("payment")));
                data.add(String.valueOf(rs.getDate("date")));
                data.add(String.valueOf(rs.getDate("checkout")));

                reservationData.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("Calling .." + reservationData);
        return reservationData;
    }
    
    public boolean processPayment(String reserveId, long amount) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = Database.connect();
            String query = "UPDATE room_reservation SET paid=? WHERE reserve_id=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, (int) amount); 
            pst.setString(2, reserveId);

            int rowsAffected = pst.executeUpdate();
            success = rowsAffected > 0;
            
            String deleteReservation = "DELETE FROM reservation_data WHERE reservation_id = ?";
            PreparedStatement pst1 = conn.prepareStatement(deleteReservation);
            pst1.setString(1, reserveId);
            int res1 = pst1.executeUpdate();

            String deleteRoomRecord = "DELETE FROM room_reservation WHERE reserve_id = ?";
            PreparedStatement pst2 = conn.prepareStatement(deleteRoomRecord);
            pst2.setString(1, reserveId);
            int res2 = pst2.executeUpdate();

            if (res1 > 0 && res2 > 0) {
                conn.commit();
                success = true;
            } else {
                conn.rollback();
            }


        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        return success;
    }



}
