package univpm.esempio.model;

import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.Gson;



public class page_post  {
	

	public class property{
		public String id = "";
		public property () {};
	
	}

	public class Impression{
		public int impressions = 0;
		public int unique_impression = 0;
		public float impressions_per_person = 0;
		public Impression() {};
		public void update() {
			impressions_per_person = (float)impressions/(float)unique_impression;
		}
	}
	public class Reactions{
		public int like= 0;
		public int wow = 0;
		public int love = 0;
		public int care = 0;
		public int haha = 0;
		public int total_impression = 0;
		public Reactions() {};
		public void update() {
			total_impression = like+wow+love+care+haha;
		}
	}
	
	
	
	private Reactions  Reactions_01 = new Reactions();
	private Impression Impression_01 = new Impression();
	private property property_01 = new property();
			
	public page_post (JSONObject post) {
		
		Gson temp_act = new Gson();
		JSONArray data_array = post.getJSONObject("insights").getJSONArray("data");
		Reactions_01= temp_act.fromJson(data_array.getJSONObject(2).getJSONArray("values").getJSONObject(0).getJSONObject("value").toString(),Reactions.class);
		property_01= temp_act.fromJson(post.toString(),property.class);
		Impression_01.impressions = data_array.getJSONObject(0).getJSONArray("values").getJSONObject(0).getInt("value");
		Impression_01.unique_impression =  data_array.getJSONObject(1).getJSONArray("values").getJSONObject(0).getInt("value");
		Impression_01.update();
		Reactions_01.update();
		
		
	}
	
	public page_post() {};
	
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
