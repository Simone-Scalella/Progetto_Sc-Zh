package univpm.OOP2020.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import univpm.OOP2020.body.request_body;
/**
 * <p>
 * <b>Classe</b> Per la gestione della richiesta della statistica 
 * </p>
 * @author Zhang Yihang & Simone Scalella
 * @version 0.9
 * @see univpm.OOP2020.model.FB_page_info
 * @see univpm.OOP2020.model.ALL_post
 * @see univpm.OOP2020.model.page_post
 */

public class Statics {
	/**
	 * Indica la classe della pagina <code>FB_page_info</code>
	 */
	FB_page_info page = null;
	/**
	 * Indica la classe dell'insieme di post <code>FB_page_info</code>
	 */
	ALL_post posts = null;
	/**
	 * Indica il vettore dei posts
	 */
	Vector<page_post> Posts_vector = null;
	/**
	 * Indica media dei click della pagina
	 */
	private float Media_click = 0;
	/**
	 * Indica media dei reazione negativa per persona
	 */
	private float Media_negativa = 0;
	/**
	 * Indica media dell'apparizione della pagina per persona
	 */
	private float Media_virale = 0;
	/**
	 * Indica lo stato di login
	 */
	private boolean login = false;
	/**
	 * Indica Id della pagina
	 */
	private String Id = "";
	/**
	 * Indica L'Access token della pagina
	 */
	private String Access_token = "";
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
			this.Id = Id;
			this.Access_token =Acess_token;
			//this.period = period;
			page = new FB_page_info(Id,Acess_token,period);
			posts = new ALL_post(Id,Acess_token);
			Media_click = divide(page.getMetric_Object().getPage_consumptions(),page.getMetric_Object().getPage_consumptions_unique());
			Media_negativa = divide(page.getMetric_Object().getPage_negative_feedback(),page.getMetric_Object().getPage_negative_feedback_unique());
		    Media_virale = divide(page.getMetric_Object().getPage_impressions(),page.getMetric_Object().getPage_impressions_unique());
			Posts_vector = posts.getPosts();
			login = true;			
		}catch (Exception e ){login = false;}
		
		return login;
	}

	/**
	 * @param query e' la struttura della condizione del filtro.
	 * @return <code> Vector<page_post> </code> se la condizione di <code> query </code> e' valido
	 * @return <code> String </code> con messaggio d'errore se la condizione di  <code> query </code> e' invalido
	 * @see univpm.esempio.body.request_body;
	 */
	public Object filter_method(request_body query) {
		
		ScriptEngine Script = new ScriptEngineManager().getEngineByName("JavaScript");		
		Vector<page_post> Posts_vector_2 = new Vector<page_post>();
		try {
			Method dyn_method = new page_post().getReactions_01().getClass().getMethod(("get"+query.getAttribute()));
			for(page_post post : Posts_vector){
				String impression_total =  ((Integer)dyn_method.invoke(post.getReactions_01())).toString();
				String condition = impression_total+query.getOperator()+query.getValue();            
				if((boolean)Script.eval(condition)){Posts_vector_2.add(post);}
			 }
		}
		catch (ScriptException e) 			{return ("Invalid Expression");} 
		catch (NoSuchMethodException e) 	{return ("No Such Reaction");} 
		catch (SecurityException e) 		{return ("Access Violation");}
		catch (IllegalAccessException e) 	{e.printStackTrace();} 
		catch (IllegalArgumentException e) 	{e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
        System.out.println("filter completed");
        return Posts_vector_2; //Vector<page_post>
	}
	/**
	 * 
	 * @return <code> String </code> il consumo medio dei post
	 */
	public String consumption_per_post()
	{return ("consumption_per_post: " + ((float)page.getMetric_Object().getPage_impressions()/(float)Posts_vector.size()));}
	/**
	 * 
	 * @return <code> String </code> la reazione media per post
	 */
	public String average_post_reaction(){
		int total_reaction = 0;
		for(page_post post:Posts_vector){total_reaction += post.getReactions_01().gettotal_impression();}
		return ("average_post_reaction: " + ((float)total_reaction/(float)Posts_vector.size()));
	}
	/**
	 * 
	 * @param a divisore
	 * @param b dividendo
	 * @return <code>float</code> a/b
	 */
	private float divide(int a,int b){return (float)a/(float)b;}
	/**
	 * Restituisce il vettore di page_post
	 * @return un <code>Vector<page_post></code> con tutti i posts della pagina
	 */
	public Vector<page_post> get_all_Post() {return Posts_vector;}
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
	 * @param period l'indica il nuovo periodo che desidera di aggiornare
	 */
	public void Update_period(String period) {this.page = new FB_page_info(Id,Access_token,period);}
	/**
	 * Restituisce la media click del contenuto della pagina per persona
	 * @return un <code>float</code> con media dei click
	 */
	public float getMedia_click() {return Media_click;}
	/**
	 * Restituisce la media della reazione negativa del contenuto della pagina per persona
	 * @return un <code>float</code> la media della reazione negativa
	 */
	public float getMedia_negativa() {return Media_negativa;}
	/**
	 * Restituisce la media della visualizzazione del contenuto della pagina per persona
	 * @return un <code>float</code> la media della visualizzazione
	 */
	public float getMedia_virale() {return Media_virale;}
	
	
}