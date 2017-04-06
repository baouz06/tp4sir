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

import fr.tp4sir.tp4sir.Home;
import fr.tp4sir.tp4sir.HybrideDevice;
import fr.tp4sir.tp4sir.ManagerSingleton;
import fr.tp4sir.tp4sir.Person;

/**
 * Servlet implementation class UserInfo
 */


@SuppressWarnings("serial")

@WebServlet(name="userinfo",urlPatterns = {"/UserInfo"})
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerSingleton ManagerSingleton;

	
    /**
     * Default constructor. 
     */
    public UserInfo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();

		tx.begin();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();

		out.println("<HTML>\n<BODY>\n");
		out.println("<H1>Recapitulatif des informations</H1>\n");
		out.println(resultList.size());

		for (Person p : resultList) {
			out.println("<UL>\n");
			out.println("<LI>Nom: " + p.getName() + "\n");
			out.println("<LI>Prenom: " + p.getFirstName() + "\n");
			out.println("<LI>Email: " + p.getMail() + "\n");
			out.println("</UL>\n");
		}
		out.println("</BODY></HTML>");
		System.out.println(".. Personnes affichées");
		tx.commit();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Person personne1 = new Person();
		personne1.setName(request.getParameter("name"));
		personne1.setFirstName(request.getParameter("firstname"));
		personne1.setMail(request.getParameter("mail"));

		Home h1 = new Home();
		h1.setNombre_de_piece(4);
		h1.setTaille(30);
		h1.setPerson_homme(personne1);
		manager.persist(personne1);
		manager.persist(h1);
		out.println("insertion enregistrée dans la base de données");
		tx.commit();
	}

}
