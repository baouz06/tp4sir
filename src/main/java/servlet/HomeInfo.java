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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String query = "select p from Person as p";
		
		EntityManagerFactory manager = Persistence.createEntityManagerFactory("hello");
				
		Collection<Person> result = manager.createEntityManager().createQuery(query).getResultList();
	
		out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations sur les maisons</H1>\n" + "<UL>\n");
		for (Object p : result) {
			out.println("<LI> enregistrement : " + p + "\n");
			out.println("coucou");
		}
		out.println("</UL>\n" + "</BODY></HTML>");
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		
		

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		
		try {
			//creation of person
			Person personne = new Person();
			  personne.setNom(request.getParameter("piece"));
	          personne.setPrenom(request.getParameter("piece"));
	          personne.setUnmail(request.getParameter("piece"));
	          //creation of Home
	          Home home = new Home();
	          home.setNombre_de_piece(2);
	          home.setTaille(50);
	          home.setPerson_homme(personne);
	          manager.persist(personne);
	          manager.persist(home);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		out.println("Enregistrement effectué");
	}

}
