package univpm.OOP2020.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import univpm.OOP2020.Body.request_body;
import univpm.OOP2020.Service.ALL_post;
import univpm.OOP2020.Service.FB_page_info;
/**
 * <p>
 * <b>Classe</b> Per la gestione della richiesta della statistica 
 * </p>
 * @author Zhang Yihang
 * @see univpm.OOP2020.Service.FB_page_info
 * @see univpm.OOP2020.Service.ALL_post
 * @see univpm.OOP2020.Model.page_post
 */

public class Statics {
	/**
	 * Indica la classe della pagina <code>FB_page_info</code>
	 * @see univpm.OOP2020.Service.FB_page_info
	 */
	FB_page_info page = null;
	/**
	 * Indica il vettore dei posts
	 */
	Vector<page_post> posts_vector = null;
	/**
	 * Indica lo stato di login
	 */
	private boolean login = false;
	/**
	 * Indica Id della pagina
	 */
	private String id = "";
	/**
	 * Indica L'Access token della pagina
	 */
	private String access_token = "";
	/**
	 * Crea la statistica con valori nulli usato solo per inizializzazione
	 */
	public Statics() {};
	/**
	 * Effettua login della pagina dando dati dell'utente.
	 * @param Id indica ID della pagina
	 * @param Acess_token indica access_token della pagina
	 * @param period indica periodo della richiesta con valori: <code> "day"</code> , <code> "week"</code> o <code> "days_28"</code> 
	 * @return un <code> true </code> se login e' successo e <code> false</code> se longin e' fallito
	 */	
	public boolean Login(String Id, String Acess_token, String period) {		
		try {
			this.id = Id;
			this.access_token =Acess_token;
			page = new FB_page_info(Id,Acess_token,period);
			posts_vector = new ALL_post(Id,Acess_token).getPosts();
			login = true;			
		}catch (Exception e ){login = false;}
		
		return login;
	}

	/**
	 * @param query e' la struttura della condizione del filtro.
	 * @return <code> Vector page_post  </code> se la condizione di <code> query </code> e' valido
	 * @throws ResponseStatusException con messaggio d'errore se la condizione di  <code> query </code> e' invalido
	 * @see univpm.OOP2020.Body.request_body
	 */
	public Object filter_method(request_body query) {
		
		ScriptEngine Script = new ScriptEngineManager().getEngineByName("JavaScript");		
		Vector<page_post> Posts_vector_2 = new Vector<page_post>();
		try {
			Method dyn_method = new page_post().getReactions_01().getClass().getMethod(("get"+query.getAttribute()));
			for(page_post post : posts_vector){
				String impression_total =  ((Integer)dyn_method.invoke(post.getReactions_01())).toString();
				String condition = impression_total+query.getOperator()+query.getValue();            
				if((boolean)Script.eval(condition)){Posts_vector_2.add(post);} //Filter with "generi" expression
			 }
		}
		catch (ScriptException e) 			{throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Expression");} 
		catch (NoSuchMethodException e) 	{throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No Such Reaction");} 
		catch (SecurityException e) 		{throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Access Violation");}
		catch (IllegalAccessException e) 	{System.out.println("UNEXPECTED BREACH: Illegal Access");e.printStackTrace();} 
		catch (IllegalArgumentException e) 	{System.out.println("UNEXPECTED BREACH: Illegal Argument");e.printStackTrace();} 
		catch (InvocationTargetException e) {System.out.println("UNEXPECTED BREACH: Invocation target");e.printStackTrace();}
        System.out.println("filter completed");
        return Posts_vector_2; //Vector<page_post> filtered posts
	}
	/**
	 * 
	 * @return <code> String </code> il consumo medio dei post
	 */
	public String impression_per_post() // impression / number of posts
	{return ("impression_per_post: " + ((float)page.getMetric_Object().getPage_impressions()/(float)posts_vector.size()));}
	/**
	 * 
	 * @return <code> String </code> la reazione media per post
	 */
	public String average_post_reaction(){ // total reaction/ number of posts average reaction
		int total_reaction = 0;
		for(page_post post:posts_vector){total_reaction += post.getReactions_01().gettotal_impression();}
		return ("average_post_reaction: " + ((float)total_reaction/(float)posts_vector.size()));
	}
	
	/**
	 * Restituisce il vettore di page_post
	 * @return un <code>Vector page_post</code> con tutti i posts della pagina
	 */
	public Vector<page_post> get_all_Post() {return posts_vector;}
	/**
	 * Restituisce lo stato del login
	 * @return un <code> boolean </code> con lo stato del Login
	 */
	public boolean isLogin() {return login;}
	/**
	 * Restituisce l'oggetto della pagina
	 * @return un <code> FB_page_info </code> con la pagina
	 */
	public FB_page_info get_page_all() {return page;}
	/**
	 * Aggiorna il periodo della metrica Facebook
	 * @param period <code>String</code> l'indica il nuovo periodo che desidera di aggiornare
	 */
	public void Update_period(String period) {this.page = new FB_page_info(id,access_token,period);}
	
}