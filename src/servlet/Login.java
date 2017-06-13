package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String code = "";
		String message = "";
		
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		
		Connection connection = DBUtil.getConnect();
		try {
			Statement statement = (Statement)connection.createStatement();
			String sql = "select username from " + DBUtil.TABLE_USERINFO + " where username = '" + username + "' and password = '" + password + "'";
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				code = "200";
				message = "µÇÂ¼³É¹¦";
			} else {
				code = "201";
				message = "µÇÂ½Ê§°Ü£¬ÃÜÂë´íÎó»òÕËºÅÎ´×¢²á";
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("resCode", code);
		map.put("resMsg", message);
		
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
