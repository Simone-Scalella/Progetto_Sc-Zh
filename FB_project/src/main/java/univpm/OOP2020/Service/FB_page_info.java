package univpm.OOP2020.Service;
import java.util.Vector;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import univpm.OOP2020.Model.Metric_values;
import univpm.OOP2020.Service.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
/**
 * <p>
 * <b>Class</b> che contiene l'informazioni e i metodi relativa alla pagina
 * </p>
 * @author Zhang Yihang e Simone Scalella
 * 
 */
public class FB_page_info implements Download {
	
	/**
	 * indica ID della pagina
	 */
	private String id ="";
	/**
	 * indica il periodo dei dati
	 */
	private String period = "";
	/**
	 * indica insieme delle metriche che i dati vengono acquisiti tramite API di Facebook
	 */
	private String[] metrics_arry = {"page_consumptions","page_consumptions_unique","page_negative_feedback","page_negative_feedback_unique","page_impressions","page_impressions_unique","page_fan_adds_unique"};
	/**
	 * indica valore delle metriche in cui abbiamo acquisiti tramite API di Facebook
	 */
	private Vector<Integer> metric_values = new Vector<Integer>(metrics_arry.length);
	/**
	 * Indica la struttura in cui sono salvati i dati delle varie metriche
	 * @author Zhang Yihang e Scalella Simone
	 *
	 */
	
	/**
	 * Indicano Oggetti delle metriche dove vengono salvati i valori delle metriche
	 */
	private Metric_values metric_Object = new Metric_values(); //inizializzazione
	/**
	 * Costruisce la pagina tramite API Facebook con dati forniti dall'utente 
	 * @param page_ID <code>String</code>  indica Id della pagina da cui acquisire le informazioni tramite API Facebook
	 * @param Access_Token <code>String</code>  indica Access Token della pagina da acquisire le informazioni tramite API Facebook
	 * @param period <code>String</code>  indica il periodo della pagina da acquisire le informazioni tramite API Faceboo
	 * @see #GetPage(String, String, String)
	 */
	public FB_page_info(String page_ID,String Access_Token,String period) {
		
		this.id = page_ID;
		this.period = period;	
		String metrics = String.join(",", metrics_arry);
		JSONArray page_data = GetPage(page_ID,Access_Token,metrics).getJSONArray("data");
		
		//popolare il vettore dei valori per rispettivi metriche
		for(Object metric : page_data) {metric_values.add(((JSONObject)metric).getJSONArray("values").getJSONObject(1).getInt("value"));}		
		Vector<String> metric_Vector =  new Vector<String>(Arrays.asList(metrics_arry));
		//Costruzione dell'oggetto metric_values partendo da metric_Vector e metric_values
		JSONObject values_object = new JSONObject();		
		for (int i =0;i<metrics_arry.length;i++) {
			values_object.put(metric_Vector.get(i) , page_data.getJSONObject(i).getJSONArray("values").getJSONObject(1).getInt("value") );
		}
		metric_Object = new Gson().fromJson(values_object.toString(),Metric_values.class);
	    metric_Object.setMedia_click(divide(this.getMetric_Object().getPage_consumptions(),this.getMetric_Object().getPage_consumptions_unique()));
		metric_Object.setMedia_negativa(divide(this.getMetric_Object().getPage_negative_feedback(),this.getMetric_Object().getPage_negative_feedback_unique()));
	    metric_Object.setMedia_virale(divide(this.getMetric_Object().getPage_impressions(),this.getMetric_Object().getPage_impressions_unique())); 
	}
	
		/**
		 * Restituisce i dati primitivi dalla pagina tramite API Facebook con dati forniti dall'utente 
		 * @param ID <code>String</code> page_ID indica Id della pagina da acquisire le informazioni tramite API Facebook
		 * @param Access_token <code>String</code> Access_Token indica Access Token della pagina da acquisire le informazioni tramite API Facebook
	 	 * @param metrics <code>String</code> period indica il periodo della pagina da acquisire le informazioni tramite API Facebook
	 	 * @return <code>JSONObject</code> che contiene i dati primitivi della pagina
		 */
		@Override
		public JSONObject GetPage(String ID,String Access_token,String metrics) {
			JSONObject response = new JSONObject(new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period="+period, String.class));
			return response;
	    }
		/**
		 * Restituisce Id della pagina
		 * @return <code>String</code> che contiene ID della pagina
		 */
		public String getID() {	return id;}	
		/**
		 * Restituisce il periodo in cui i dati della pagina sono ottenuti 
		 * @return <code>String</code> che contiene il periodo
		 */
		public String getPeriod() {return period;}
		/**
		 * Restituisce l'oggetto che contiene insieme delle metriche e i rispettivi valori
		 * @return <code>metri_value</code> che contiene metriche e i rispettivi valori
		 */
		public Metric_values getMetric_Object() {return metric_Object;}	
		/**
		 * 
		 * @param a divisore
		 * @param b dividendo
		 * @return <code>float</code> a/b
		 */
		private float divide(int a,int b){return (float)a/(float)b;}
		
}
