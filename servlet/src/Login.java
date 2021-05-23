

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
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
		if((request.getParameter("username") != null) && (request.getParameter("password") != null))
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Utenti","davidec","calcagno");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT * FROM Utenti.utenti WHERE username='" + request.getParameter("username")+ "'" + "AND password='" + request.getParameter("password") + "';");
			rs.last();
			if(request.getParameter("accesso").equals("a")) {
			if(rs.getRow() == 1) {
				response.getWriter().append("true");
			} else {
				response.getWriter().append("false");
			}
			
			}
			
			
			if(request.getParameter("accesso").equals("r")){
				Statement stmt1=con.createStatement();  
				ResultSet rs1=stmt1.executeQuery("SELECT * FROM Utenti.utenti WHERE username='" + request.getParameter("username")+ "'" + ";");
				rs1.last();
				if(rs1.getRow() == 1) {
					response.getWriter().append("Mi dispiace, l'utente già esiste");
					} else {
						String query = " insert into utenti (username, password)"
				    	        + " values (?, ?)";
						PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
						preparedStmt.setString (1, request.getParameter("username"));
						preparedStmt.setString (2, request.getParameter("password"));
						preparedStmt.execute();
				}
				
				
			}
			
			con.close();
			
	}  catch (ClassNotFoundException e) {
		System.out.println("non trovata classe");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
