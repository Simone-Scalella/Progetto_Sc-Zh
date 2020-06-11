package univpm.OOP2020.model;

import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.Gson;

/**
 * <p>
 * <b>Classe</b> Per la gestione della richiesta dati di un post
 * </p> 
 * @author Zhang Yihang e Simone Scalella
 * @version 0.9
 * @see univpm.OOP2020.model.page_post
 */

public class page_post  {
	
	/**
	 * questa classe serve per ricevere l'Id di ogni post dentro la pagina
	 */
	public class property{
		/**
		 * attributo della classe property
		 */
		private String id = "";
		/**
		 * costruttore vuoto della classe property
		 */
		public property () {};
		/**
		 * 
		 * @return questo e' il metodo getter per utilizzare l'attributo private Id
		 */
		public String getId() {return id;}
		
	}
	/**
	 * 
	 * all'interno di questa classe vengono salvati alcuni dati del post specificati di seguito 
	 *
	 */

	public class Impression{
		/**
		 * Il numero di volte in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo di una persona.
		 */
		private int impressions = 0;
		/**
		 * Il numero di persone in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo.
		 */
		private int unique_impression = 0;
		/**
		 * media tra il numero di e il numero di persone in cui un contenuto della tua Pagina o relativo alla tua Pagina è apparso nello schermo.
		 */
		private float impressions_per_person = 0;
		/**
		 * costruttore vuoto della classe Impression
		 */
		public Impression() {};
		/**
		 * metodo che assegna all'attributo precedente il suo effettivo valore 
		 */
		public void update() {impressions_per_person = (float)impressions/(float)unique_impression;}
		/**
		 * 
		 * @return metodo getter che ci restituisce l'attributo privato impressions
		 */
		public int getImpressions() {return impressions;}
		/**
		 * 
		 * @param impression è dato di tipo INT derivato dal JSONObject ottenuto tramite la get all'API della pagina Facebook
		 */
		public void setImpression(int impression) {this.impressions = impression;}
		/**
		 * 
		 * @return metodo che ci restituisce l'attributo privato unique_impression
		 */
		public int getUnique_impression() {return unique_impression;}
		/**
		 * 
		 * @param unique_impression<code>int</code>  derivato dal JSONObject ottenuto tramite la get all'API della pagina Facebook
		 */
		public void setImpression_unique(int unique_impression) {this.unique_impression = unique_impression;}
		/**
		 * 
		 * @return metodo che ci restituisce l'attributo privato Impressions_per_person
		 */
		public float getImpressions_per_person() {return impressions_per_person;}
		
		
	}
	/**
	 * 
	 * Classe che contiene tutte le possibili reazioni ai post
	 *
	 */
	public class Reactions{
		/**
		 * attributo per i like 
		 */
		private int like= 0;
		/**
		 * attributo per wow
		 */
		private int wow = 0;
		/**
		 * attributo per love 
		 */
		private int love = 0;
		/**
		 * attributo care 
		 */
		private int care = 0;
		/**
		 * attributo haha 
		 */
		private int haha = 0;
		/**
		 * attributo per il totale delle reazioni  
		 */
		private int total_impression = 0;
		/**
		 * costruttore vuoto di reactions
		 */
		public Reactions() {};
		/**
		 * metodo che assegna la somma delle reazioni all'attributo precedente
		 */
		public void update(){total_impression = like+wow+love+care+haha;}
		/**
		 * @return metodo getter per like
		 */
		public int getlike() {return like;}
		/**
		 * @return metodo getter per wow
		 */
		public int getwow() {return wow;}
		/**
		 * @return metodo getter per love
		 */
		public int getlove() {return love;}	
		/**
		 * @return metodo getter per care
		 */
		public int getcare(){return care;}
		/**
		 * @return metodo getter per haha
		 */
		public int gethaha() {return haha;}
		/**
		 * @return metodo getter per total_impression
		 */
		public int gettotal_impression() {return total_impression;}
		
	}
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
	private property property_01 = new property();
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
		property_01= temp_act.fromJson(post.toString(),property.class);
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
	 * @return metodo getter che restituisce un oggetto della classe reaction 
	 */
	public Reactions getReactions_01() {return Reactions_01;}
	/**
	 * @return metodo getter che restituisce un oggetto della classe Impression 
	 */
	public Impression getImpression_01() {return Impression_01;}
	/**
	 * @return metodo getter che restituisce un oggetto della classe property 
	 */
	public property getProperty_01() {return property_01;}	
}

