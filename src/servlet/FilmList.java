package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class FilmList
 */
@WebServlet("/FilmList")
public class FilmList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Connection connection = DBUtil.getConnect();
		StringBuilder rs = new StringBuilder();
		
		try {
			Statement statement = (Statement)connection.createStatement();
			String sql = "select * from " + DBUtil.TABLE_FILMLIST;
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				rs.append("name=");
				rs.append(result.getString(1));
				rs.append("introduce=");
				rs.append(result.getString(2));
				rs.append("actor=");
				rs.append(result.getString(3));
				rs.append("filmPic=");
				rs.append(result.getString(4));
				rs.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		PrintWriter pw = response.getWriter();
		pw.println(rs.toString());
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
