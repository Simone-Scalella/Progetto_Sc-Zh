package univpm.esempio.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;


public class modello {
	public modello () {};
	ObjectMapper obj = new ObjectMapper();
		void get() {
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject("https://graph.facebook.com/112228053835001/posts?fields=insights.metric(post_impressions,post_impressions_unique,post_reactions_by_type_total)&access_token=EAAj9pRhdt7cBADoFODHvJhLjqnMdEZCKaz8ORVjZCUmdL0uubCSjjykBSAc2ZANdVoOO5ilUcW8Hd03NlefWTvQlgPjDeSNcsizn5dvhGaTgPiwLZCXDGOVdkL1CORzryXatEQEc1i38mR9og5ohFSf4iy8dqysyMcRsk3tsDAZDZD", String.class);
			//System.out.println(response);
		    JSONObject obj = new JSONObject(response);
		    JSONArray page_data = ((JSONObject)((obj.getJSONArray("data")).getJSONObject(0)).get("insights")).getJSONArray("data");
		    System.out.println(((JSONObject)((obj.getJSONArray("data")).getJSONObject(0)).get("insights")).getJSONArray("data"));
		    for(Object post:(obj.getJSONArray("data"))) { //for each post
		    	System.out.println("id: "+((JSONObject)post).getString("id"));
		    	
		    	for (Object param: (((JSONObject)post).getJSONObject("insights")).getJSONArray("data")){ //for each parameter
		    		
				    System.out.println("type: "+((JSONObject)param).getString("name"));
				    System.out.println("values: "+((((JSONObject)param).getJSONArray("values")).getJSONObject(0)).get("value"));
				    System.out.println("period: "+((JSONObject)param).getString("period"));
		    	}
		    	System.out.println("");
		    	}
		    }
		}
