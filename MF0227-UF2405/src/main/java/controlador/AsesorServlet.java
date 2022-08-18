package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.AsesorDAO;
import dao.AsesorDAOMySQL;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Asesor;


/**
 * Servlet implementation class AsesorServlet
 */
public class AsesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean coche,hogar,decesos;
	String nombre;
	String apellidos;
	String dni;
	String contrato;
	String seguro;
	String promedio;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsesorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		nombre = request.getParameter("nombre");
		apellidos = request.getParameter("apellidos");
		dni = request.getParameter("dni");
		contrato = request.getParameter("contrato");
		seguro = request.getParameter("seguro");
		promedio = request.getParameter("promedio");
		
		
		
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Datos</title>\r\n"
				+ "</head>\r\n"
				+ "<body>");
		
		out.println("<h1>Datos del asesor</h1>");
		out.println("<ul>");
		out.println("<li>Nombre: " + nombre + "</li>");
		out.println("<li>Apellidos: " + apellidos + "</li>");
		out.println("<li>DNI: " + dni + "</li>");
		out.println("<li>Relación contractual: " + contrato + "</li>");
		out.println("<li>Tipo de seguros: " + seguro + "</li>");
		out.println("<li>Num Ventas promedio: " + promedio + "</li>");
		
		out.println("</ul>");	
		out.println("</body>\r\n"
				+ "</html>");
		insertarAsesor(request,response);
	}
	private void insertarAsesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nombre = request.getParameter("nombre");
		apellidos = request.getParameter("apellidos");
		dni = request.getParameter("dni");
		contrato = request.getParameter("contrato");
		seguro = request.getParameter("seguro");
		promedio = request.getParameter("promedio");
		
		if (seguro=="Coche") {
			coche=true;hogar=false;decesos=false;
		} else if (seguro=="Hogar") {
			hogar=true;coche=false;decesos=false;
		} else {
			decesos=true;hogar=false;coche=false;
		}
		int prom = Integer.parseInt(promedio);
		
		Asesor a1 = new Asesor(dni, nombre, apellidos, contrato, coche, hogar, decesos, prom);
		AsesorDAO dao = new AsesorDAOMySQL();
			
		dao.insertarAsesor(a1); 
		AsesorDAO libroDAO = new AsesorDAOMySQL();
		List<Asesor> lista = libroDAO.getListaAsesores();
	}
		

	
}
