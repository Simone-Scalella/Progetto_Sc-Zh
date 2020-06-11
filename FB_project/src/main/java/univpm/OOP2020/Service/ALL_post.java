
package univpm.OOP2020.Service;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import univpm.OOP2020.Model.page_post;

/**
 * <p>
 * <b>Classe</b> Per la gestione della richiesta dati di un post
 * </p> 
 * @author Zhang Yihang & Simone Scalella
 * @version 0.9
 * @see univpm.OOP2020.model.ALL_post
 */
/**
 * All'interno di questa classe andiamo prendere tutti i post della nostra pagina,
 * implementiamo l'interfaccia Download per usare il metodo get_page
 * @see univpm.OOP2020.Service.Download
 *
 */
public class ALL_post implements Download{
	/**
	 * metric_array e' un vettore di stringhe all'interno del quale inseriamo le nostre metic 
	 */
	private String[] metrics_arry = {"post_impressions","post_impressions_unique","post_reactions_by_type_total"};
	/**
	 * posts e' un vettore dinamico di oggetti della classe page_post
	 */
	private Vector<page_post> posts = new Vector<page_post>();
	/**
	 * inizializziamo metrics che è una stringa formata da tutte le metric contenute nel nostro metrics_array
	 */
	String metrics = String.join(",", metrics_arry);
	/**
	 * Costruttore della classe ALL_post, al suo inerno abbaimo la chiamata al metodo GetPage e tale valore viene assegnato
	 * a Json_posts di tipo JSONArray,infine un for che scorre tutto il JSONArray e al suo interno aggiungiamo elementi al vettore posts,
	 * chiamando il costruttore di post_page e passandogli l'oggetto del for
	 * @param ID <code>String</code> che indica id della pagina
	 * @param Access_token <code>String</code> che indica Access token di API Facebook
	 */
	public ALL_post (String ID,String Access_token){
		JSONArray Json_posts = GetPage(ID,Access_token,this.metrics).getJSONArray("data");
		for (Object pippo : Json_posts ) {
			posts.add(new page_post((JSONObject)pippo));
		}
	}
	
	/**
	 * @return metodo getters che ci resttuisce posts
	 */
	public Vector<page_post> getPosts() {return posts;}
    /**
     * metodo GetPage utilizzato per fare la chiamata alla nostra API facebook 
     * e ci restituisce il JSONObject con tutte le informazioni necessarie
     */
	public JSONObject GetPage(String ID,String Access_token,String metrics) {
    	JSONObject response = new JSONObject( new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/posts?fields=insights.metric("+metrics+")&access_token="+Access_token, String.class));
    	return response;
	}

}

