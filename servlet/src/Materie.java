

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
 * Servlet implementation class Materie
 */
@WebServlet("/Materie")
public class Materie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Materie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prova="";
		System.out.println("ciao");
		ArrayList<String> indirizzoscuola = new ArrayList<String>();
		ArrayList<String> materiascuola = new ArrayList<String>();
		ArrayList<String> materie= new ArrayList<String>();
		ArrayList<Indirizzomat> indirizzi = new ArrayList<Indirizzomat>();
		Indirizzomat indirizzomat = null;
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/progetto","davidec","calcagno");
			Statement stmt=con.createStatement();  
			if(request.getParameter("codicescuola") != null) {
				ResultSet rs=stmt.executeQuery("SELECT REGIONE FROM progetto.informazioni WHERE CODICESCUOLA='" + request.getParameter("codicescuola")+ "'" + ";");
				System.out.println("SELECT REGIONE FROM progetto.informazioni WHERE CODICESCUOLA='" + request.getParameter("codicescuola")+ "'" + ";");
				if(rs.next()) {
					prova = rs.getString(1);
				
				}
				
					ResultSet rs2=stmt.executeQuery("SELECT DISTINCT combinazione FROM progetto.discipline WHERE CODICESCUOLA='" + request.getParameter("codicescuola")+ "' ORDER BY combinazione" + ";");
					System.out.println("SELECT DISTINCT combinazione FROM progetto.discipline WHERE CODICESCUOLA='" + request.getParameter("codicescuola")+ "' ORDER BY combinazione" + ";");
					int contator = 0;
					while(rs2.next()) {
						indirizzoscuola.add(rs2.getString(1));
						Indirizzomat ind = new Indirizzomat();
						ind.setIndirizzomateria(indirizzoscuola.get(contator));
						indirizzi.add(ind);
						contator++;
					}
				
				
				int stampa=0;
				int st=0;
				while(stampa < indirizzi.size()) {
					String rimpiazzo = indirizzi.get(stampa).getIndirizzomateria();
					rimpiazzo = rimpiazzo.replaceAll("'", "");
					System.out.println("SELECT DISTINCT disciplina FROM progetto.discipline WHERE combinazione LIKE '%" +  rimpiazzo + "%' ORDER BY disciplina" + ";");
					ResultSet rs3=stmt.executeQuery("SELECT DISTINCT disciplina FROM progetto.discipline WHERE combinazione LIKE '%" + rimpiazzo + "%' ORDER BY disciplina" + ";");
					while(rs3.next()) {
 						materie.add(rs3.getString(1));
 						materie.set(st, materie.get(st).replaceAll("\r",""));
 						st++;
 					}
					st =0;
					Indirizzomat ind = new Indirizzomat();
					ind.setIndirizzomateria(indirizzi.get(stampa).getIndirizzomateria());
					ind.setMaterie(materie);
					indirizzi.set(stampa, ind);
					materie = new ArrayList<String>();
					stampa++;
				}
				//System.out.println(indirizzi);
				Gson gson = new Gson();				
				con.close();  
				if(indirizzi.size() == 0) {
					response.getWriter().append("a");
				
				} else {
					response.getWriter().append(gson.toJson(indirizzi));
					System.out.println(gson.toJson(indirizzi));
				}
				//response.getWriter().append(materiascuola.toString());
			}

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
