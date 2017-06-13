package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.log.LogUtils;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(
		description = "regist foe ticket", 
		urlPatterns = { 
				"/Regist"
		}, 
		initParams = { 
				@WebInitParam(name = "UserName", value = "abc", description = "username")
		})


public class RegistFOE extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistFOE() {
        // TODO Auto-generated constructor stub
    	//LogUtil.log("RegisterSerlet construct...");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String phone = request.getParameter("Phone");
		System.out.print("Username: " + username + "\nPassword: " + password + "\n" + "\nPhone: " + phone + "\n");
	
		
		String resCode = "";
		String resMsg = "";

		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement)connect.createStatement();
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_USERINFO + " where username = '" + username + " '";
			
			result = statement.executeQuery(sqlQuery);
			
			if (result.next()) {
				resCode = "201";
				resMsg = "该账号已注册, 请使用此账号登录或使用其他账号注册";
			} else {
				String sqlInsertPass = "insert into " + DBUtil.TABLE_USERINFO + "(username, password, phone) values('" + username + "','" + password + "','" + phone + "')";
				int row1 = statement.executeUpdate(sqlInsertPass);
				if (row1 == 1) {
					resCode = "200";
					resMsg = "注册成功";
				} else {
					resCode = "202";
					resMsg = "用户信息表插入错误";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("resCode", resCode);
		map.put("resMsg", resMsg);
		
		PrintWriter pw = response.getWriter();
		pw.println(map.toString());
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
