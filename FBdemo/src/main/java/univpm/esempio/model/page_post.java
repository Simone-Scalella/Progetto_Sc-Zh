package univpm.esempio.model;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import univpm.esempio.interf.Download;


public class page_post  implements Download {
	
	private String[] metrics_arry = {"post_impressions","post_impressions_unique","post_reactions_by_type_total"};
	//puoi fare query sulla quantita' delle metriche

	private class property{
		public String id = "";
		//public String title = "";
		public property () {};
	  /*  public property (String page_ID,String Access_Token,String metrics) {
	    	JSONObject post_data = GetPage(page_ID,Access_Token,metrics);
	    	this.id = (String) ((post_data).getJSONArray("data")).getJSONObject(0).get("id");
	    	this.title = (String)((((post_data).getJSONArray("data")).getJSONObject(0)).getJSONObject("insights").getJSONArray("data").get(4));
	    	
	    };*/
	
	}

	private class Impression{
		public int impressions = 0;
		public int unique_impression = 0;
		public float impressions_per_person = 0;
		public Impression() {};
		/*public Impression(String page_ID,String Access_Token,String metrics) {
			JSONObject post_data = GetPage(page_ID,Access_Token,metrics);
			this.impressions = (((post_data).getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONArray(2).getJSONObject(0).getInt("value");
		}*/
		public void update() {
			impressions_per_person = (float)impressions/(float)unique_impression;
		}
	}
	private class Reactions{
		public int like= 0;
		public int wow = 0;
		public int love = 0;
		public int care = 0;
		public int haha = 0;
		public int total_impression = 0;
		public Reactions() {};
		/*
		public Reactions(String page_ID,String Access_Token,String metrics) {
			JSONObject post_data = GetPage(page_ID,Access_Token,metrics);
			this.total_impression = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(0).getJSONArray("values").getJSONObject(0).getInt("value");
			this.like = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value").getInt("like");
			this.haha = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value").getInt("haha");
			this.love = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value").getInt("love");
			this.wow = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value").getInt("wow");
			this.care = ((post_data.getJSONArray("data")).getJSONObject(0).getJSONObject("insights").getJSONArray("data")).getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value").getInt("care");
		}*/
		public void update() {
			total_impression = like+wow+love+care+haha;
		}
	}
	
	
	
	private Reactions  Reactions_01 = new Reactions();
	private Impression Impression_01 = new Impression();
	private property property_01 = new property();
			
	public page_post (String ID,String Access_token,JSONObject post) {
		System.out.println("test");
		String metrics = String.join(",", metrics_arry);
		Gson temp_act = new Gson();
		//JSONObject buffer = 
		Reactions_01= temp_act.fromJson(GetPage(ID,Access_token,metrics).getJSONArray("data").getJSONObject(0).getJSONObject("insights").getJSONArray("data").getJSONObject(2).getJSONArray("values").getJSONObject(0).getJSONObject("value").toString(),Reactions.class);
		property_01= temp_act.fromJson(GetPage(ID,Access_token,metrics).getJSONArray("data").getJSONObject(0).toString(),property.class);
		System.out.println("Get Reaction success");
		Impression_01.impressions = GetPage(ID,Access_token,metrics).getJSONArray("data").getJSONObject(0).getJSONObject("insights").getJSONArray("data").getJSONObject(0).getJSONArray("values").getJSONObject(0).getInt("value");
		Impression_01.unique_impression =  GetPage(ID,Access_token,metrics).getJSONArray("data").getJSONObject(0).getJSONObject("insights").getJSONArray("data").getJSONObject(1).getJSONArray("values").getJSONObject(0).getInt("value");
		Impression_01.update();
		//Reactions Reactions_01 =(Object) GetPage(ID,Access_token,metrics).getJSONArray("data").getJSONObject(0).getJSONObject("insights").getJSONArray("data").getJSONObject(1).getJSONArray("values").getJSONObject(0).getJSONObject("value");
		
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
	@Override
	public JSONObject GetPage(String ID,String Access_token,String metrics) {
		//TODO definire oggetto erorre e fare try catch
    	//try(errorboj = respose) ...
    	JSONObject response = new JSONObject( new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/posts?fields=insights.metric("+metrics+")&access_token="+Access_token, String.class));
    	System.out.println("response is : "+ response);
    	return response;
    }


	public Reactions getReactions_01() {
		return Reactions_01;
	}


	public void setReactions_01(Reactions reactions_01) {
		Reactions_01 = reactions_01;
	}


	public Impression getImpression_01() {
		return Impression_01;
	}


	public void setImpression_01(Impression impression_01) {
		Impression_01 = impression_01;
	}


	public property getProperty_01() {
		return property_01;
	}


	public void setProperty_01(property property_01) {
		this.property_01 = property_01;
	}
	
}

