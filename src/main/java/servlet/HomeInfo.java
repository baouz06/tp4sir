package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tp4sir.tp4sir.*;

import java.util.Collection;


/**
 * Servlet implementation class HomeInfo
 */

@WebServlet(name="homeinfo",urlPatterns = {"/HomeInfo"})
public class HomeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerSingleton ManagerSingleton;

	/**
	 * Default constructor.
	 */
	public HomeInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		Collection<Home> result = manager.createQuery("Select h From Home h", Home.class).getResultList();

		out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations sur les maisons</H1>\n" + "<UL>\n");
		for (Home h : result) {
			out.println("<LI> maison : " + h+ "\n");
			
		}
		out.println("</UL>\n" + "</BODY></HTML>");
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n" +			
				" <LI>piece: "
				+ request.getParameter("piece") + "\n" +
				" <LI>taille : "
				+ request.getParameter("taille") + "\n" +
				" <LI>person : "
				+ request.getParameter("person") + "\n" +
				"</UL>\n");

		
		
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		Home home = new Home();
		home.setNombre_de_piece(Long.valueOf(request.getParameter("piece")));
		home.setTaille(Long.valueOf(request.getParameter("taille")));
		manager.persist(home);

		tx.commit();
		out.println("Enregistrement effectué");
	}

}
