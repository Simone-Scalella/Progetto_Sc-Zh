package univpm.esempio.model;
import univpm.esempio.interf.*;

import java.util.Vector;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.List;

public class FB_page_info implements Download {
	
	private String ID ="";
	private String period = "";
	private String Access_Token = "";
	
	private String[] metrics_arry = {"page_consumptions","page_consumptions_unique","page_negative_feedback","page_negative_feedback_unique","page_impressions","page_impressions_unique","page_fan_adds_unique"};
	private Vector<Integer> metric_values = new Vector<Integer>(metrics_arry.length);
	
	public class metric_values {
		public int page_consumptions = 0;
		public int page_consumptions_unique = 0;
		public int page_negative_feedback = 0;
		public int page_negative_feedback_unique=0;
		public int page_impressions = 0;
		public int page_impressions_unique = 0;
		public int page_fan_adds_unique = 0;
		
		public metric_values() {};
	}
	private metric_values metric_Object = new metric_values(); //inizializzazione
	public FB_page_info(String page_ID,String Access_Token,String period) {
		
		this.ID = page_ID;
		this.Access_Token = Access_Token;
		this.period = period;	
		String metrics = String.join(",", metrics_arry);
		System.out.println(metrics);
		JSONArray page_data = GetPage(page_ID,Access_Token,metrics).getJSONArray("data");
		
		
		for(Object metric : page_data) {
			System.out.println(metric);
			metric_values.add(((JSONObject)metric).getJSONArray("values").getJSONObject(1).getInt("value"));
		}
		
		Vector<String> metric_Vector =  new Vector<String>(Arrays.asList(metrics_arry)); //conversione
		JSONObject values_object = new JSONObject();
		for (int i =0;i<metrics_arry.length;i++) {
			values_object.put(metric_Vector.get(i) , page_data.getJSONObject(i).getJSONArray("values").getJSONObject(1).getInt("value") );
		}
		metric_Object = new Gson().fromJson(values_object.toString(),metric_values.class);
		System.out.println(page_data);
	}
	
	
	
	
		@Override
		public JSONObject GetPage(String ID,String Access_token,String metrics) {
			//TODO definire oggetto erorre e fare try catch
	    	//try(errorboj = respose) ...
			JSONObject response = new JSONObject(new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period="+period, String.class));
			String link = "https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period="+period;
			System.out.println(link);
			return response;
	    }

		public String getID() {
			return ID;
		}
		
		

		public String getPeriod() {
			return period;
		}

		/*
		public JSONObject get_stat(int i) {
			
		}
		*/

		public metric_values getMetric_Object() {
			return metric_Object;
		}



		public void setMetric_Object(metric_values metric_Object) {
			this.metric_Object = metric_Object;
		}
		
}
