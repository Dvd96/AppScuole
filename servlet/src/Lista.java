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
* Servlet implementation class Lista
*/
@WebServlet("/Lista")
public class Lista extends HttpServlet {
private static final long serialVersionUID = 1L;

/**
* @see HttpServlet#HttpServlet()
*/
public Lista() {
super();
// TODO Auto-generated constructor stub
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ArrayList<ScuolaLista> scuola = new ArrayList<ScuolaLista>();

// TODO Auto-generated method stub
try {
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con=DriverManager.getConnection( 
"jdbc:mysql://localhost:3306/progetto","davidec","calcagno");
Statement stmt=con.createStatement(); 
if(request.getParameter("nome") != null) {
ResultSet rs=stmt.executeQuery("SELECT DISTINCT * FROM progetto.informazioni WHERE NOMESCUOLA LIKE '%" + request.getParameter("nome")+ "%' ORDER BY NOMESCUOLA;");
while(rs.next()) { 
String prova;
prova = rs.getString(4);
prova = prova.replaceAll("\"", "");
prova = prova.replaceAll("\"\"", "");
prova = prova.replaceAll("\"\"\"", "");
prova = prova.replaceAll("'", "");
prova = prova.replaceAll("\\\\", "");	
ScuolaLista scuolalista = new ScuolaLista(rs.getString(1), prova,rs.getString(3));
scuola.add(scuolalista);
}
con.close(); 
Gson gson = new Gson();
System.out.println(gson.toJson(scuola));
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



}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}

}