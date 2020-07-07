
package univpm.esempio.model;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import univpm.esempio.interf.Download;

public class ALL_post implements Download{
	private String[] metrics_arry = {"post_impressions","post_impressions_unique","post_reactions_by_type_total"};
	private Vector<page_post> posts = new Vector<page_post>();
	String metrics = String.join(",", metrics_arry);
	
	public ALL_post (String ID,String Access_token){
		JSONArray Json_posts = GetPage(ID,Access_token,this.metrics).getJSONArray("data");
		for (Object pippo : Json_posts ) {
			posts.add(new page_post((JSONObject)pippo));
		}
	}
	
	
	public Vector<page_post> getPosts() {
		return posts;
	}

	public JSONObject GetPage(String ID,String Access_token,String metrics) {
    	JSONObject response = new JSONObject( new RestTemplate().getForObject("https://graph.facebook.com/"+ID+"/posts?fields=insights.metric("+metrics+")&access_token="+Access_token, String.class));
    	return response;
	}

}

