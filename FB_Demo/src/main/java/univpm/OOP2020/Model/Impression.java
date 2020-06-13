package univpm.OOP2020.Model;

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