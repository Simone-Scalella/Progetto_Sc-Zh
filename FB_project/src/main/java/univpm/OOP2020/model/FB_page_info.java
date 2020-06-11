package univpm.OOP2020.model;
import java.util.Vector;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import univpm.OOP2020.interf.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
/**
 * 
 * @author Zhang Yihang & Simone Scalella
 * 
 */
public class FB_page_info implements Download {
	
	private String ID ="";
	private String period = "";
	
	private String[] metrics_arry = {"page_consumptions","page_consumptions_unique","page_negative_feedback","page_negative_feedback_unique","page_impressions","page_impressions_unique","page_fan_adds_unique"};
	private Vector<Integer> metric_values = new Vector<Integer>(metrics_arry.length);
	
	public class metric_values {
		private Integer page_consumptions = null;
		private Integer page_consumptions_unique = null;
		private Integer page_negative_feedback = null;
		private Integer page_negative_feedback_unique= null;
		private Integer page_impressions = null;
		private Integer page_impressions_unique = null;
		private Integer page_fan_adds_unique = null;
		
		public metric_values() {}

		public Integer getPage_consumptions() {return page_consumptions;}
		public Integer getPage_consumptions_unique() {return page_consumptions_unique;}
		public Integer getPage_negative_feedback() {return page_negative_feedback;}
		public Integer getPage_negative_feedback_unique() {return page_negative_feedback_unique;}
		public Integer getPage_impressions() {return page_impressions;}
		public Integer getPage_impressions_unique() {return page_impressions_unique;}
		public Integer getPage_fan_adds_unique() {return page_fan_adds_unique;};
		
	}
	
	private JSONArray page_data = null;
	private metric_values metric_Object = new metric_values(); //inizializzazione
	public FB_page_info(String page_ID,String Access_Token,String period) {
		
		this.ID = page_ID;
		this.period = period;	
		String metrics = String.join(",", metrics_arry);
		//System.out.println(metrics);
		page_data = GetPage(page_ID,Access_Token,metrics).getJSONArray("data");
		
		
		for(Object metric : page_data) {
			//System.out.println(metric);
			metric_values.add(((JSONObject)metric).getJSONArray("values").getJSONObject(1).getInt("value"));
		}
		
		Vector<String> metric_Vector =  new Vector<String>(Arrays.asList(metrics_arry)); //conversione
		JSONObject values_object = new JSONObject();
		for (int i =0;i<metrics_arry.length;i++) {
			values_object.put(metric_Vector.get(i) , page_data.getJSONObject(i).getJSONArray("values").getJSONObject(1).getInt("value") );
		}
		metric_Object = new Gson().fromJson(values_object.toString(),metric_values.class);
		//System.out.println(page_data);
	}
	
	
	
	
		@Override
		public JSONObject GetPage(String ID,String Access_token,String metrics) {
			JSONObject response = new JSONObject(new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period="+period, String.class));
			return response;
	    }

		public String getID() {	return ID;}	

		public String getPeriod() {return period;}

		public metric_values getMetric_Object() {return metric_Object;}
		
		public JSONArray getPage_data () {return page_data;}
		
		public void setMetric_Object(metric_values metric_Object) {this.metric_Object = metric_Object;}
		
}
