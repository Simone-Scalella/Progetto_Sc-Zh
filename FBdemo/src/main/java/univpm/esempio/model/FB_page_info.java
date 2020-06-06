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
	private String Impressions = "";
	private String period = ""; //must be defined
	private String Access_Token = "";
	private Vector<page_post> posts = new Vector();
	private String[] metrics_arry = {"page_consumptions","page_consumptions_unique","page_negative_feedback","page_negative_feedback_unique","page_impressions","page_impressions_unique","page_impressions_organic_unique"};
	private Vector<Integer> metric_values = new Vector(metrics_arry.length);
	
	//Constructor
	private class metric_values {
		public int page_consumptions = 0;
		public int page_consumptions_unique = 0;
		public int page_negative_feedback = 0;
		public int page_negative_feedback_unique=0;
		public int page_impressions = 0;
		public int page_impressions_unique = 0;
		public int page_impressions_organic_unique = 0;
		
		public metric_values() {};
	}
	private metric_values metric_Object = new metric_values();
	
	public FB_page_info(String page_ID,String Access_Token,String period) {
		
		
		this.ID = page_ID;
		this.Access_Token = Access_Token;
		this.period = period;	
		String metrics = String.join(",", metrics_arry);
		System.out.println(metrics);
		//int metrics_size = metrics_arry.length; //quantita' di parametri && temporaneo
		//112228053835001 Page ID
		//EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD
		//String metrics = "page_posts_impressions,page_posts_impressions_unique,page_engaged_users";
		//String response =  new RestTemplate().getForObject("https://graph.facebook.com/112228053835001/insights?metric=page_consumptions_by_consumption_type&access_token=EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD", String.class);
		//System.out.print(response);
		JSONArray page_data = GetPage(page_ID,Access_Token,metrics).getJSONArray("data");
		
		
		for(Object metric : page_data) {
			System.out.println(metric);
			//int temp =  ((((JSONObject)metric).getJSONArray("values")).getJSONObject(1)).getInt("value") ;
			
			metric_values.add(((JSONObject)metric).getJSONArray("values").getJSONObject(1).getInt("value"));
			//System.out.println(temp);
		}
		
		Vector<String> metric_Vector =  new Vector<String>(Arrays.asList(metrics_arry)); //conversione
		JSONObject values_object = new JSONObject();
		for (int i =0;i<metrics_arry.length;i++) {
			values_object.put(metric_Vector.get(i) , page_data.getJSONObject(i).getJSONArray("values").getJSONObject(1).getInt("value") );
		}
		metric_Object = new Gson().fromJson(values_object.toString(),metric_values.class);
		System.out.println(page_data);
		//JSONObject obj = new JSONObject(response);
		/*
	    
         for (Object params:page_data) {
	    	
	    	System.out.println(((((JSONObject)params).getJSONArray("values")).getJSONObject(1)).getJSONObject("value"));
	    	System.out.println(((JSONObject)params).get("name"));
	   	
}	*/
	    
	}
	
	
	
		@Override
		public JSONObject GetPage(String ID,String Access_token,String metrics) {
			//TODO definire oggetto erorre e fare try catch
	    	//try(errorboj = respose) ...
			//System.out.println("https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period=days_28");
			JSONObject response = new JSONObject(new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/insights?metric="+metrics+"&access_token="+Access_token+"&period=days_28", String.class));
	    	return response;
	    }
		@Override
		public JSONObject GetPageUpdate(String ID, String Access_token) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String GetPageString(String ID, String Access_token) {
			// TODO Auto-generated method stub
			return null;
		}


		public String getID() {
			return ID;
		}
		
		
		public String getImpressions() {
			return Impressions;
		}


		public String getPeriod() {
			return period;
		}

		/*
		public JSONObject get_stat(int i) {
			
		}
		*/
		public String getAccess_Token() {
			return Access_Token;
		}


		public metric_values getMetric_Object() {
			return metric_Object;
		}



		public void setMetric_Object(metric_values metric_Object) {
			this.metric_Object = metric_Object;
		}



		public Vector<page_post> getPosts() {
			return posts;
		}
	
}
