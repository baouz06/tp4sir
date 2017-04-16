# TP 4 de Systèmes d'informations répartis

## Objective

1. Comprendre les mécanismes des Servlet
2. Réaliser une application Web en utilisant Combinant JPA et les Servlet
3. Comprendre les principes d’une architecture Rest
4. Comprendre les bénéfices d’un framework comme Jersey

## Sujet 

L’objectif de ce projet est de continuer le développement d’une application type
réseau social permettant de comparer sa consommation électrique avec ses amis, ses voisins,etc. dans la lignée de opower.

## Etape 1 Chargement des dépendances

Tout d’abord, nous avons modifié notre fichier pom.xml pour ajouter les dépendances nécessaires

```
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.0.1</version>
	<scope>provided</scope>
</dependency>
```
## Insertion et visualisation dés données en utilisant les  Servlets

Nous avons crée 2 formulaires:

### myfrom.html 

```
<div>
	<FORM Method="POST" Action="/UserInfo">
		Name : <INPUT type=text size=20 name=name><BR> 
		Firstname: <INPUT type=text size=20 name=firstname><BR> 
		mail : <INPUT type=text size=2 name=mail><BR> 
		<INPUT type=submit value=Send>
	</FORM>
</div>
<div>
	<FORM Method="GET" Action="/UserInfo">
		<INPUT type=submit value=visualiser>
	</FORM>
</div>
```
Comme nous le voyant, on peut soit visualiser toutes  les informations concernant les  personnes enregistrées (GET) ou bien ajouter des personnes à la base de données (POST).

### home.html
```
<div>
  <FORM Method="POST" Action="/HomeInfo" Name="Form1">
		nombre de  pieces: <INPUT type=text size=20 name=piece><BR> 
		taille: <INPUT type=float size=20 name=taille><BR>
		personne : <INPUT type=text size=2 name=person><BR> 
		<INPUT type=submit value=Send>
	</FORM>
</div>
<div>
 <form Method="GET" Action="/HomeInfo">
	<INPUT type=submit value=afficher>
 </form>
</div>	
```
Même principe pour ce qui concerne les maisons.

## Création des Servlets
Précédemment, nous avons mis  «/UserInfo » et « /HomeInfo » comme action des formulaires. Ces deux url référencie  l’url de notre  servlet soit en GET ou bien en POST, les servlets jouent un rôle de contrôleurs dans notre application 

Donc nous avons crée une servlet pour chaque formulaire comme ceci:

### HomeInfo.java - UserInfo.java

Dans la partie doGet,

on crée une variable de type collection pour récupérer et afficher les données qui sont dans notre base
```
Collection<Home> result = manager.createQuery("Select h From Home h", Home.class).getResultList();
    out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations sur les maisons</H1>\n" + "<UL>\n");
		for (Home h : result) {
		out.println("<LI> maison : " + h+ "\n");	
		}
		out.println("</UL>\n" + "</BODY></HTML>");
```

Dans la partie doPost,

Ici on crée une maison à partir de données envoyées dans le formulaire home.html
```
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

```

Pareil pour la servlet UserInfo.

### ManagerSingleton.java

Nous avons créé ce fichier pour eviter de créer la connexion à la base de données plusieurs fois pour chaque action de l'utilisateur.
```
private ManagerSingleton() {
		factory = Persistence.createEntityManagerFactory("example");
		manager = factory.createEntityManager();
		m=this;
	}
	public static ManagerSingleton getInstance() {
		if (m == null) { // Premier appel
	         m = new ManagerSingleton();
			}
		return m;
	}
```

## Etape 4 Les architectures Rest

Pour la partie Rest nous avons créé deux fichiers, HomeService.java et PersonService.java

HomeService.java nous permet de récupérer une maison spécifique,toutes les maisons, modifier, supprimer et ajouter une maisons dans la base de données en format json.

UserInfo.java nous permet de récupérer une personne spécifique,toutes les personnes,ajouter un utilisateur dans la base de données en format en json, pour la suppression d'une personne ça ne fonctionne pas à cause des clés étrangères d’autres classes. 

Nous avons utilisé les methodes POST,GET et DELETE:
```
@Path("/home")
public class PersonService {
	private ManagerSingleton ManagerSingleton;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
` public List<Person> getAllPersons() {
		``
	}	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHomeById(@PathParam("id") long id) {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		Home home = manager.find(Home.class, id);
		return home;
	}

	 @DELETE
	 @Path("/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public void removeHomeById(@PathParam("id") long id ) {
	 this.ManagerSingleton = ManagerSingleton.getInstance();
	 EntityManager manager = this.ManagerSingleton.getManager();
	 EntityTransaction tx = manager.getTransaction();
	 tx.begin();
	 manager.remove(manager.find(Home.class, id));
	 tx.commit();
	 }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHome(Home h) {
		this.ManagerSingleton = ManagerSingleton.getInstance();
		EntityManager manager = this.ManagerSingleton.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(h);
		tx.commit();
	}
```
Par défaut quand on met  localhost :8080/rest/home la méthode getAllHomes()est appelé qui est une méthode get ($GET) permettant de récupérer tous les maisons sous format Json « @produces(MediaType.APPLICATION8JSON)».
Quand on rajoute un id après home comme : localhost :8080/rest/home/1 la méthode getHomeById est appelé pour récupérer une maison ayant un id égal 1.
L’annotation @Pathparam("id") précise bien que l’id passé en url sera bien le paramètre de la fonction et aussi ici on récupère du json grâce à  @produces(MediaType.APPLICATION8JSON).
La troisième  est de type delete @DELETE pour supprimer une maison ayant un id précis
La dernière est de type POST @POST pour ajouter une maison, dans ce cas on doit fournir à la méthode addHome  une maison sous format JSON  « @consumes(MediaType.APPLICATION8JSON)».


Pareil pour UserInfo.java .








