package univpm.OOP2020.Model;
/**
 * <p>
 * <b>Classe</b> che contiene i valori delle metriche
 * </p>
 * @author Zhang Yihang
 * 
 *
 */
public class Metric_values {
	/**
	 * attributo privato relativo alla metric page_consumptions
	 */
	private Integer page_consumptions = null;
	/**
	 * attributo privato relativo alla metric page_consumptions_unique
	 */
	private Integer page_consumptions_unique = null;
	/**
	 * attributo privato relativo alla metric page_negative_feedback
	 */
	private Integer page_negative_feedback = null;
	/**
	 * attributo privato relativo alla metric page_negative_feedback_unique
	 */
	private Integer page_negative_feedback_unique= null;
	/**
	 * attributo privato relativo alla metric page_impressions
	 */
	private Integer page_impressions = null;
	/**
	 * attributo privato relativo alla page_impressions_unique
	 */
	private Integer page_impressions_unique = null;
	/**
	 * attributo privato relativo alla page_fan_adds_unique
	 */
	private Integer page_fan_adds_unique = null;
	/**
	 * Indica media dei click della pagina
	 */
	private float media_click = 0;
	/**
	 * Indica media dei reazione negativa per persona
	 */
	private float media_negativa = 0;
	/**
	 * Indica media dell'apparizione della pagina per persona
	 */
	private float media_virale = 0;
	/**
	 * costruttore vuoto della classe metric_values
	 */
	public Metric_values() {}
    /**
     * @return un <code> Integer</code> che contiene consumo della pagina
     */
	public Integer getPage_consumptions() {return page_consumptions;}
    /**
     * @return un <code> Integer</code> che contiene consumo unico della pagina
     */
	public Integer getPage_consumptions_unique() {return page_consumptions_unique;}
    /**
     * @return un <code> Integer</code> che contiene consumo della pagina
     */
	public Integer getPage_negative_feedback() {return page_negative_feedback;}
    /**
     * @return un <code> Integer</code> che contiene feedback negativa unica della pagina
     */
	public Integer getPage_negative_feedback_unique() {return page_negative_feedback_unique;}
    /**
     * @return un <code> Integer</code> che contiene impressione della pagina
     */
	public Integer getPage_impressions() {return page_impressions;}
    /**
     * @return un <code> Integer</code> che contiene impressione unico della pagina
     */
	public Integer getPage_impressions_unique() {return page_impressions_unique;}
    /**
     * @return un <code> Integer</code> che contiene numero di fan della pagina
     */
	public Integer getPage_fan_adds_unique() {return page_fan_adds_unique;};
	/**
	 * Restituisce la media click del contenuto della pagina per persona
	 * @return un <code>float</code> con media dei click
	 */
	public float getMedia_click() {return media_click;}
	/**
	 * Restituisce la media della reazione negativa del contenuto della pagina per persona
	 * @return un <code>float</code> la media della reazione negativa
	 */
	public float getMedia_negativa() {return media_negativa;}
	/**
	 * Restituisce la media della visualizzazione del contenuto della pagina per persona
	 * @return un <code>float</code> la media della visualizzazione
	 */
	public float getMedia_virale() {return media_virale;}
	/**
	 * metodo utilizzato per valorizzare l'attributo media_click
	 * @param valore che indica valore di media click
	 */
	public void setMedia_click(float valore) {this.media_click = valore;}
	/**
	 * metodo utilizzato per valorizzare l'attributo media_negativa
	 * @param valore che indica valore di media negativa
	 */
	public void setMedia_negativa(float valore) {this.media_negativa = valore;}
	/**
	 * metodo utilizzato per valorizzare l'attributo media_virale
	 * @param valore che indica valore di media virale
	 */
	public void setMedia_virale(float valore) {this.media_virale = valore;}
}