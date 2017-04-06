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

Tout d’abord, nous avons modifié notre fichier pom.xml pour ajouter la dépendance à l'API des servlets

```
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.0.1</version>
	<scope>provided</scope>
</dependency>
```

Une fois que nous avons fini d'implementation des dépendances ,on a lancé la commande "mvn tomcat7:run " dans la console.


## Etape 2 Insertion de ressources statiques

Nous avons crée 2 fichiers d'html pour récupérer les informations qui sera saisie par les utilisateurs.Dans ces deux fichiers d'html on a mis une formulaire avec des differents champs.


Puisque nous avons deux actions possibles dans chaque fichier d'Html
1. Afficher les données
2. Mettre les données dans la base 
on a utilisé les methodes de POST et GET. 


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

## Etape 3 Création des Servlets

Pour créer une page web qui retourne des informations issus
de la base de données et un formulaire qui permet d’ajouter des éléments dans la
base de données nous avons besion forcement des servlets qui sera intermediare pour la comunication entre la base de données et la page web.

Donc nous avons crée deux fichiers de servlets pour deux pages web differentes.

### HomeInfo.java - UserInfo.java

Une fois que nous avons defini EntityManager pour utiliser les foncionalités comme "Insert","Select","Delete" pour la base de données 

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

On met les informations ou un utilisateur a saisie par formulaire de notre page web c'est pour ça que  nous avons utilisé les fonctions de setter de fichier Home.java

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

Pour le fichier de UserInfo aussi nous avons fait les meme etapes.

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

HomeService.java nous permet de voir les informations des maison qui se trouve sur la base de données en format de json en localhost

UserInfo.java nous permet de voir les informations des utilisateurs qui se trouve sur la base de données en format de json en localhost

Nous avons utilisé les methodes POST,GET et DELETE et aussi en utilisant managerSingleton on a evité des connections unsuffisant à la base de données apres chaque action.
```
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
Nous avons fait les meme etapes pour UserInfo.java .








