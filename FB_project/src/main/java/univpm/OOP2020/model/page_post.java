package univpm.OOP2020.Model;

import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.Gson;

/**
 * <p>
 * <b>Classe</b> Per la gestione della richiesta dati di un post
 * </p> 
 * @author Simone Scalella
 * @see univpm.OOP2020.Model.page_post
 */

public class page_post  {
	
	/**
	 * viene istanziato un oggetto della classe Reaction che si chiama reaction_01
	 */
	private Reactions  Reactions_01 = new Reactions();
	/**
	 * viene istanziato un oggetto della classe Impression che si chiama impression_01
	 */
	private Impression Impression_01 = new Impression();
	/**
	 * viene istanziato un oggetto della classe property che si chiama property_01
	 */
	private Property property_01 = new Property();
	/**
	 * costruttore della pagina page_post
	 * @param post di tipo JSONObject,per poter utilizzare tale costruttoe nel for di ALL_page.
	 * questo costruttore passatogli un JSONObject tramite la classe Gson può estrarre tutte le informazioni richieste,
	 * e assegnarle agli attributi delle varie classi create in precedenza 
	 */
	public page_post (JSONObject post) {
	
		Gson temp_act = new Gson(); //instaza del Gson()
		JSONArray data_array = post.getJSONObject("insights").getJSONArray("data");
		Reactions_01= temp_act.fromJson(data_array.getJSONObject(2).getJSONArray("values").getJSONObject(0).getJSONObject("value").toString(),Reactions.class);
		property_01= temp_act.fromJson(post.toString(),Property.class);
		Impression_01.setImpression(data_array.getJSONObject(0).getJSONArray("values").getJSONObject(0).getInt("value"));
		Impression_01.setImpression_unique(data_array.getJSONObject(1).getJSONArray("values").getJSONObject(0).getInt("value"));
		Impression_01.update();
		Reactions_01.update();
		
		
	}
	/**
	 * costruttore vuoto di page_post
	 */
	public page_post() {};	
	/**
	 * @return un <code> Reaction </code> che contiene reaction del post 
	 */
	public Reactions getReactions_01() {return Reactions_01;}
	/**
	 * @return <code> Impression </code> metodo getter che restituisce un oggetto della classe Impression 
	 */
	public Impression getImpression_01() {return Impression_01;}
	/**
	 * @return <code> Property </code>  metodo getter che restituisce un oggetto della classe property 
	 */
	public Property getProperty_01() {return property_01;}	
}

