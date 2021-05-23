

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
 * Servlet implementation class Caratteristiche
 */
@WebServlet("/Caratteristiche")
public class Caratteristiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Caratteristiche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.sql.Connection con = null;
		ArrayList<ScuolaLista> scuola = new ArrayList<ScuolaLista>();	
		ArrayList<String> s= new ArrayList<String>();
		ArrayList<PosizioneMarker> posizioni = new ArrayList<PosizioneMarker>();
		ArrayList<PosizioneMarker> posizioninew = new ArrayList<PosizioneMarker>();

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/progetto","davidec","calcagno");
			
			if(request.getParameter("regioni") != null) {
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT REGIONE FROM progetto.informazioni ORDER BY REGIONE;");
				while (rs.next()) {
					//response.getWriter().append(rs.getString(1)).append("\r");
					s.add(rs.getString(1));
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
				
			}
			
			if(request.getParameter("provincie") != null) {
				Statement stmt=con.createStatement(); 
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT PROVINCIA FROM progetto.informazioni WHERE REGIONE LIKE '%"+ request.getParameter("regione") +"%' ORDER BY PROVINCIA;");
				while(rs.next()) {
				//response.getWriter().append(rs.getString(1)).append("\r");
				s.add(rs.getString(1));
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			if(request.getParameter("comuni") != null) {
				Statement stmt=con.createStatement();  
				String provincia;
				provincia = request.getParameter("provincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				System.out.println("SELECT DISTINCT COMUNE FROM progetto.informazioni WHERE PROVINCIA LIKE '%"+ provincia +"%' ORDER BY COMUNE;");
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT COMUNE FROM progetto.informazioni WHERE PROVINCIA LIKE '%"+ provincia +"%' ORDER BY COMUNE;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
						//response.getWriter().append(rs.getString(1)).append("\r");
						s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			if(request.getParameter("tipologiecomune") != null) {
				Statement stmt=con.createStatement(); 
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT TIPOLOGIASCUOLA FROM progetto.informazioni WHERE COMUNE LIKE '%" + request.getParameter("tipologiecomune") + "%';");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
				
			}
			
			if(request.getParameter("materietipologia") != null){
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT disciplina FROM progetto.discipline WHERE codicescuola IN (select codicescuola from informazioni where TIPOLOGIASCUOLA LIKE '%" + request.getParameter("materietipologia") + "%') ORDER BY disciplina;");
				System.out.println("SELECT DISTINCT disciplina FROM progetto.discipline WHERE codicescuola IN (select codicescuola from informazioni where TIPOLOGIASCUOLA LIKE '%" + request.getParameter("materietipologia") + "%') ORDER BY disciplina;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			if(request.getParameter("tipologiamateria") != null){
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT TIPOLOGIASCUOLA FROM progetto.informazioni WHERE codicescuola IN (select codicescuola from discipline where disciplina LIKE '%" + request.getParameter("tipologiamateria") + "%') ORDER BY TIPOLOGIASCUOLA;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			
			
			
			if(request.getParameter("tipologie") != null) {
				Statement stmt=con.createStatement(); 
				String provincia;
				provincia = request.getParameter("provincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT TIPOLOGIASCUOLA FROM progetto.informazioni WHERE PROVINCIA LIKE '%" + provincia + "%';");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
				
			}
			
			if(request.getParameter("materiecomune") != null){
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT disciplina FROM progetto.discipline WHERE codicescuola IN (select codicescuola from informazioni where comune LIKE '%"+ request.getParameter("materiecomune") + "%') ORDER BY disciplina;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			if(request.getParameter("materieprovincia") != null){
				Statement stmt=con.createStatement(); 
				String provincia;
				provincia = request.getParameter("materieprovincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT disciplina FROM progetto.discipline WHERE codicescuola IN (select codicescuola from informazioni where PROVINCIA LIKE '%"+ provincia + "%') ORDER BY disciplina;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			
			if(request.getParameter("materie") != null){
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT disciplina FROM progetto.discipline ORDER BY disciplina;");
				while(rs.next()) {
					if(rs.getString(1) != null) {
					s.add(rs.getString(1));
					}
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(s));
				
			}
			
			if(request.getParameter("a") != null) {
				//provincia
				Statement stmt=con.createStatement();
				String provincia;
				provincia = request.getParameter("provincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				System.out.println("SELECT DISTINCT * FROM progetto.informazioni WHERE PROVINCIA LIKE '%"+ provincia +  "%' ORDER BY NOMESCUOLA;");
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT * FROM progetto.informazioni WHERE PROVINCIA LIKE '%"+ provincia +  "%' ORDER BY NOMESCUOLA;");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //10 è comune
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
				
				
				
			}
			if(request.getParameter("b") != null) {
				//comune
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT * FROM progetto.informazioni WHERE COMUNE LIKE '%"+ request.getParameter("comune") +  "%' ORDER BY NOMESCUOLA;");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(6)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
			}
			if(request.getParameter("c") != null) {
				//materia + provincia
				String provincia;
				provincia = request.getParameter("provincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				Statement stmt=con.createStatement();
				String p = "SELECT codicescuola FROM progetto.discipline WHERE disciplina LIKE %'"+ request.getParameter("disciplina") +  "%';";
				System.out.println(p);
				ResultSet rs=stmt.executeQuery("SELECT * FROM informazioni WHERE codicescuola IN \n" + 
						"    (SELECT codicescuola FROM discipline\n" + 
						"     WHERE (disciplina LIKE '%" + request.getParameter("disciplina") +  "%') AND PROVINCIA LIKE '%"+ provincia + "%');");
				
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
					
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola)); 
				
			}
			if(request.getParameter("d") != null) {
				//tipologia + provincia
				String provincia;
				provincia = request.getParameter("provincia");
				if(provincia.equals("L'AQUILA")) {
					provincia = "AQUILA";
				}
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT DISTINCT * FROM progetto.informazioni WHERE TIPOLOGIASCUOLA LIKE '%"+ request.getParameter("tipologia") +  "%' AND PROVINCIA LIKE '%" + provincia + "%' ORDER BY NOMESCUOLA;");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
			}
			if(request.getParameter("e") != null) {
				//comune + materia
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT * FROM informazioni WHERE codicescuola IN \n" + 
						"    (SELECT codicescuola FROM discipline\n" + 
						"     WHERE (disciplina LIKE '%" + request.getParameter("disciplina") + "%')) AND COMUNE LIKE '%" + request.getParameter("comune") + "%';");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
			}
			if(request.getParameter("f") != null) {
				//comune + tipologia
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT * FROM informazioni WHERE COMUNE LIKE '%" + request.getParameter("comune") + "%' AND TIPOLOGIASCUOLA LIKE '%"+ request.getParameter("tipologia") +"%';");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
			}
			if(request.getParameter("g") != null) {
				//materia + tipologia
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT * FROM informazioni WHERE codicescuola IN \n" + 
						"    (SELECT codicescuola FROM discipline\n" + 
						"     WHERE (disciplina LIKE '%"+ request.getParameter("disciplina") +"%')) AND TIPOLOGIASCUOLA LIKE '%" + request.getParameter("tipologia") + "%';");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
			}
			if(request.getParameter("h") != null) {
				//materia + tipologia + comune
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT * FROM informazioni WHERE codicescuola IN \n" + 
						"    (SELECT codicescuola FROM discipline\n" + 
						"     WHERE (disciplina LIKE '%"+ request.getParameter("disciplina") + "%')) AND TIPOLOGIASCUOLA LIKE '%" + request.getParameter("tipologia") + "%' AND COMUNE LIKE '%"+ request.getParameter("comune") + "%';");
				while(rs.next()) {
					String prova;
					prova = rs.getString(4);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");	
					ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(10)); //6 è tipologia
					scuola.add(scuolalista);
				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(scuola));
				
				
			}
			
			
			


		} catch (ClassNotFoundException e) {
			System.out.println("non trovata classe");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("distanza") != null) {
			double lat1 =  Double.parseDouble((request.getParameter("lat1")));
			double lon1 =  Double.parseDouble((request.getParameter("lon1")));

			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT codicescuola, nomescuola, indirizzo, longitudine, latitudine FROM informazioni WHERE longitudine != '-100.445882';");
				while(rs.next()) {
					String prova;
					prova = rs.getString(2);
					prova = prova.replaceAll("\"", "");
					prova = prova.replaceAll("\"\"", "");
					prova = prova.replaceAll("\"\"\"", "");
					prova = prova.replaceAll("'", "");
					prova = prova.replaceAll("\\\\", "");
					PosizioneMarker posizionemarker = new PosizioneMarker(rs.getString(1), prova, rs.getString(3), rs.getString(4), rs.getString(5));
					posizioni.add(posizionemarker);
				}
				for(int f=0; f < posizioni.size(); f++) {
					if((posizioni.get(f).getLatitudine() != null) && (posizioni.get(f).getLongitudine() != null)) {
						if(!(posizioni.get(f).getLongitudine().toString().equals("-100.445882"))){
							double lat2 =  Double.parseDouble(posizioni.get(f).getLatitudine());
							double lon2 =  Double.parseDouble((posizioni.get(f).getLongitudine()));
							double distanza = distance(lat1, lat2, lon1, lon2, 0, 0);
							if(distanza < 5000) {
								System.out.println("distanza" + distanza);
								posizioninew.add(posizioni.get(f));
							}
						}
					} 
					
					

				}
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(posizioninew));
				System.out.println("scuole nelle vicinanze" + posizioninew.size());
				con.close();
		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			
		}
	

		
		
		
	}
	
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
