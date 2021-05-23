

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

import com.google.gson.Gson;
import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Main() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("codicescuola"));
		Scuola scuola = null;
		if(request.getParameter("codicescuola") != null)
		try {
			System.out.println("ho eseguito query sul database");
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/progetto","davidec","calcagno");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT * FROM progetto.informazioni WHERE CODICESCUOLA=" + "'" + request.getParameter("codicescuola")+ "'" + ";");
			while(rs.next()) { 
				String prova;
				prova = rs.getString(4);
				prova = prova.replaceAll("\"", "");
				prova = prova.replaceAll("\"\"", "");
				prova = prova.replaceAll("\"\"\"", "");
				prova = prova.replaceAll("'", "");
				prova = prova.replaceAll("\\\\", "");
				String prova2 = rs.getString(7);
				prova2 = prova2.replaceAll("\r", "");
				scuola = new Scuola(rs.getString(1),rs.getString(2),rs.getString(3),prova,rs.getString(5),rs.getString(6),prova2,rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
			}
				con.close();  
				Gson gson = new Gson();
				System.out.println(gson.toJson(scuola));
				String json = gson.toJson(scuola);
				json = json.replace("\r", "");
				response.getWriter().append(json);

		} catch (ClassNotFoundException e) {
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
