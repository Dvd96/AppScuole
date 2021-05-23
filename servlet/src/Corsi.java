

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Corsi
 */
@WebServlet("/Corsi")
public class Corsi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Corsi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<String> s= new ArrayList<String>();

		if(request.getParameter("codicescuola") != null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/progetto","davidec","calcagno");
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT combinazione FROM progetto.discipline WHERE CODICESCUOLA=" + "'" + request.getParameter("codicescuola")+ "'" + ";");
				while(rs.next()) {
					s.add(rs.getString(1));
				}
					con.close();  
					Gson gson = new Gson();
					response.getWriter().append(gson.toJson(s));

			} catch (ClassNotFoundException e) {
				System.out.println("non trovata classe");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		
		
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
