package servlet;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
	public static final String TABLE_USERINFO = "user_info";
	public static final String TABLE_FILMLIST = "film_list";
	
	public static Connection getConnect() {
		String url = "jdbc:mysql://localhost:3306/foe_ticket?useUnicode=true&characterEncoding=utf-8&userSSL=false";
		Connection connector = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connector = (Connection)DriverManager.getConnection(url, "root", "5201314");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("SQLExeption: " + e.getMessage());
			System.out.print("SQLState: " + e.getSQLState());
			System.out.print("VendorError: " + e.getErrorCode());
		}
		return connector;
	}
}
